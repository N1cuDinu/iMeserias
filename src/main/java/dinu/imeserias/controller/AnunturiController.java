package dinu.imeserias.controller;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.model.*;
import dinu.imeserias.repository.ReviewuriRepository;
import dinu.imeserias.security.SecurityUtil;
import dinu.imeserias.service.AnunturiService;
import dinu.imeserias.service.LocalitatiService;
import dinu.imeserias.service.ServiciiService;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.DoubleBuffer;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AnunturiController {
    private AnunturiService anunturiService;
    private ServiciiService serviciiService;
    private UtilizatorService utilizatorService;
    private LocalitatiService localitatiService;
    private ReviewuriRepository reviewuriRepository;
    @Autowired
    public AnunturiController(AnunturiService anunturiService, UtilizatorService utilizatorService, ServiciiService serviciiService, LocalitatiService localitatiService, ReviewuriRepository reviewuriRepository) {
        this.anunturiService = anunturiService;
        this.serviciiService = serviciiService;
        this.utilizatorService = utilizatorService;
        this.localitatiService = localitatiService;
        this.reviewuriRepository = reviewuriRepository;
    }
    @GetMapping({"/index", "/"})
    public String goToIndex(){
        return "index";
    }
    @GetMapping("/judete")
    @ResponseBody
    public List<String> getJudete() {
        return anunturiService.returnJudete();
    }

    @GetMapping("/localitati")
    @ResponseBody
    public List<String> getLocalitati(@RequestParam String judet) {
        return anunturiService.returnLocalitateByJudete(judet);
    }
    @GetMapping("/anunturi")
    public String listAnunturi(Model model, @RequestParam(defaultValue = "0") int page) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilizatori utilizator = new Utilizatori();

        // Setăm paginarea cu 10 anunțuri per pagină
        Pageable pageable = PageRequest.of(page, 10);

        // Obținem anunțurile pentru pagina curentă
        Page<AnunturiDto> anunturiPage = anunturiService.findAllAnunturi(pageable);

        String username = SecurityUtil.getSessionUser();
        List<Servicii> servicii = serviciiService.findAllServicii();
        if(username != null) {
            utilizator = utilizatorService.findByUsername(username);
        }

        // Adaugă logica pentru a verifica recenziile utilizatorului
        List<Reviewuri> recenziiUtilizator = reviewuriRepository.findReviewuriByIdUser(utilizator.getIduser());
        Map<Integer, Reviewuri> recenziiAnunt = new HashMap<>();
        for (Reviewuri recenzie : recenziiUtilizator) {
            // Maparea recenziilor la anunțurile corespunzătoare
            recenziiAnunt.put(recenzie.getIdanunt(), recenzie); // Folosește ID-ul anunțului din recenzie
        }
        model.addAttribute("judetFiltru", getJudete());
        model.addAttribute("serviciiFiltru", servicii);
        model.addAttribute("utilizator", utilizator);
        model.addAttribute("anunturiPage", anunturiPage);
        model.addAttribute("authentication", auth);
        model.addAttribute("recenziiAnunt", recenziiAnunt); // Adăugăm recenziile în model
        return "anunturi";
    }


    @GetMapping("/anunturi/new")
    public String createAnuntForm(Model model) {
        Anunturi anunturi = new Anunturi();
        model.addAttribute("anunt", anunturi);
        model.addAttribute("servicii", serviciiService.findAllServicii());  // Lista serviciilor pentru dropdown
        return "adaugareAnunt";
    }

    @PostMapping("/anunturi/new")
    public String createAnunt(@ModelAttribute("anunt") Anunturi anunturi,
                              @RequestParam("serviciuId") int serviciuId,
                              @RequestParam("autocompleteLocalitate") String localitate) {

        Servicii serviciu = serviciiService.findById(serviciuId);
        anunturi.setServiciu(serviciu);  // Setează serviciul selectat

        // Salvează anunțul
        anunturiService.saveAnunt(anunturi, localitate);
        return "redirect:/anunturi";
    }

    @GetMapping("/anunturi/edit/{id}")
    public String editAnuntForm(@PathVariable("id") int id, Model model) {
        AnunturiDto anunt = anunturiService.findById(id);
        if (anunt == null) {
            return "redirect:/anunturi";  // Sau afișează un mesaj de eroare
        }

        // Găsește județul și localitatea asociată anunțului
        String[] parts = anunt.getLocalizare().split(",\\s*");
        String numeLocalitate = parts[0];
        String judet = parts[1];
        Localitati localitate = anunturiService.findLocalitateByJudetAndNume(judet, numeLocalitate);
        System.out.println(localitate);

        model.addAttribute("anunt", anunt);
        model.addAttribute("servicii", serviciiService.findAllServicii());
        model.addAttribute("judete", anunturiService.returnJudete());
        System.out.println("LOCALITATE+ JUDET = " + localitate.getJudet() + " " +  localitate.getNume());
        // Adăugăm județul și localitatea pentru a fi utilizate în pagină
        if (localitate != null) {
            model.addAttribute("județSelectat", localitate.getJudet());
            model.addAttribute("localitateSelectată", localitate.getNume());
        }

        return "modificareAnunt";  // Numele template-ului Thymeleaf pentru editare
    }

    @PostMapping("/anunturi/edit/{id}")
    public String updateAnunt(@PathVariable("id") int id,
                              @ModelAttribute("anunt") Anunturi anunt,
                              @RequestParam("serviciuId") int serviciuId,
                              @RequestParam("judet") String judet,
                              @RequestParam("localitate") String localitate) {

        AnunturiDto existingAnunt = anunturiService.findById(id);
        if (existingAnunt == null) {
            return "redirect:/anunturi";  // Sau afișează un mesaj de eroare
        }

        // Actualizează câmpurile anunțului
        existingAnunt.setNumeAnunt(anunt.getNumeAnunt());
        existingAnunt.setDescriereAnunt(anunt.getDescriereAnunt());
        existingAnunt.setNumarTelefon(anunt.getNumarTelefon());

        // Setează serviciul și localizarea
        Servicii serviciu = serviciiService.findById(serviciuId);
        existingAnunt.setServiciu(serviciu);

        // Update localizare
        Localitati loc = anunturiService.findLocalitateByJudetAndNume(judet, localitate);
        System.out.println("LOCALITATE POST PENTRU ID"  + loc.getId());
        if (loc != null) {
            existingAnunt.setLocalizare(String.valueOf(loc.getId()));
        }

        // Salvează modificările
        anunturiService.updateAnunt(id,existingAnunt);

        return "redirect:/anunturi";  // Redirecționează la lista de anunțuri
    }

    @GetMapping("/anunturi/view/{id}")
    public String viewAnunt(@PathVariable("id") int id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilizatori utilizator = new Utilizatori();
        String username = SecurityUtil.getSessionUser();
        if(username!= null){
            utilizator = utilizatorService.findByUsername(username);
        }
        AnunturiDto anunt = anunturiService.findById(id);
        if (anunt == null) {
            return "redirect:/anunturi";  // sau poți adăuga o pagină de eroare
        }
        model.addAttribute("utilizator", utilizator);
        model.addAttribute("anunt", anunt);
        return "vizualizareAnunt";  // numele template-ului Thymeleaf pentru vizualizare
    }
    @PostMapping("/anunturi/delete/{id}")
    public String deleteAnunt(@PathVariable("id") int id) {
        anunturiService.deleteAnuntWithReviews(id);
        return "redirect:/anunturi";  // Redirecționează către lista de anunțuri după ștergere
    }

    @GetMapping("/anunturi/review/{id}")
    public String showReviewForm(@PathVariable("id") Long anuntId, Model model) {
        model.addAttribute("anuntId", anuntId);
        return "review"; // Numele fișierului Thymeleaf care conține formularul
    }

    @PostMapping("/anunturi/review")
    public String saveReview(@RequestParam("anuntId") int anuntId,
                             @RequestParam("rating") int rating,
                             @RequestParam("comment") String comment) {
        String username = SecurityUtil.getSessionUser();
        Utilizatori utilizator = utilizatorService.findByUsername(username);
        AnunturiDto anunt = anunturiService.findById(anuntId);
        Reviewuri review = new Reviewuri();
        review.setStars(rating);
        review.setDescrierereview(comment);
        review.setIdanunt(anunt.getIdanunt());
        review.setIdUser(utilizator.getIduser());

        reviewuriRepository.save(review);

        return "redirect:/anunturi";// Redirecționează înapoi la pagina anunțului
    }

    @GetMapping("/search")
    public String searchAnunturi(@RequestParam(required = false) Integer service,
                                 @RequestParam(required = false) String location,
                                 @RequestParam(required = false) Double rating,
                                 Model model, @RequestParam(defaultValue = "0") int page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilizatori utilizator = new Utilizatori();
        String username = SecurityUtil.getSessionUser();
        List<Servicii> servicii = serviciiService.findAllServicii();
        if(username != null) {
            utilizator = utilizatorService.findByUsername(username);
        }

        Pageable pageable = PageRequest.of(page, 10);
        // Apelăm metoda de căutare din serviciu
        Page<AnunturiDto> filteredAnunturi = anunturiService.searchAnunturi(service, location, rating, pageable);

        List<Reviewuri> recenziiUtilizator = reviewuriRepository.findReviewuriByIdUser(utilizator.getIduser());
        Map<Integer, Reviewuri> recenziiAnunt = new HashMap<>();
        for (Reviewuri recenzie : recenziiUtilizator) {
            // Maparea recenziilor la anunțurile corespunzătoare
            recenziiAnunt.put(recenzie.getIdanunt(), recenzie); // Folosește ID-ul anunțului din recenzie
        }
        model.addAttribute("recenziiAnunt", recenziiAnunt); // Adăugăm recenziile în model

        // Adăugăm rezultatele căutării în model
        model.addAttribute("anunturiPage", filteredAnunturi);
        model.addAttribute("currentPage", page);
        model.addAttribute("serviciiFiltru", serviciiService.findAllServicii());
        model.addAttribute("judetFiltru", anunturiService.returnJudete());
        model.addAttribute("ratingFiltru", rating);

        return "anunturi";
    }
    @PostMapping("/anunturi/review/edit/{id}")
    public String editReview(@PathVariable("id") int id, @ModelAttribute("review") Reviewuri review, RedirectAttributes redirectAttributes) {
        // Caută recenzia existentă în baza de date
        Reviewuri existingReview;
        existingReview = anunturiService.findRecenziiById(Long.valueOf(id));

        if (existingReview != null) {
            // Actualizează valorile recenziei
            existingReview.setStars(review.getStars());
            existingReview.setDescrierereview(review.getDescrierereview());
            reviewuriRepository.save(existingReview);

            // Redirect cu mesaj de succes
            redirectAttributes.addFlashAttribute("success", "Recenzia a fost actualizată cu succes!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Recenzia nu a fost găsită!");
        }

        return "redirect:/anunturi";
    }
    @GetMapping("/anunturi/review/edit/{id}")
    public String showEditReviewForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        // Găsim recenzia existentă
        Reviewuri existingReview = anunturiService.findRecenziiById(id);

        if (existingReview != null) {
            // Adăugăm recenzia găsită la model pentru a o utiliza în formularul de editare
            model.addAttribute("review", existingReview);
            return "editReview"; // Returnează numele paginii de editare (ex. editReview.html)
        } else {
            // Dacă recenzia nu este găsită, trimitem un mesaj de eroare și redirecționăm la lista de anunțuri
            redirectAttributes.addFlashAttribute("error", "Recenzia nu a fost găsită!");
            return "redirect:/anunturi";
        }
    }
}

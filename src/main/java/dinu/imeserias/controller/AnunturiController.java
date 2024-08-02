package dinu.imeserias.controller;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.model.Anunturi;
import dinu.imeserias.model.Servicii;
import dinu.imeserias.model.Utilizatori;
import dinu.imeserias.security.SecurityUtil;
import dinu.imeserias.service.AnunturiService;
import dinu.imeserias.service.ServiciiService;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AnunturiController {
    private AnunturiService anunturiService;
    private ServiciiService serviciiService;
    private UtilizatorService utilizatorService;
    @Autowired
    public AnunturiController(AnunturiService anunturiService, UtilizatorService utilizatorService) {
        this.anunturiService = anunturiService;
        this.serviciiService = serviciiService;
        this.utilizatorService = utilizatorService;
    }
    @GetMapping("/servicii")
    @ResponseBody
    public List<String> getServicii() {
        return anunturiService.returnServicii();
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
    public String listAnunturi(Model model){
        Utilizatori utilizator = new Utilizatori();
        List<AnunturiDto> anunturi = anunturiService.findAllAnunturi();
        String username = SecurityUtil.getSessionUser();
        if(username!= null){
            utilizator = utilizatorService.findByUsername(username);
        }
        model.addAttribute("utilizator", utilizator);
        model.addAttribute("anunturi", anunturi);
        return "anunturi";
    }

    @GetMapping("/anunturi/new")
    public String createAnuntForm(Model model){
        Anunturi anunturi = new Anunturi();
        model.addAttribute("anunt", anunturi);
        return "adaugareAnunt";
    }

    @PostMapping("/anunturi/new")
    public String createAnunt(@ModelAttribute("anunt") Anunturi anunturi,@RequestParam("serviciiIds") String serviciiIds, @RequestParam("autocompleteLocalitate") String localitate){
        Set<Servicii> serviciiSet = new HashSet<>();
        for (String id : serviciiIds.split(",")) {
            Servicii serviciu = serviciiService.findById(Integer.parseInt(id));
            serviciiSet.add(serviciu);
        }
        anunturi.setServicii(serviciiSet);
        anunturiService.saveAnunt(anunturi, localitate);
        return "redirect:/anunturi";
    }

}

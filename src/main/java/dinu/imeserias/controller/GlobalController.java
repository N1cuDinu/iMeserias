package dinu.imeserias.controller;

import dinu.imeserias.model.Anunturi;
import dinu.imeserias.model.Utilizatori;
import dinu.imeserias.service.AnunturiService;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {
    UtilizatorService utilizatorService;
    AnunturiService anunturiService;
    @Autowired
    public GlobalController(UtilizatorService utilizatorService, AnunturiService anunturiService) {
        this.utilizatorService = utilizatorService;
        this.anunturiService = anunturiService;
    }

    @ModelAttribute
    public void populateNavbar(Model model) {
        System.out.println("Accesata metoda NAVBAR");
        // Obține utilizatorul curent
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // Obține numele utilizatorului logat
        System.out.println(username);
        // Verifică dacă utilizatorul este autentificat
        if (username != null && !username.equals("anonymousUser")) {
            Utilizatori utilizator = utilizatorService.findByUsername(username);

            // Verifică dacă utilizatorul este meseriaș și dacă are un anunț
            boolean anuntExist = false;
            int anuntId = 0;
            if (utilizator != null && (utilizator.getTipUtilizator().equals("MESERIAS") || utilizator.getTipUtilizator().equals("ADMIN"))) {
                Anunturi anunt = anunturiService.findAnuntByUserId(utilizator.getIduser());
                if (anunt != null) {
                    anuntExist = true;
                    anuntId = anunt.getIdanunt();
                }
            }
            System.out.println(anuntExist);
            System.out.println(anuntId);
            System.out.println(utilizator);
            // Adaugă variabilele necesare în model
            model.addAttribute("anuntExistNavbar", anuntExist);
            model.addAttribute("anuntIdNavbar", anuntId);
            model.addAttribute("utilizatorNavbar", utilizator);
        }
    }

    @GetMapping("/")
    public String returnIndex(Model model) {
        return "index";
    }
}

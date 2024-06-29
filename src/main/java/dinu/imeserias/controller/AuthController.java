package dinu.imeserias.controller;

import ch.qos.logback.classic.Logger;
import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.enums.RoluriEnum;
import dinu.imeserias.model.Utilizatori;
import dinu.imeserias.service.UtilizatorService;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class AuthController {
    private UtilizatorService utilizatorService;
    public AuthController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("roles", utilizatorService.roluri());
        model.addAttribute("user",user);
        return "register";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user, BindingResult result, Model model){

        Utilizatori mailUtilizatorExistent = utilizatorService.findByEmail(user.getEmail());
        if(mailUtilizatorExistent != null) {
            return "redirect:/register?fail=email";
        }
        Utilizatori usernameUtilizatorExistent = utilizatorService.findByUsername(user.getUsername());
        if(usernameUtilizatorExistent != null) {
            return "redirect:/register?fail=username";
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        utilizatorService.saveUser(user);

        System.out.println("Attempting to register user: " + user.getUsername());
        return "redirect:/login";
    }
}

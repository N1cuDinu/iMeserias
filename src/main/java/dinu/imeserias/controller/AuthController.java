package dinu.imeserias.controller;

import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.model.Utilizator;
import dinu.imeserias.service.UtilizatorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UtilizatorService utilizatorService;

    public AuthController(UtilizatorService utilizatorService) {
        this.utilizatorService = utilizatorService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user, BindingResult result, Model model){
        Utilizator mailUtilizatorExistent = utilizatorService.findByEmail(user.getEmail());
        if(mailUtilizatorExistent != null && mailUtilizatorExistent.getEmail() != null && !mailUtilizatorExistent.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }
        Utilizator usernameUtilizatorExistent = utilizatorService.findByUsername(user.getUsername());
        if(usernameUtilizatorExistent != null && usernameUtilizatorExistent.getUsername() != null && !usernameUtilizatorExistent.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        utilizatorService.saveUser(user);
        return "redirect:/anunturi?success";
    }
}

package dinu.imeserias.controller;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.service.AnunturiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AnunturiController {
    private AnunturiService anunturiService;

    @Autowired
    public AnunturiController(AnunturiService anunturiService) {
        this.anunturiService = anunturiService;
    }

    @GetMapping("/anunturi")
    public String listAnunturi(Model model){
        List<AnunturiDto> anunturi = anunturiService.findAllAnunturi();
        model.addAttribute("anunturi", anunturi);
        return "anunturi";
    }
}

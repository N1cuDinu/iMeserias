package dinu.imeserias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dinu.imeserias.service.AdminService;
import dinu.imeserias.model.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Afișează pagina principală a Admin Panel-ului
    @GetMapping
    public String adminHome() {
        return "admin/adminHome"; // Redirect către pagina principală de administrare
    }

    // Gestionare Servicii
    @GetMapping("/servicii")
    public String listServicii(Model model) {
        List<Servicii> servicii = adminService.findAllServicii();
        model.addAttribute("servicii", servicii);
        return "admin/servicii";
    }

    @PostMapping("/servicii/add")
    public String addServiciu(@RequestParam("numeServiciu") String numeServiciu) {
        adminService.addServiciu(numeServiciu);
        return "redirect:/admin/servicii";
    }

    @PostMapping("/servicii/delete/{id}")
    public String deleteServiciu(@PathVariable("id") int id) {
        adminService.deleteServiciu(id);
        return "redirect:/admin/servicii";
    }

    // Gestionare Utilizatori
    @GetMapping("/utilizatori")
    public String listUtilizatori(Model model) {
        List<Utilizatori> utilizatori = adminService.findAllUtilizatori();
        model.addAttribute("utilizatori", utilizatori);
        return "admin/utilizatori";
    }

    @PostMapping("/utilizatori/delete/{id}")
    public String deleteUtilizator(@PathVariable("id") int id) {
        adminService.deleteUtilizator(id);
        return "redirect:/admin/utilizatori";
    }

    @PostMapping("/utilizatori/update-role/{id}")
    public String updateRole(@PathVariable("id") int id, @RequestParam("rol") String rol) {
        adminService.updateRole(id, rol);
        return "redirect:/admin/utilizatori";
    }

    // Gestionare Anunțuri
    @GetMapping("/anunturi")
    public String listAnunturi(Model model) {
        List<Anunturi> anunturi = adminService.findAllAnunturi();
        model.addAttribute("anunturi", anunturi);
        return "admin/anunturi";
    }

    @PostMapping("/anunturi/delete/{id}")
    public String deleteAnunt(@PathVariable("id") int id) {
        adminService.deleteAnunt(id);
        return "redirect:/admin/anunturi";
    }
}

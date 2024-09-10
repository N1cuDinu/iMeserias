package dinu.imeserias.service.impl;

import dinu.imeserias.model.*;
import dinu.imeserias.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dinu.imeserias.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ServiciiRepository serviciiRepository;

    @Autowired
    private UtilizatoriRepository utilizatoriRepository;

    @Autowired
    private AnunturiRepository anuntRepository;
    @Autowired
    private ReviewuriRepository reviewuriRepository;

    // Metode pentru Servicii
    public List<Servicii> findAllServicii() {
        return serviciiRepository.findAll();
    }

    public void addServiciu(String numeServiciu) {
        Servicii serviciu = new Servicii();
        serviciu.setNumeServiciu(numeServiciu);
        serviciiRepository.save(serviciu);
    }

    public void deleteServiciu(int id) {
        serviciiRepository.deleteById(id);
    }

    // Metode pentru Utilizatori
    public List<Utilizatori> findAllUtilizatori() {
        return utilizatoriRepository.findAll();
    }

    public void deleteUtilizator(int id) {
        utilizatoriRepository.deleteById((long) id);
    }

    public void updateRole(int id, String rol) {
        Utilizatori utilizator = utilizatoriRepository.findById((long) id).orElseThrow();
        utilizator.setTipUtilizator(rol);
        utilizatoriRepository.save(utilizator);
    }

    // Metode pentru Anunțuri
    public List<Anunturi> findAllAnunturi() {
        return anuntRepository.findAll();
    }

    public void deleteAnunt(int id) {
        anuntRepository.deleteById((long) id);
    }

}

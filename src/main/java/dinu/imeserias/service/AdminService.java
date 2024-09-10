package dinu.imeserias.service;

import dinu.imeserias.model.Anunturi;
import dinu.imeserias.model.Servicii;
import dinu.imeserias.model.Utilizatori;

import java.util.List;

public interface AdminService {
    List<Servicii> findAllServicii();
    void addServiciu(String numeServiciu);
    void deleteServiciu(int id);
    List<Utilizatori> findAllUtilizatori();
    void deleteUtilizator(int id);
    void updateRole(int id, String rol);
    List<Anunturi> findAllAnunturi();
    void deleteAnunt(int id);
}

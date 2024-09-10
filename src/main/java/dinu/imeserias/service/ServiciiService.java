package dinu.imeserias.service;

import dinu.imeserias.model.Servicii;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiciiService {
    List<String> findAllServiciiNames();
    Servicii findById(int id);
    List<Servicii> findAllServicii();
}

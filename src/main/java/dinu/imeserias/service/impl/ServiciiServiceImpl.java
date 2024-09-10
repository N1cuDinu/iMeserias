package dinu.imeserias.service.impl;

import dinu.imeserias.model.Servicii;
import dinu.imeserias.repository.ServiciiRepository;
import dinu.imeserias.service.ServiciiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiciiServiceImpl implements ServiciiService {
    private ServiciiRepository serviciiRepository;

    @Autowired
    public ServiciiServiceImpl(ServiciiRepository serviciiRepository) {
        this.serviciiRepository = serviciiRepository;
    }

    @Override
    public List<String> findAllServiciiNames() {
        return serviciiRepository.findAll().stream().map(Servicii::getNumeServiciu).collect(Collectors.toList());
    }

    public List<Servicii> findAllServiciu(){
        return serviciiRepository.findAll();
    }

    @Override
    public Servicii findById(int id) {
        return serviciiRepository.findById(id).orElse(null);
    }

    @Override
    public List<Servicii> findAllServicii() {
        return serviciiRepository.findAll();  // Apelează repository-ul pentru a obține toate serviciile
    }

}

package dinu.imeserias.service.impl;

import dinu.imeserias.model.Localitati;
import dinu.imeserias.model.Servicii;
import dinu.imeserias.repository.LocalitatiRepository;
import dinu.imeserias.service.LocalitatiService;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalitatiServiceImpl implements LocalitatiService {
    private final LocalitatiRepository localitatiRepository;
    @Autowired
    public LocalitatiServiceImpl(LocalitatiRepository localitatiRepository) {
        this.localitatiRepository = localitatiRepository;
    }

    @Override
    public Localitati findById(int id) {
        return localitatiRepository.findById((long) id).orElse(null);
    }
}

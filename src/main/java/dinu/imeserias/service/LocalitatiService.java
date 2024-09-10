package dinu.imeserias.service;

import dinu.imeserias.model.Localitati;
import dinu.imeserias.repository.LocalitatiRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;

@Service
public interface LocalitatiService {
    Localitati findById(int id);

}

package dinu.imeserias.service;

import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.model.Utilizator;

public interface UtilizatorService {
    void saveUser(RegistrationDto registrationDto);
    Utilizator findByEmail(String email);

    Utilizator findByUsername(String username);
}

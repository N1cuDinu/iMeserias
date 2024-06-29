package dinu.imeserias.service;

import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.model.Utilizatori;

import java.util.List;

public interface UtilizatorService {
    void saveUser(RegistrationDto registrationDto);
    Utilizatori findByEmail(String email);

    Utilizatori findByUsername(String username);

    List<String> roluri();
}

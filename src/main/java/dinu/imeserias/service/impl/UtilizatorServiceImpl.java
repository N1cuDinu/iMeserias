package dinu.imeserias.service.impl;

import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.model.Rol;
import dinu.imeserias.model.Utilizator;
import dinu.imeserias.repository.RolRepository;
import dinu.imeserias.repository.UtilizatoriRepository;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilizatorServiceImpl implements UtilizatorService {
    private UtilizatoriRepository utilizatoriRepository;
    private RolRepository rolRepository;
    @Autowired
    public UtilizatorServiceImpl(UtilizatoriRepository utilizatoriRepository, RolRepository rolRepository) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        Utilizator user = new Utilizator();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Rol rol = rolRepository.getRolByNumeRol("USER");
        user.setIdRol(rol.getIdrol());
        utilizatoriRepository.save(user);
    }

    @Override
    public Utilizator findByEmail(String email) {
        return utilizatoriRepository.findByEmail(email);
    }

    @Override
    public Utilizator findByUsername(String username) {
        return utilizatoriRepository.findByUsername(username);
    }
}

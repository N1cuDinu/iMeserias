package dinu.imeserias.service.impl;

import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.enums.RoluriEnum;
import dinu.imeserias.helpers.TimeHelper;
import dinu.imeserias.model.Utilizatori;
import dinu.imeserias.repository.UtilizatoriRepository;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class UtilizatorServiceImpl implements UtilizatorService {
    private UtilizatoriRepository utilizatoriRepository;
    private PasswordEncoder passwordEncoder;
    private TimeHelper timeHelper;

    @Autowired
    public UtilizatorServiceImpl(UtilizatoriRepository utilizatoriRepository, PasswordEncoder passwordEncoder, TimeHelper timeHelper) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.passwordEncoder = passwordEncoder;
        this.timeHelper = timeHelper;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        Utilizatori user = new Utilizatori();
        user.setTipUtilizator(registrationDto.getTipCont());
        user.setEmail(registrationDto.getEmail());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword())); // Criptarea parolei
        user.setDataSignUp(timeHelper.getActualTime());
        utilizatoriRepository.save(user);
    }

    @Override
    public Utilizatori findByEmail(String email) {
        return utilizatoriRepository.findByEmail(email);
    }

    @Override
    public Utilizatori findByUsername(String username) {
        return utilizatoriRepository.findByUsername(username);
    }

    @Override
    public List<String> roluri() {
        RoluriEnum[] roluri = RoluriEnum.values();
        List<String> listRoluri = new ArrayList<>();
        for (RoluriEnum roluriEnum : Arrays.stream(roluri).toList()) {
            if (!roluriEnum.getNumeRol().equals("ADMIN")) {
                listRoluri.add(roluriEnum.getNumeRol());
            }
        }
        return listRoluri;
    }
}

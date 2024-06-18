package dinu.imeserias.service.impl;

import dinu.imeserias.dto.RegistrationDto;
import dinu.imeserias.model.Rol;
import dinu.imeserias.model.Utilizator;
import dinu.imeserias.repository.RolRepository;
import dinu.imeserias.repository.UtilizatoriRepository;
import dinu.imeserias.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class UtilizatorServiceImpl implements UtilizatorService {
    private UtilizatoriRepository utilizatoriRepository;
    private RolRepository rolRepository;
    @Autowired
    public UtilizatorServiceImpl(UtilizatoriRepository utilizatoriRepository, RolRepository rolRepository) {
        this.utilizatoriRepository = utilizatoriRepository;
        this.rolRepository = rolRepository;
    }

    public Timestamp getActualTime(){
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
        return timestamp;
    }
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        Utilizator user = new Utilizator();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        user.setTipCont(registrationDto.getTipCont());
        user.setDataSignUp(getActualTime());
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

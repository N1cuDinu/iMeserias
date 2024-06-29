package dinu.imeserias.service.impl;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.helpers.TimeHelper;
import dinu.imeserias.model.Anunturi;
import dinu.imeserias.model.Localitati;
import dinu.imeserias.model.Servicii;
import dinu.imeserias.model.Utilizatori;
import dinu.imeserias.repository.AnunturiRepository;
import dinu.imeserias.repository.LocalitatiRepository;
import dinu.imeserias.repository.ServiciiRepository;
import dinu.imeserias.repository.UtilizatoriRepository;
import dinu.imeserias.security.SecurityUtil;
import dinu.imeserias.service.AnunturiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnunturiServiceImpl implements AnunturiService {
    TimeHelper timeHelper;
    private final AnunturiRepository anunturiRepository;
    private final UtilizatoriRepository utilizatoriRepository;
    private final ServiciiRepository serviciiRepository;
    private final LocalitatiRepository localitatiRepository;
    @Autowired
    public AnunturiServiceImpl(AnunturiRepository anunturiRepository, UtilizatoriRepository utilizatoriRepository, ServiciiRepository serviciiRepository, LocalitatiRepository localitatiRepository){
        this.anunturiRepository = anunturiRepository;
        this.utilizatoriRepository = utilizatoriRepository;
        this.serviciiRepository = serviciiRepository;
        this.localitatiRepository = localitatiRepository;
    }
    @Override
    public List<AnunturiDto> findAllAnunturi() {
        List<Anunturi> anunturi = anunturiRepository.findAll();
        return anunturi.stream().map(this::mapToAnunturiDto).collect(Collectors.toList());
    }

    @Override
    public Anunturi saveAnunt(Anunturi anunturi, String localitate) {
        String username = SecurityUtil.getSessionUser();
        Utilizatori utilizator = utilizatoriRepository.findByUsername(username);
        Localitati localitati = localitatiRepository.findLocalitatiByNume(localitate);
        anunturi.setIduser(utilizator.getIduser());
        anunturi.setLocalizare(localitati.getId());
        anunturi.setDataAdaugare(timeHelper.getActualTime());
        anunturi.setDataActualizare(timeHelper.getActualTime());
        return anunturiRepository.save(anunturi);
    }
    @Override
    public List<String> returnJudete() {
        return localitatiRepository.getJudete();
    }
    @Override
    public List<String> returnLocalitateByJudete(String judet) {
        return localitatiRepository.getLocalitatiByJudet(judet);
    }
    @Override
    public List<String> returnServicii() {
        List<Servicii> servicii = serviciiRepository.findAll();
        List<String> numeServicii = new ArrayList<>();
        for (Servicii serviciu : servicii) {
            numeServicii.add(serviciu.getNumeServiciu());
        }
        return numeServicii;
    }
    private AnunturiDto mapToAnunturiDto(Anunturi anunturi){
        AnunturiDto anuntDto = AnunturiDto.builder()
                .idanunt(anunturi.getIdanunt())
                .iduser(anunturi.getIduser())
                .numeAnunt(anunturi.getNumeAnunt())
                .localizare(anunturi.getLocalizare())
                .dataActualizare(anunturi.getDataActualizare())
                .dataAdaugare(anunturi.getDataAdaugare())
                .descriereAnunt(anunturi.getDescriereAnunt())
                .servicii(anunturi.getServicii().stream().map(Servicii::getNumeServiciu).collect(Collectors.toSet()))
                .numarTelefon(anunturi.getNumarTelefon())
                .build();
        return anuntDto;
    }




}

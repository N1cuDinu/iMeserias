package dinu.imeserias.service.impl;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.model.Anunturi;
import dinu.imeserias.repository.AnunturiRepository;
import dinu.imeserias.service.AnunturiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnunturiServiceImpl implements AnunturiService {
    private final AnunturiRepository anunturiRepository;
    @Autowired
    public AnunturiServiceImpl(AnunturiRepository anunturiRepository){
        this.anunturiRepository = anunturiRepository;
    }
    @Override
    public List<AnunturiDto> findAllAnunturi() {
        List<Anunturi> anunturi = anunturiRepository.findAll();
        return anunturi.stream().map(this::mapToAnunturiDto).collect(Collectors.toList());
    }

    private AnunturiDto mapToAnunturiDto(Anunturi anunt){
        AnunturiDto anuntDto = AnunturiDto.builder()
                .idanunt(anunt.getIdanunt())
                .iduser(anunt.getIduser())
                .numeAnunt(anunt.getNumeAnunt())
                .localizare(anunt.getLocalizare())
                .dataActualizare(anunt.getDataActualizare())
                .dataAdaugare(anunt.getDataAdaugare())
                .descriereAnunt(anunt.getDescriereAnunt())
                .idserviciu(anunt.getIdserviciu())
                .numarTelefon(anunt.getNumarTelefon())
                .build();
        return anuntDto;
    }
}

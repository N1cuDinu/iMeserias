package dinu.imeserias.service;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.model.Anunturi;
import dinu.imeserias.model.Localitati;
import dinu.imeserias.model.Servicii;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AnunturiService {
    List<AnunturiDto> findAllAnunturi();
    Anunturi saveAnunt(Anunturi anunturi, String localitate);
    List<String> returnJudete();
    List<String> returnLocalitateByJudete(String judet);
    List<String> returnServicii();
}

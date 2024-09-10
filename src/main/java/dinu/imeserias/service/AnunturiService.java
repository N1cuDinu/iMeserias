package dinu.imeserias.service;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.model.Anunturi;
import dinu.imeserias.model.Localitati;
import dinu.imeserias.model.Reviewuri;
import dinu.imeserias.model.Servicii;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AnunturiService {
    Page<AnunturiDto> findAllAnunturi(Pageable pageable);
    Anunturi saveAnunt(Anunturi anunturi, String localitate);
    List<String> returnJudete();
    List<String> returnLocalitateByJudete(String judet);
    List<Servicii> returnServicii();
    AnunturiDto findById(int id);
    Anunturi updateAnunt(int id, AnunturiDto anunturi);
    Anunturi findAnuntByUserId(int userId);
    Localitati findLocalitateByJudetAndNume(String judet, String nume);
    void deleteAnunt(int id);
    Page<AnunturiDto> searchAnunturi(Integer service, String location, Double rating, Pageable pageable);
    void deleteAnuntWithReviews(int anuntId);
    Localitati findLocalitatiByJudet(String judet);
    Reviewuri findRecenziiById(Long id);
    void saveReview(Reviewuri review);
}

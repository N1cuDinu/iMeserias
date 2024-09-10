package dinu.imeserias.service.impl;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.helpers.TimeHelper;
import dinu.imeserias.model.*;
import dinu.imeserias.repository.*;
import dinu.imeserias.security.SecurityUtil;
import dinu.imeserias.service.AnunturiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnunturiServiceImpl implements AnunturiService {
    private final TimeHelper timeHelper;
    private final AnunturiRepository anunturiRepository;
    private final UtilizatoriRepository utilizatoriRepository;
    private final ServiciiRepository serviciiRepository;
    private final LocalitatiRepository localitatiRepository;
    private final ReviewuriRepository reviewuriRepository;
    @Autowired
    public AnunturiServiceImpl(AnunturiRepository anunturiRepository, UtilizatoriRepository utilizatoriRepository, ServiciiRepository serviciiRepository, LocalitatiRepository localitatiRepository, TimeHelper timeHelper, ReviewuriRepository reviewuriRepository){
        this.anunturiRepository = anunturiRepository;
        this.utilizatoriRepository = utilizatoriRepository;
        this.serviciiRepository = serviciiRepository;
        this.localitatiRepository = localitatiRepository;
        this.timeHelper = timeHelper;
        this.reviewuriRepository = reviewuriRepository;
    }
    @Override
    public Page<AnunturiDto> findAllAnunturi(org.springframework.data.domain.Pageable pageable) {
        return anunturiRepository.findAll(pageable).map(this::mapToAnunturiDto);
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
    public List<Servicii> returnServicii() {
        List<Servicii> servicii = serviciiRepository.findAll();
        return servicii;
    }
    @Override
    public AnunturiDto findById(int id) {
        Anunturi anunt =  anunturiRepository.findAnunturiByIdanunt(id);
        return mapToAnunturiDto(anunt);
    }
    @Override
    public Anunturi updateAnunt(int id, AnunturiDto anunt) {
        // Preia serviciul și localitatea din baza de date
        // Găsește anunțul existent după ID
        Anunturi existingAnunt = anunturiRepository.findById((long) id).orElse(null);
        if (existingAnunt == null) {
            throw new EntityNotFoundException("Anunțul cu ID-ul " + id + " nu a fost găsit.");
        }
        // Actualizează câmpurile anunțului
        existingAnunt.setNumeAnunt(anunt.getNumeAnunt());
        existingAnunt.setDescriereAnunt(anunt.getDescriereAnunt());
        existingAnunt.setNumarTelefon(anunt.getNumarTelefon());
        existingAnunt.setServiciu(anunt.getServiciu());
        existingAnunt.setLocalizare(Integer.parseInt(anunt.getLocalizare()));
        // Salvează modificările
        return anunturiRepository.save(existingAnunt);
    }

    @Override
    public Anunturi findAnuntByUserId(int userId) {
        return anunturiRepository.findAnunturiByIduser(userId);
    }

    @Override
    public Localitati findLocalitateByJudetAndNume(String judet, String nume) {
        return localitatiRepository.findLocalitatiByJudetAndNume(judet, nume);
    }

    @Override
    public void deleteAnunt(int id) {
        anunturiRepository.deleteById((long) id);
    }

    @Override
    public Page<AnunturiDto> searchAnunturi(Integer service, String location, Double rating, Pageable pageable) {
        // Construiește criteriile de căutare
        Page<Anunturi> anunturiPage = anunturiRepository.searchAnunturiByCriteria(service, location, rating, pageable);

        // Mapează rezultatele la DTO-uri
        List<AnunturiDto> anunturiDtoList = anunturiPage.getContent()
                .stream()
                .map(this::mapToAnunturiDto)
                .collect(Collectors.toList());

        return new PageImpl<>(anunturiDtoList, pageable, anunturiPage.getTotalElements());
    }



    private AnunturiDto mapToAnunturiDto(Anunturi anunturi){
        Localitati localitati = localitatiRepository.findById(anunturi.getLocalizare()).orElse(null);
        String localizare = localitati != null ? localitati.getNume() + ", " + localitati.getJudet() : "Unknown Location";

        Utilizatori utilizator = utilizatoriRepository.findById((long) anunturi.getIduser()).orElse(null);
        String numeUtilizator = utilizator != null ? utilizator.getUsername() : "Unknown User";
        Double ratingMedie = reviewuriRepository.findAverageRatingByAnuntId(anunturi.getIdanunt());
        if(ratingMedie == null){
            ratingMedie=0.0d;
        }
        int numarRecenzii = reviewuriRepository.findNumberOfReviewsPerAnunt(anunturi.getIdanunt());
        AnunturiDto anuntDto = AnunturiDto.builder()
                .idanunt(anunturi.getIdanunt())
                .iduser(anunturi.getIduser())
                .numeAnunt(anunturi.getNumeAnunt())
                .localizare(localizare)
                .dataActualizare(anunturi.getDataActualizare())
                .dataAdaugare(anunturi.getDataAdaugare())
                .descriereAnunt(anunturi.getDescriereAnunt())
                .serviciu(anunturi.getServicii())
                .numarTelefon(anunturi.getNumarTelefon())
                .numeUtilizator(numeUtilizator)  // populatează numele utilizatorului
                .ratingMedie(ratingMedie)
                .numarRecenzii(numarRecenzii)
                .build();
        return anuntDto;
    }

    @Transactional // Asigură o tranzacție pentru metoda de ștergere
    public void deleteAnuntWithReviews(int anuntId) {
        // Șterge toate recenziile asociate cu anunțul
        reviewuriRepository.deleteByIdanunt(anuntId);

        // Șterge anunțul
        anunturiRepository.deleteById((long) anuntId);
    }

    @Override
    public Localitati findLocalitatiByJudet(String judet) {
        return null;
    }

    public Reviewuri findRecenziiById(Long id) {
        Optional<Reviewuri> optionalReview = reviewuriRepository.findById((long) id.intValue());

        // Verificăm dacă recenzia există și returnăm obiectul găsit, altfel returnăm null
        return optionalReview.orElse(null);
    }

    public void saveReview(Reviewuri review) {
        reviewuriRepository.save(review);
    }
}

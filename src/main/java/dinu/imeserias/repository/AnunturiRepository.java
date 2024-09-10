package dinu.imeserias.repository;

import dinu.imeserias.dto.AnunturiDto;
import dinu.imeserias.model.Anunturi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnunturiRepository extends JpaRepository<Anunturi, Long> {
    Anunturi findAnunturiByIdanunt(int Idanunt);
    Anunturi findAnunturiByIduser(int Iduser);


    @Query("SELECT a FROM Anunturi a " +
            "JOIN a.serviciu s " +
            "JOIN Localitati l ON a.localizare = l.id " +
            "LEFT JOIN Reviewuri r ON r.idanunt = a.idanunt " +
            "WHERE " +
            "(:service IS NULL OR s.idserviciu = :service) AND " +
            "(:judet IS NULL OR l.judet = :judet) " +
            "GROUP BY a.idanunt, a.numeAnunt, a.descriereAnunt, s.numeServiciu, l.nume, l.judet " +
            "HAVING (:rating IS NULL OR AVG(COALESCE(r.stars, 0)) >= :rating)")
    Page<Anunturi> searchAnunturiByCriteria(
            @Param("service") Integer service,
            @Param("judet") String judet,
            @Param("rating") Double rating,
            Pageable pageable);
}


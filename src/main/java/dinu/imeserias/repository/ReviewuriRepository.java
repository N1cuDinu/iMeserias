package dinu.imeserias.repository;

import dinu.imeserias.model.Reviewuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewuriRepository extends JpaRepository<Reviewuri, Long> {
    @Query("SELECT AVG(r.stars) FROM Reviewuri r WHERE r.idanunt = :anuntId")
    Double findAverageRatingByAnuntId(@Param("anuntId") int anuntId);
    @Query("SELECT count (r.stars) FROM Reviewuri r WHERE r.idanunt = :anuntId")

    int findNumberOfReviewsPerAnunt(@Param("anuntId")int anuntId);
    List<Reviewuri> findReviewuriByIdUser(int idUser);
    void deleteByIdanunt(int anundId);
}

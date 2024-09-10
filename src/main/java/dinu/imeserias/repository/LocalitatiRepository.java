package dinu.imeserias.repository;

import dinu.imeserias.model.Localitati;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalitatiRepository extends JpaRepository<Localitati, Long> {
    @Query(value = "select distinct judet from Localitati")
    List<String> getJudete();

    @Query(value = "select distinct nume from Localitati where judet=?1", nativeQuery = true)
    List<String> getLocalitatiByJudet(String judet);

    Localitati findLocalitatiByNume(String nume);
    Localitati findLocalitatiByJudetAndNume(String judet, String nume);
}

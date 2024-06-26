package dinu.imeserias.repository;

import dinu.imeserias.model.Reviewuri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewuriRepository extends JpaRepository<Reviewuri, Long> {
}

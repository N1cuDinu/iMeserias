package dinu.imeserias.repository;

import dinu.imeserias.model.Reporturi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporturiRepository extends JpaRepository<Reporturi, Long> {
}

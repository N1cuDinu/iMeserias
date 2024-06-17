package dinu.imeserias.repository;

import dinu.imeserias.model.Anunturi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnunturiRepository extends JpaRepository<Anunturi, Long> {
}

package dinu.imeserias.repository;

import dinu.imeserias.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizatoriRepository extends JpaRepository<Utilizator, Long> {
    Utilizator findByEmail(String email);
    Utilizator findByUsername(String username);

}

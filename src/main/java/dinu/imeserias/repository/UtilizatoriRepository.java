package dinu.imeserias.repository;

import dinu.imeserias.model.Utilizatori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizatoriRepository extends JpaRepository<Utilizatori, Long> {
    Utilizatori findByEmail(String email);
    Utilizatori findByUsername(String username);
    Utilizatori findFirstByUsername(String username);

}

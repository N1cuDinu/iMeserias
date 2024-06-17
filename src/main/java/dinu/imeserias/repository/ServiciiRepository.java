package dinu.imeserias.repository;

import dinu.imeserias.model.Servicii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiciiRepository extends JpaRepository<Servicii, Long> {
}

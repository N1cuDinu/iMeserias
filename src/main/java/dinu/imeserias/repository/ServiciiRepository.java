package dinu.imeserias.repository;

import dinu.imeserias.model.Servicii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiciiRepository extends JpaRepository<Servicii, Integer> {

}

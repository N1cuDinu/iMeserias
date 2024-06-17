package dinu.imeserias.repository;

import dinu.imeserias.model.Statistici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticiRepository extends JpaRepository<Statistici,Long> {
}

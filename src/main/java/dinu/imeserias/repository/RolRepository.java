package dinu.imeserias.repository;

import dinu.imeserias.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol getRolByNumeRol(String numeRol);
}

package esfe.asistencia.repositorios;

import esfe.asistencia.entidades.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocenteRepository extends JpaRepository<Docente, Integer> {
}

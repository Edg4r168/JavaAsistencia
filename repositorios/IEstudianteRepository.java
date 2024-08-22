package esfe.asistencia.repositorios;

import esfe.asistencia.entidades.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteRepository extends JpaRepository<Estudiante, Integer> {
}

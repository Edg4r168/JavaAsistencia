package esfe.asistencia.repositorios;

import esfe.asistencia.entidades.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGrupoRepository extends JpaRepository<Grupo, Integer> {
}

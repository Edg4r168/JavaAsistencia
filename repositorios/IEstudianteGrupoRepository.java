package esfe.asistencia.repositorios;

import esfe.asistencia.entidades.EstudianteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteGrupoRepository extends JpaRepository<EstudianteGrupo, Integer> {
    Page<EstudianteGrupo> findByOrderByEstudianteNameDesc(Pageable pageable);
}

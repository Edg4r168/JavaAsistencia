package esfe.asistencia.servicios.interfaces;

import esfe.asistencia.entidades.DocenteGrupo;
import esfe.asistencia.entidades.EstudianteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEstudianteGrupoService {
    Page<EstudianteGrupo> searchPaginated(Pageable pageable);

    List<EstudianteGrupo> getAll();

    Optional<EstudianteGrupo> getById(int id);

    EstudianteGrupo CreateOrEdit(EstudianteGrupo estudianteGrupo);

    void deleteById(Integer id);
}

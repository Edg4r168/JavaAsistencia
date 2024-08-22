package esfe.asistencia.servicios.interfaces;

import esfe.asistencia.entidades.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEstudianteService {
    Page<Estudiante> searchPaginated(Pageable pageable);

    List<Estudiante> getAll();

    Optional<Estudiante> getById(int id);

    Estudiante CreateOrEdit(Estudiante estudiante);

    void deleteById(Integer id);
}

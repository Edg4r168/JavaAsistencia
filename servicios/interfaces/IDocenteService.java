package esfe.asistencia.servicios.interfaces;

import esfe.asistencia.entidades.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDocenteService {
    Page<Docente> searchPaginated(Pageable pageable);

    List<Docente> getAll();

    Optional<Docente> getById(int id);

    Docente CreateOrEdit(Docente docente);

    void deleteById(Integer id);
}

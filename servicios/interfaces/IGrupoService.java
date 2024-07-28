package esfe.asistencia.servicios.interfaces;

import esfe.asistencia.entidades.Grupo;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

public interface IGrupoService {
    Page<Grupo> searchPaginated(Pageable pageable);

    List<Grupo> getAll();

    Optional<Grupo> getById(int id);

    Grupo CreateOrEdit(Grupo grupo);

    void deleteById(Integer id);
}

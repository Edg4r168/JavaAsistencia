package esfe.asistencia.servicios.interfaces;

import esfe.asistencia.entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IDocenteGrupoService {
    Page<DocenteGrupo> searchPaginated(Pageable pageable);

    List<DocenteGrupo> getAll();

    Optional<DocenteGrupo> getById(int id);

    DocenteGrupo CreateOrEdit(DocenteGrupo docenteGrupo);

    void deleteById(Integer id);
}

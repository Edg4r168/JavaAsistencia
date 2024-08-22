package esfe.asistencia.servicios.implementaciones;

import esfe.asistencia.entidades.DocenteGrupo;
import esfe.asistencia.repositorios.IDocenteGrupoRepository;
import esfe.asistencia.servicios.interfaces.IDocenteGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteGrupoService implements IDocenteGrupoService {
    @Autowired
    private IDocenteGrupoRepository _docenteGrupoRepository;

    @Override
    public Page<DocenteGrupo> searchPaginated(Pageable pageable) {
        return _docenteGrupoRepository.findByOrderTeacherDesc(pageable);
    }

    @Override
    public List<DocenteGrupo> getAll() {
        return _docenteGrupoRepository.findAll();
    }

    @Override
    public Optional<DocenteGrupo> getById(int id) {
    return _docenteGrupoRepository.findById(id);
    }

    @Override
    public DocenteGrupo CreateOrEdit(DocenteGrupo docenteGrupo) {
        return _docenteGrupoRepository.save(docenteGrupo);
    }

    @Override
    public void deleteById(Integer id) {
        _docenteGrupoRepository.deleteById(id);
    }
}

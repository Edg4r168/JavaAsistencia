package esfe.asistencia.servicios.implementaciones;

import esfe.asistencia.entidades.EstudianteGrupo;
import esfe.asistencia.repositorios.IEstudianteGrupoRepository;
import esfe.asistencia.servicios.interfaces.IEstudianteGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteGrupoService implements IEstudianteGrupoService {
    @Autowired
    private IEstudianteGrupoRepository _estudianteGrupoRepository;

    @Override
    public Page<EstudianteGrupo> searchPaginated(Pageable pageable) {
        return _estudianteGrupoRepository.findByOrderByEstudianteNameDesc(pageable);
    }

    @Override
    public List<EstudianteGrupo> getAll() {
        return _estudianteGrupoRepository.findAll();
    }

    @Override
    public Optional<EstudianteGrupo> getById(int id) {
        return _estudianteGrupoRepository.findById(id);
    }

    @Override
    public EstudianteGrupo CreateOrEdit(EstudianteGrupo estudianteGrupo) {
        return _estudianteGrupoRepository.save(estudianteGrupo);
    }

    @Override
    public void deleteById(Integer id) {
        _estudianteGrupoRepository.deleteById(id);
    }
}

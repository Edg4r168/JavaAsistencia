package esfe.asistencia.servicios.implementaciones;

import esfe.asistencia.entidades.Estudiante;
import esfe.asistencia.repositorios.IEstudianteRepository;
import esfe.asistencia.servicios.interfaces.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    private IEstudianteRepository _estudianteRepository;

    @Override
    public Page<Estudiante> searchPaginated(Pageable pageable) {
        return _estudianteRepository.findAll(pageable);
    }

    @Override
    public List<Estudiante> getAll() {
        return _estudianteRepository.findAll();
    }

    @Override
    public Optional<Estudiante> getById(int id) {
        return _estudianteRepository.findById(id);
    }

    @Override
    public Estudiante CreateOrEdit(Estudiante estudiante) {
        return _estudianteRepository.save(estudiante);
    }

    @Override
    public void deleteById(Integer id) {
        _estudianteRepository.deleteById(id);
    }
}

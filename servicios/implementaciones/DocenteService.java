package esfe.asistencia.servicios.implementaciones;

import esfe.asistencia.entidades.Docente;
import esfe.asistencia.repositorios.IDocenteRepository;
import esfe.asistencia.servicios.interfaces.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService implements IDocenteService {
    @Autowired
    private IDocenteRepository _docenteRepository;

    @Override
    public Page<Docente> searchPaginated(Pageable pageable) {
        return _docenteRepository.findAll(pageable);
    }

    @Override
    public List<Docente> getAll() {
        return _docenteRepository.findAll();
    }

    @Override
    public Optional<Docente> getById(int id) {
        return _docenteRepository.findById(id);
    }

    @Override
    public Docente CreateOrEdit(Docente docente) {
        return _docenteRepository.save(docente);
    }

    @Override
    public void deleteById(Integer id) {
        _docenteRepository.deleteById(id);
    }
}

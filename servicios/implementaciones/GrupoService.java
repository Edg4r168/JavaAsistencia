package esfe.asistencia.servicios.implementaciones;

import esfe.asistencia.entidades.Grupo;
import esfe.asistencia.repositorios.IGrupoRepository;
import esfe.asistencia.servicios.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService implements IGrupoService {
    @Autowired
    private IGrupoRepository _grupoRepository;

    @Override
    public Page<Grupo> searchPaginated(Pageable pageable) {
        return _grupoRepository.findAll(pageable);
    }

    @Override
    public List<Grupo> getAll() {
        return _grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo> getById(int id) {
        return _grupoRepository.findById(id);
    }

    @Override
    public Grupo CreateOrEdit(Grupo grupo) {
        return _grupoRepository.save(grupo);
    }

    @Override
    public void deleteById(Integer id) {
        _grupoRepository.deleteById(id);
    }
}

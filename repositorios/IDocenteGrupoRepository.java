package esfe.asistencia.repositorios;

import esfe.asistencia.entidades.DocenteGrupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDocenteGrupoRepository extends JpaRepository<DocenteGrupo, Integer> {
    @Query("SELECT dg FROM DocenteGrupo dg ORDER BY dg.anio DESC")
    Page<DocenteGrupo> findByOrderTeacherDesc(Pageable pageable);
}

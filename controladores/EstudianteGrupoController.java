package esfe.asistencia.controladores;

import esfe.asistencia.entidades.*;
import esfe.asistencia.servicios.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/EstudianteGrupo")
public class EstudianteGrupoController {
    @Autowired
    private IEstudianteGrupoService _estudianteGrupoService;

    @Autowired
    private IEstudianteService _estudianteService;

    @Autowired
    private IGrupoService _grupoService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size
    ) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(3);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<EstudianteGrupo> estudianteGrupos = _estudianteGrupoService.searchPaginated(pageable);

        model.addAttribute("estudianteGrupos", estudianteGrupos);
        int totalPge = estudianteGrupos.getTotalPages();

        if(totalPge > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPge)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }

        return "estudianteGrupo/index";
    }

    @GetMapping("/create")
    public String create(EstudianteGrupo estudianteGrupo, Model model) {

        List<Estudiante> estudiantes = _estudianteService.getAll();
        List<Grupo> grupos = _grupoService.getAll();

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("grupos", grupos);

        return "estudianteGrupo/create";
    }

    @PostMapping("/save")
    public String save(EstudianteGrupo estudianteGrupo, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(model);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error");

            return "estudianteGrupo/create";
        }

        _estudianteGrupoService.CreateOrEdit(estudianteGrupo);
        attributes.addFlashAttribute("msg", "Grupo de estudiante creado correctamente");

        return "redirect:/EstudianteGrupo";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = _estudianteGrupoService.getById(id).get();

        model.addAttribute("estudianteGrupo", estudianteGrupo);

        return "estudianteGrupo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = _estudianteGrupoService.getById(id).get();
        List<Estudiante> estudiantes = _estudianteService.getAll();
        List<Grupo> grupos = _grupoService.getAll();

        model.addAttribute("estudianteGrupo", estudianteGrupo);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("grupos", grupos);

        return "estudianteGrupo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = _estudianteGrupoService.getById(id).get();
        model.addAttribute("estudianteGrupo", estudianteGrupo);

        return "estudianteGrupo/delete";
    }

    @PostMapping("/delete")
    public String delete(EstudianteGrupo estudianteGrupo, RedirectAttributes attributes) {

        _estudianteGrupoService.deleteById(estudianteGrupo.getId());
        attributes.addFlashAttribute("msg", "Grupo de estudiante eliminado correctamente");

        return "redirect:/EstudianteGrupo";
    }
}

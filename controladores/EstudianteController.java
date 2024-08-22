package esfe.asistencia.controladores;

import esfe.asistencia.entidades.Docente;
import esfe.asistencia.entidades.Estudiante;
import esfe.asistencia.servicios.interfaces.IDocenteService;
import esfe.asistencia.servicios.interfaces.IEstudianteService;
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
@RequestMapping("/Estudiante")
public class EstudianteController {
    @Autowired
    private IEstudianteService _estudianteService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size
    ) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(3);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Estudiante> estudiantes = _estudianteService.searchPaginated(pageable);

        model.addAttribute("estudiantes", estudiantes);
        int totalPge = estudiantes.getTotalPages();

        if(totalPge > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPge)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }

        return "estudiante/index";
    }

    @GetMapping("/create")
    public String create(Estudiante estudiante) {
        return "estudiante/create";
    }

    @PostMapping("/save")
    public String save(Estudiante estudiante, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(model);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error");

            return "estudiante/create";
        }

        _estudianteService.CreateOrEdit(estudiante);
        attributes.addFlashAttribute("msg", "Estudiante creado correctamente");

        return "redirect:/Estudiante";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Estudiante estudiante = _estudianteService.getById(id).get();
        model.addAttribute("estudiante", estudiante);

        return "estudiante/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Estudiante estudiante = _estudianteService.getById(id).get();
        model.addAttribute("estudiante", estudiante);

        return "estudiante/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Estudiante estudiante = _estudianteService.getById(id).get();
        model.addAttribute("estudiante", estudiante);

        return "estudiante/delete";
    }

    @PostMapping("/delete")
    public String delete(Estudiante estudiante, RedirectAttributes attributes) {

        _estudianteService.deleteById(estudiante.getId());
        attributes.addFlashAttribute("msg", "Estudiante eliminado correctamente");

        return "redirect:/Estudiante";
    }
}

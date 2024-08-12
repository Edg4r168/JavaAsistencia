package esfe.asistencia.controladores;

import esfe.asistencia.entidades.Docente;
import esfe.asistencia.servicios.interfaces.IDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/Docente")
public class DocenteController {
    @Autowired
    private IDocenteService _docenteService;

    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size
    ) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(3);

        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Docente> docentes = _docenteService.searchPaginated(pageable);

        model.addAttribute("docentes", docentes);
        int totalPge = docentes.getTotalPages();

        if(totalPge > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPge)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }

        return "docente/index";
    }

    @GetMapping("/create")
    public String create(Docente docente) {
        return "docente/create";
    }

    @PostMapping("/save")
    public String save(Docente docente, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(model);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error");

            return "docente/create";
        }

        _docenteService.CreateOrEdit(docente);
        attributes.addFlashAttribute("msg", "Docente creado correctamente");

        return "redirect:/Docente";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        Docente docente = _docenteService.getById(id).get();
        model.addAttribute("docente", docente);

        return "docente/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Docente docente = _docenteService.getById(id).get();
        model.addAttribute("docente", docente);

        return "docente/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        Docente docente = _docenteService.getById(id).get();
        model.addAttribute("docente", docente);

        return "docente/delete";
    }

    @PostMapping("/delete")
    public String delete(Docente docente, RedirectAttributes attributes) {

        _docenteService.deleteById(docente.getId());
        attributes.addFlashAttribute("msg", "Docente eliminado correctamente");

        return "redirect:/Docente";
    }
}

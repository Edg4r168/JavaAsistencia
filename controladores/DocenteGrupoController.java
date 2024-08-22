package esfe.asistencia.controladores;

import esfe.asistencia.entidades.Docente;
import esfe.asistencia.entidades.DocenteGrupo;
import esfe.asistencia.entidades.Grupo;
import esfe.asistencia.servicios.interfaces.IDocenteGrupoService;
import esfe.asistencia.servicios.interfaces.IDocenteService;
import esfe.asistencia.servicios.interfaces.IGrupoService;
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
@RequestMapping("DocenteGrupo")
public class DocenteGrupoController {
    @Autowired
    private IDocenteGrupoService _docenteGrupoService;
    @Autowired
    private IDocenteService _docenteService;
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
        Page<DocenteGrupo> docentesGrupos = _docenteGrupoService.searchPaginated(pageable);

        model.addAttribute("docentesGrupos", docentesGrupos);
        int totalPge = docentesGrupos.getTotalPages();

        if(totalPge > 0) {
            List<Integer> pageNumber = IntStream.rangeClosed(1, totalPge)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumber", pageNumber);
        }

        return "docenteGrupo/index";
    }

    @GetMapping("/create")
    public String create(DocenteGrupo docenteGrupo, Model model) {

        List<Docente> docentes = _docenteService.getAll();
        List<Grupo> grupos = _grupoService.getAll();

        model.addAttribute("docentes", docentes);
        model.addAttribute("grupos", grupos);

        return "docenteGrupo/create";
    }

    @PostMapping("/save")
    public String save(DocenteGrupo docenteGrupo, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(model);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error");

            return "docenteGrupo/create";
        }

        _docenteGrupoService.CreateOrEdit(docenteGrupo);
        attributes.addFlashAttribute("msg", "Grupo de docente creado correctamente");

        return "redirect:/DocenteGrupo";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        DocenteGrupo docenteGrupo = _docenteGrupoService.getById(id).get();

        model.addAttribute("docenteGrupo", docenteGrupo);

        return "docenteGrupo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        DocenteGrupo docenteGrupo = _docenteGrupoService.getById(id).get();
        List<Docente> docentes = _docenteService.getAll();
        List<Grupo> grupos = _grupoService.getAll();

        model.addAttribute("docenteGrupo", docenteGrupo);
        model.addAttribute("docentes", docentes);
        model.addAttribute("grupos", grupos);

        return "docenteGrupo/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        DocenteGrupo docenteGrupo = _docenteGrupoService.getById(id).get();
        model.addAttribute("docenteGrupo", docenteGrupo);

        return "docenteGrupo/delete";
    }

    @PostMapping("/delete")
    public String delete(DocenteGrupo docenteGrupo, RedirectAttributes attributes) {

        _docenteGrupoService.deleteById(docenteGrupo.getId());
        attributes.addFlashAttribute("msg", "Grupo de docente eliminado correctamente");

        return "redirect:/DocenteGrupo";
    }
}

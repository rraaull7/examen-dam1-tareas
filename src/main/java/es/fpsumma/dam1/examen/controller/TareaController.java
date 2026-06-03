package es.fpsumma.dam1.examen.controller;

import es.fpsumma.dam1.examen.model.Tarea;
import es.fpsumma.dam1.examen.service.EmpleadoService;
import es.fpsumma.dam1.examen.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;
    private final EmpleadoService empleadoService;


    @GetMapping
    public String listar(Model model,
                         @RequestParam(required = false) String estado) {

        if (estado != null && !estado.isBlank()) {
            model.addAttribute("tareas", tareaService.filtrarEstado(estado));
            model.addAttribute("filtrarestado", estado);
        } else {
            model.addAttribute("tareas", tareaService.findAll());
        }
        model.addAttribute("empleados", empleadoService.findAll());
        return "tareas-listado";
    }


    @GetMapping("/{id}")
    public String detalle(@PathVariable long id, Model model) {
        model.addAttribute("detalles", tareaService.findById(id));
        return "tareas-detalle";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("empleados", empleadoService.findAll());
        return "tarea-formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute Tarea tarea, RedirectAttributes redirectAttributes) {
        try {
            tareaService.save(tarea);
            redirectAttributes.addFlashAttribute("mensaje", "mensaje guardado bien");
            return "redirect:/tareas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/tareas-listado";
        }
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("tarea", tareaService.findById(id));
        model.addAttribute("empleados", empleadoService.findAll());
        return "tarea-formulario";
    }


@PostMapping("/{id}/editar")
    public String guardareditado(@PathVariable Long id, @ModelAttribute Tarea tarea, RedirectAttributes redirectAttributes) {
        try {
            tarea.setId(id);
            tareaService.update(tarea);
            redirectAttributes.addFlashAttribute("mensaje", "tarea actualizada correctamente");
            return "redirect:/tareas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return  "redirect:/tareas/";
        }




}


    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            tareaService.deletebyId(id);

            redirectAttributes.addFlashAttribute("mensaje", "tarea eliminada bien");
        } catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "error al eliminar la tarea");
        }

         return "redirect:/tareas-listado";
    }





}
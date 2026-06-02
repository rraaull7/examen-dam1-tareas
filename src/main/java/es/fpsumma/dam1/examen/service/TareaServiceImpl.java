package es.fpsumma.dam1.examen.service;

import es.fpsumma.dam1.examen.model.Tarea;
import es.fpsumma.dam1.examen.repository.EmpleadoRepository;
import es.fpsumma.dam1.examen.repository.TareaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TareaServiceImpl implements TareaService {

    private final TareaRepository tareaRepository;
    private final EmpleadoRepository empleadoRepository;



    @Override
    public List<Tarea> findAll() {
return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> findById(long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public void save(Tarea tarea) {

        if(tarea.getTitulo()==null   || tarea.getTitulo().isBlank()  ) {
            throw new IllegalArgumentException("El titulo no puede estar vacio");
        }
if (tarea.getDescripcion()== null || tarea.getDescripcion().isBlank()  ) {
    throw new IllegalArgumentException("la descripcion no puede estar vacia");
}
    if (tarea.getEstado()==null || tarea.getEstado().isBlank()  ) {
        throw new IllegalArgumentException("El estado no puede estar vacio");
    }
if (tarea.getPrioridad() == null || tarea.getPrioridad().isBlank() ){
    throw new IllegalArgumentException("la prioridad no puede estar vacia");
}
if (tarea.getFechaLimite() == null ){
    throw new IllegalArgumentException("la  fecha limite tiene q existir");
}
if(tarea.getEmpleado()==null || tarea.getEmpleado().getId() <= 0 ){
    throw new IllegalArgumentException("tienes q elegir un empleado correcto");
}
empleadoRepository.findById(tarea.getEmpleado().getId());
tareaRepository.save(tarea);
    }

    @Override
    public void update(Tarea tarea) {

        if(tarea.getTitulo()==null   || tarea.getTitulo().isBlank()  ) {
            throw new IllegalArgumentException("El titulo no puede estar vacio");
        }
        if (tarea.getDescripcion()== null || tarea.getDescripcion().isBlank()  ) {
            throw new IllegalArgumentException("la descripcion no puede estar vacia");
        }
        if (tarea.getEstado()==null || tarea.getEstado().isBlank()  ) {
            throw new IllegalArgumentException("El estado no puede estar vacio");
        }
        if (tarea.getPrioridad() == null || tarea.getPrioridad().isBlank() ){
            throw new IllegalArgumentException("la prioridad no puede estar vacia");
        }
        if (tarea.getFechaLimite() == null ){
            throw new IllegalArgumentException("la  fecha limite tiene q existir");
        }
        if(tarea.getEmpleado()==null || tarea.getEmpleado().getId() <= 0 ){
            throw new IllegalArgumentException("tienes q elegir un empleado correcto");
        }
tareaRepository.findById(tarea.getId());
        empleadoRepository.findById(tarea.getEmpleado().getId());
tareaRepository.update(tarea);
    }

    @Override
    public void deletebyId(long id) {
        tareaRepository.findById(id);
tareaRepository.deleteById(id);
    }

    @Override
    public List<Tarea> filtrarEstado(String estado) {

return tareaRepository.filtrarEstado(estado);    }
}

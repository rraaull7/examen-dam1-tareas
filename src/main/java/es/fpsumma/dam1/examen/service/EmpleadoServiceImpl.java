package es.fpsumma.dam1.examen.service;

import es.fpsumma.dam1.examen.model.Empleado;
import es.fpsumma.dam1.examen.repository.EmpleadoRepository;
import es.fpsumma.dam1.examen.repository.TareaRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmpleadoServiceImpl implements EmpleadoService {


    private final EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAll() {

return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(long id) {
        return empleadoRepository.findById(id);
    }


}

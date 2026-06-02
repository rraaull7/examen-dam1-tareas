package es.fpsumma.dam1.examen.service;

import es.fpsumma.dam1.examen.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    List<Empleado> findAll();
    Optional<Empleado> findById(long id);

}

package es.fpsumma.dam1.examen.repository;

import es.fpsumma.dam1.examen.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {

    List<Empleado> findAll();
    Optional<Empleado> findById(long id);
}

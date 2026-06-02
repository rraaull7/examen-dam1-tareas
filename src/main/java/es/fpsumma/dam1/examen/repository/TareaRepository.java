package es.fpsumma.dam1.examen.repository;

import es.fpsumma.dam1.examen.model.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaRepository {

    List<Tarea> findAll();
    Optional<Tarea> findById(long id);
    void save(Tarea tarea);
    void update(Tarea tarea);
    void deleteById(long id);
    List<Tarea> filtrarEstado(String estado);
}

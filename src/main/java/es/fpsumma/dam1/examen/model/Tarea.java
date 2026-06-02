package es.fpsumma.dam1.examen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tarea {
    private long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private String prioridad;
    private LocalDate fechaLimite;
    private boolean completada;
    private Empleado empleado;




}

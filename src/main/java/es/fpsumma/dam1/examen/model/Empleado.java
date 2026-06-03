package es.fpsumma.dam1.examen.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Empleado {

    private Long id;
    private String nombre;
    private String email;

}

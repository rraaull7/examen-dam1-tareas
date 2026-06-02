package es.fpsumma.dam1.examen.repository;

import es.fpsumma.dam1.examen.model.Empleado;
import es.fpsumma.dam1.examen.model.Tarea;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TareaRepositoryImpl implements TareaRepository {

    private final  JdbcTemplate jdbcTemplate;

    private final RowMapper<Tarea> tareaRowMapper = (rs, rowNum) -> {
        Empleado e = new Empleado(
                rs.getLong("empleado_id"),
                rs.getString("empleado_nombre"),
                rs.getString("empleado_email")
                );

        Tarea t = new Tarea();
        t.setId(rs.getLong("id"));
        t.setTitulo(rs.getString("titulo"));
        t.setDescripcion(rs.getString("descripcion"));
        t.setEstado(rs.getString("estado"));
        t.setPrioridad(rs.getString("prioridad"));
        t.setFechaLimite(rs.getDate("fecha_limite").toLocalDate());
        t.setCompletada(rs.getBoolean("completada"));
        t.setEmpleado(e);
        return t;
    };


    @Override
    public List<Tarea> findAll() {
String sql = "SELECT\n" +
        "    t.id,\n" +
        "    t.titulo,\n" +
        "    t.descripcion,\n" +
        "    t.estado,\n" +
        "    t.prioridad,\n" +
        "    t.fecha_limite,\n" +
        "    t.completada,\n" +
        "    e.id AS empleado_id,\n" +
        "    e.nombre AS empleado_nombre,\n" +
        "    e.email AS empleado_email\n" +
        "FROM tareas t\n" +
        "LEFT JOIN empleados e ON t.empleado_id = e.id;";
return jdbcTemplate.query(sql, tareaRowMapper);

    }


    @Override
    public Optional<Tarea> findById(long id) {
        String sql ="SELECT\n" +
                "    t.id,\n" +
                "    t.titulo,\n" +
                "    t.descripcion,\n" +
                "    t.estado,\n" +
                "    t.prioridad,\n" +
                "    t.fecha_limite,\n" +
                "    t.completada,\n" +
                "    e.id AS empleado_id,\n" +
                "    e.nombre AS empleado_nombre,\n" +
                "    e.email AS empleado_email\n" +
                "FROM tareas t\n" +
                "LEFT JOIN empleados e ON t.empleado_id = e.id\n" +
                "WHERE t.id = ?;";
        return jdbcTemplate.query(sql, tareaRowMapper, id)
                .stream()
                .findFirst();
    }

    @Override
    public void save(Tarea tarea) {
String sql = "INSERT INTO tareas (\n" +
        "    titulo,\n" +
        "    descripcion,\n" +
        "    estado,\n" +
        "    prioridad,\n" +
        "    fecha_limite,\n" +
        "    completada,\n" +
        "    empleado_id\n" +
        ")\n" +
        "VALUES (?, ?, ?, ?, ?, ?, ?);";
 jdbcTemplate.update(sql,
        tarea.getTitulo(),
        tarea.getDescripcion(),
        tarea.getEstado(),
        tarea.getPrioridad(),
        java.sql.Date.valueOf(tarea.getFechaLimite()),
        tarea.isCompletada(),
        tarea.getEmpleado().getId()

);
    }

    @Override
    public void update(Tarea tarea) {
String sql ="UPDATE tareas\n" +
        "SET\n" +
        "    titulo = ?,\n" +
        "    descripcion = ?,\n" +
        "    estado = ?,\n" +
        "    prioridad = ?,\n" +
        "    fecha_limite = ?,\n" +
        "    completada = ?,\n" +
        "    empleado_id = ?\n" +
        "WHERE id = ?;";

jdbcTemplate.update(sql,
        tarea.getTitulo(),
        tarea.getDescripcion(),
        tarea.getEstado(),
        tarea.getPrioridad(),
        java.sql.Date.valueOf(tarea.getFechaLimite()),
        tarea.isCompletada(),
        tarea.getEmpleado().getId(),
        tarea.getId()
        );

    }

    @Override
    public void deleteById(long id) {
String sql = "DELETE FROM tareas\n" +
        "WHERE id = ?;";
jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Tarea> filtrarEstado(String estado) {
        String sql = "SELECT\n" +
                "    t.id,\n" +
                "    t.titulo,\n" +
                "    t.descripcion,\n" +
                "    t.estado,\n" +
                "    t.prioridad,\n" +
                "    t.fecha_limite,\n" +
                "    t.completada,\n" +
                "    e.id AS empleado_id,\n" +
                "    e.nombre AS empleado_nombre,\n" +
                "    e.email AS empleado_email\n" +
                "FROM tareas t\n" +
                "LEFT JOIN empleados e ON t.empleado_id = e.id\n" +
                "WHERE t.estado = ?;";
        return jdbcTemplate.query(sql,tareaRowMapper, estado);
    }
}

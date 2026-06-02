package es.fpsumma.dam1.examen.repository;

import es.fpsumma.dam1.examen.model.Empleado;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
@Repository
public class EmpleadoRepositoryImpl implements EmpleadoRepository {

    private final JdbcTemplate jdbcTemplate;


    private final RowMapper<Empleado> empleadoRowMapper = (rs, rowNum) -> {
        Empleado e = new Empleado();
        e.setId(rs.getLong("id"));
        e.setNombre(rs.getString("nombre"));
        e.setEmail(rs.getString("email"));
        return e;
    };


    @Override
    public List<Empleado> findAll() {
        String sql = "SELECT\n" +
                "    id,\n" +
                "    nombre,\n" +
                "    email\n" +
                "FROM empleados;";
        return jdbcTemplate.query(sql, empleadoRowMapper);
    }

    @Override
    public Optional<Empleado> findById(long id) {
        String sql ="SELECT\n" +
                "    id,\n" +
                "    nombre,\n" +
                "    email\n" +
                "FROM empleados\n" +
                "WHERE id = ?;";
        return jdbcTemplate.query(sql, empleadoRowMapper, id)
        .stream()
                .findFirst();

    }
}

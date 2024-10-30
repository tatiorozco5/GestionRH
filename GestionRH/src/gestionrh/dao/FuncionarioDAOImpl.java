package gestionrh.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import gestionrh.Config.ConexionDB;
import gestionrh.Model.Funcionario;

public class FuncionarioDAOImpl implements FuncionarioDAO {

    @Override
    public List<Funcionario> listarFuncionarios() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";

        try (Connection con = ConexionDB.Conectar(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                funcionarios.add(mapResultSetToFuncionario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    @Override
    public void CrearFuncionario(Funcionario funcionario) {
        String sql = "INSERT INTO funcionarios (tipoIdentificacion, numeroIdentificacion, nombres, apellidos, estadoCivil, sexo, direccion, telefono, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.Conectar(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, funcionario.getTipoIdentificacion());
            ps.setString(2, funcionario.getNumeroIdentificacion());
            ps.setString(3, funcionario.getNombres());
            ps.setString(4, funcionario.getApellidos());
            ps.setString(5, funcionario.getEstadoCivil());
            ps.setString(6, funcionario.getSexo());
            ps.setString(7, funcionario.getDireccion());
            ps.setString(8, funcionario.getTelefono());
            ps.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Error al obtener el ID del nuevo funcionario.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ActualizarFuncionario(Funcionario funcionario) {
        StringBuilder sql = new StringBuilder("UPDATE funcionarios SET ");
        List<Object> parameters = new ArrayList<>();

        if (funcionario.getTipoIdentificacion() != null) {
            sql.append("tipoIdentificacion = ?, ");
            parameters.add(funcionario.getTipoIdentificacion());
        }
        if (funcionario.getNumeroIdentificacion() != null) {
            sql.append("numeroIdentificacion = ?, ");
            parameters.add(funcionario.getNumeroIdentificacion());
        }
        if (funcionario.getNombres() != null) {
            sql.append("nombres = ?, ");
            parameters.add(funcionario.getNombres());
        }
        if (funcionario.getApellidos() != null) {
            sql.append("apellidos = ?, ");
            parameters.add(funcionario.getApellidos());
        }
        if (funcionario.getEstadoCivil() != null) {
            sql.append("estadoCivil = ?, ");
            parameters.add(funcionario.getEstadoCivil());
        }
        if (funcionario.getSexo() != null) {
            sql.append("sexo = ?, ");
            parameters.add(funcionario.getSexo());
        }
        if (funcionario.getDireccion() != null) {
            sql.append("direccion = ?, ");
            parameters.add(funcionario.getDireccion());
        }
        if (funcionario.getTelefono() != null) {
            sql.append("telefono = ?, ");
            parameters.add(funcionario.getTelefono());
        }
        if (funcionario.getFechaNacimiento() != null) {
            sql.append("fechaNacimiento = ?, ");
            parameters.add(new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
        }

        sql.setLength(sql.length() - 2); // Quita la última coma
        sql.append(" WHERE id = ?");
        parameters.add(funcionario.getId());

        try (Connection con = ConexionDB.Conectar(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setObject(i + 1, parameters.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario ObtenerFuncionario(int id) {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM funcionarios WHERE id = ?";
        try (Connection con = ConexionDB.Conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                funcionario = mapResultSetToFuncionario(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

    private Funcionario mapResultSetToFuncionario(ResultSet rs) throws SQLException {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(rs.getInt("id"));
        funcionario.setTipoIdentificacion(rs.getString("tipoIdentificacion"));
        funcionario.setNumeroIdentificacion(rs.getString("numeroIdentificacion"));
        funcionario.setNombres(rs.getString("nombres"));
        funcionario.setApellidos(rs.getString("apellidos"));
        funcionario.setEstadoCivil(rs.getString("estadoCivil"));
        funcionario.setSexo(rs.getString("sexo"));
        funcionario.setDireccion(rs.getString("direccion"));
        funcionario.setTelefono(rs.getString("telefono"));
        funcionario.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        return funcionario;
    }
}

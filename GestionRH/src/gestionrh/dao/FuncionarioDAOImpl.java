package gestionrh.dao;

import gestionrh.Config.ConexionDB;
import gestionrh.Model.Funcionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void ActualizarFuncionario(Funcionario funcionarioActualizado) {
        // Paso 1: Recuperar los datos actuales de la base de datos
        Funcionario funcionarioExistente = ObtenerFuncionario(funcionarioActualizado.getId());

        if (funcionarioExistente == null) {
            System.out.println("Funcionario no encontrado");
            return; // O lanzar una excepción
        }

        // Paso 2: Verificar y actualizar cada campo
        String tipoIdentificacion = funcionarioActualizado.getTipoIdentificacion() != null && !funcionarioActualizado.getTipoIdentificacion().isEmpty()
                ? funcionarioActualizado.getTipoIdentificacion()
                : funcionarioExistente.getTipoIdentificacion();

        String numeroIdentificacion = funcionarioActualizado.getNumeroIdentificacion() != null && !funcionarioActualizado.getNumeroIdentificacion().isEmpty()
                ? funcionarioActualizado.getNumeroIdentificacion()
                : funcionarioExistente.getNumeroIdentificacion();

        String nombres = funcionarioActualizado.getNombres() != null && !funcionarioActualizado.getNombres().isEmpty()
                ? funcionarioActualizado.getNombres()
                : funcionarioExistente.getNombres();

        String apellidos = funcionarioActualizado.getApellidos() != null && !funcionarioActualizado.getApellidos().isEmpty()
                ? funcionarioActualizado.getApellidos()
                : funcionarioExistente.getApellidos();

        String estadoCivil = funcionarioActualizado.getEstadoCivil() != null && !funcionarioActualizado.getEstadoCivil().isEmpty()
                ? funcionarioActualizado.getEstadoCivil()
                : funcionarioExistente.getEstadoCivil();

        String sexo = funcionarioActualizado.getSexo() != null && !funcionarioActualizado.getSexo().isEmpty()
                ? funcionarioActualizado.getSexo()
                : funcionarioExistente.getSexo();

        String direccion = funcionarioActualizado.getDireccion() != null && !funcionarioActualizado.getDireccion().isEmpty()
                ? funcionarioActualizado.getDireccion()
                : funcionarioExistente.getDireccion();

        String telefono = funcionarioActualizado.getTelefono() != null && !funcionarioActualizado.getTelefono().isEmpty()
                ? funcionarioActualizado.getTelefono()
                : funcionarioExistente.getTelefono();

        java.sql.Date fechaNacimiento;
        if (funcionarioActualizado.getFechaNacimiento() != null) {
            fechaNacimiento = new java.sql.Date(funcionarioActualizado.getFechaNacimiento().getTime());
        } else {
            fechaNacimiento = (Date) funcionarioExistente.getFechaNacimiento(); // No actualiza, mantiene el existente
        }

        // Paso 3: Realizar la actualización en la base de datos
        String query = "UPDATE funcionarios SET tipoIdentificacion = ?, numeroIdentificacion = ?, nombres = ?, apellidos = ?, estadoCivil = ?, sexo = ?, direccion = ?, telefono = ?, fechaNacimiento = ? WHERE id = ?";
        try (Connection conn = ConexionDB.Conectar(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tipoIdentificacion);
            ps.setString(2, numeroIdentificacion);
            ps.setString(3, nombres);
            ps.setString(4, apellidos);
            ps.setString(5, estadoCivil);
            ps.setString(6, sexo);
            ps.setString(7, direccion);
            ps.setString(8, telefono);
            ps.setDate(9, new java.sql.Date(fechaNacimiento.getTime())); // Conversión de Date a java.sql.Date
            ps.setInt(10, funcionarioActualizado.getId());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Funcionario actualizado exitosamente.");
            } else {
                System.out.println("No se actualizó ningún funcionario.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
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

    
    @Override
    public void eliminarFuncionario(int id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection con = ConexionDB.Conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar excepción
        }
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

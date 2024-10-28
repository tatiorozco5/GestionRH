/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionrh.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import gestionrh.Config.ConexionDB;
import gestionrh.Model.Funcionario;
        
public class FuncionarioDAOImpl implements FuncionarioDAO{
    @Override
    public List<Funcionario> listarFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "Select * from funcionarios";
        try (
                Connection con = ConexionDB.Conectar();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
                ) {
            while (rs.next()){
                funcionarios.add(mapResultSetToFuncionarios(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestionrh.dao;

import gestionrh.Model.Funcionario;
import java.util.List;

public interface FuncionarioDAO {
    List<Funcionario> listarFuncionarios();
    void CrearFuncionario(Funcionario funcionario);
    void ActualizarFuncionario(Funcionario funcionario);
    Funcionario ObtenerFuncionario(int id);
}

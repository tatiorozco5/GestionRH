/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionrh;

import gestionrh.Model.Funcionario;
import gestionrh.dao.FuncionarioDAO;
import gestionrh.dao.FuncionarioDAOImpl;
import java.util.Date;
import java.util.List;


public class GestionRH {

    public static void main(String[] args) {
       FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();

       System.out.println("Lista de todos los funcionarios:");
        List<Funcionario> todosLosFuncionarios = funcionarioDAO.listarFuncionarios();
        for (Funcionario funcionario : todosLosFuncionarios) {
            System.out.println(funcionario);
        }
       
       
        // ID que deseas actualizar manualmente
        int idExistente = 18;  // Reemplaza con un ID válido

        Funcionario funcionarioExistente = funcionarioDAO.ObtenerFuncionario(idExistente);

        if (funcionarioExistente != null) {

            Funcionario funcionarioActualizado = new Funcionario();
            funcionarioActualizado.setId(idExistente); // Establece el ID manualmente
            funcionarioActualizado.setNumeroIdentificacion("23456789");

            funcionarioDAO.ActualizarFuncionario(funcionarioActualizado);

            Funcionario funcionarioDespuesActualizacion = funcionarioDAO.ObtenerFuncionario(idExistente);
            System.out.println("Funcionario despues de la actualizacion:\n" + funcionarioDespuesActualizacion);
        } else {
            System.out.println("No se encontro el funcionario con ID: " + idExistente);
        }
    }
}

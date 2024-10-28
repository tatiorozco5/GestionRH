/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionrh;

import gestionrh.Config.ConexionDB;
import gestionrh.dao.FuncionarioDAO;
        
public class GestionRH {

    public static void main(String[] args) {
        // Listar todos los funcionarios
        System.out.println("Funcionarios: " + FuncionarioDAO.listarFuncionarios());
    }
    
}

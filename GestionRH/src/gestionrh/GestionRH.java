/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestionrh;

import gestionrh.Config.ConexionDB;
        
public class GestionRH {

    public static void main(String[] args) {
        ConexionDB dbc = new ConexionDB();
        dbc.Conectar();
    }
    
}

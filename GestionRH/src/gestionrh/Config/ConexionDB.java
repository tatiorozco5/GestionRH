/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestionrh.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    static String url = "jdbc:mysql://localhost:3306/gestionrh";
    static String user = "root";
    static String pass = "";

    public static Connection Conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion establecida");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}

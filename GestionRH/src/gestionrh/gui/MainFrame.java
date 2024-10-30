/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionrh.gui;

import gestionrh.dao.Funcionario;
import gestionrh.dao.FuncionarioDAO;
import gestionrh.dao.FuncionarioDAOImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class MainFrame extends JFrame {
    private FuncionarioDAO funcionarioDAO;
    private JTable funcionariosTable;
    private DefaultTableModel tableModel;


    public class MainFrame extends javax.swing.JFrame {
    private FuncionarioDAO funcionarioDAO;
    private JTable funcionarioTable;
    private DefaultTableModel tableModel;

    public MainFrame() {
        initComponents();
        funcionarioDAO = new FuncionarioDAOImpl();
        setupTable();
    }

     private void setupTable() {
        // Configura la tabla
        tableModel = new DefaultTableModel(new String[]{
                "ID", "Tipo de Identificación", "Número de Identificación", "Nombres", "Apellidos",
                "Estado Civil", "Sexo", "Dirección", "Teléfono", "Fecha de Nacimiento"
        }, 0);
        funcionarioTable.setModel(tableModel);
    }

    private void listarFuncionarios() {
        // Limpiar la tabla
        tableModel.setRowCount(0);
        
        // Obtener la lista de funcionarios
        List<Funcionario> funcionarios = funcionarioDAO.listarFuncionarios();
        
        // Agregar funcionarios a la tabla
        for (Funcionario f : funcionarios) {
            tableModel.addRow(new Object[]{
                f.getId(),
                f.getTipoIdentificacion(),
                f.getNumeroIdentificacion(),
                f.getNombres(),
                f.getApellidos(),
                f.getEstadoCivil(),
                f.getSexo(),
                f.getDireccion(),
                f.getTelefono(),
                f.getFechaNacimiento()
            });
        }
    }
    
    // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(listarButton)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(listarButton)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | 
                 javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
package gestionrh.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gestionrh.dao.FuncionarioDAOImpl;

public class EliminarFuncionarioFrame extends JFrame {
    private JTextField txtId;
    private JButton btnEliminar;

    public EliminarFuncionarioFrame() {
        // Configuración del JFrame
        setTitle("Eliminar Funcionario");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Inicialización de componentes
        txtId = new JTextField(15);
        btnEliminar = new JButton("Eliminar");
        
        // Añadir ActionListener al botón
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFuncionario();
            }
        });

        // Layout
        JPanel panel = new JPanel();
        panel.add(new JLabel("ID del Funcionario:"));
        panel.add(txtId);
        panel.add(btnEliminar);
        
        add(panel);
    }

    private void eliminarFuncionario() {
        String idStr = txtId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            FuncionarioDAOImpl funcionarioDAO = new FuncionarioDAOImpl(); // Crear instancia de DAO
            funcionarioDAO.eliminarFuncionario(id); // Llamar al método para eliminar
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

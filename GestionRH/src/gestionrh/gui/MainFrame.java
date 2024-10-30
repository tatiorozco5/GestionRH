package gestionrh.gui;

import gestionrh.Model.Funcionario;
import gestionrh.dao.FuncionarioDAO;
import gestionrh.dao.FuncionarioDAOImpl;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends javax.swing.JFrame {
    private FuncionarioDAO funcionarioDAO;
    private JTable funcionarioTable;
    private DefaultTableModel tableModel;
    private JButton actualizarButton;

    public MainFrame() {
        initComponents();
        funcionarioDAO = new FuncionarioDAOImpl();
        setupTable();
    }

    private void setupTable() {
        // Configura la tabla
        tableModel = new DefaultTableModel(new String[] {
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
            tableModel.addRow(new Object[] {
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

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Funcionarios");
        setSize(800, 1000);
        setLocationRelativeTo(null);

        // Crear componentes
        JButton listarButton = new JButton("Listar Funcionarios");
        listarButton.addActionListener(e -> listarFuncionarios());

        funcionarioTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(funcionarioTable);

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Dentro del método initComponents() de MainFrame
        JButton crearButton = new JButton("Crear Funcionario");
        crearButton.addActionListener(e -> new CrearFuncionarioFrame().setVisible(true));

        JButton actualizarButton = new JButton("Actualizar Funcionario");
        actualizarButton.addActionListener(e -> new ActualizarFuncionarioFrame().setVisible(true));

        // Añadir estos botones al layout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(crearButton)
                                .addComponent(actualizarButton)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 750,
                                        GroupLayout.PREFERRED_SIZE)));

        // En el setVerticalGroup también se deben incluir los nuevos botones
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(crearButton)
                        .addComponent(actualizarButton)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE));

        pack();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify
    // End of variables declaration

}

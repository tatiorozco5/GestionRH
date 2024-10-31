package gestionrh.gui;

import gestionrh.Model.Funcionario;
import gestionrh.dao.FuncionarioDAO;
import gestionrh.dao.FuncionarioDAOImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*; // Añadir esta línea
import javax.swing.table.DefaultTableModel;    // Añadir esta línea

public class MainFrame extends javax.swing.JFrame {

    private JButton btnActualizarFuncionario; // Botón para actualizar
    private JButton btnAbrirActualizar; // Definición de btnAbrirActualizar
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

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestión de Funcionarios");
        setSize(800, 1000);
        setLocationRelativeTo(null);

        // Crear componentes
        funcionarioTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(funcionarioTable);

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JButton listarButton = new JButton("Listar Funcionarios");
        listarButton.addActionListener(e -> listarFuncionarios());

        // Crear botón para abrir el formulario de creación
        JButton crearButton = new JButton("Crear Funcionario");
        crearButton.addActionListener(e -> new CrearFuncionarioFrame().setVisible(true));

        btnAbrirActualizar = new JButton("Abrir Actualizar");
        btnAbrirActualizar.addActionListener(e -> {
            int funcionarioId = obtenerFuncionarioId(); // Obtener el ID del funcionario seleccionado
            if (funcionarioId != -1) { // Verificar si se obtuvo un ID válido
                ActualizarFuncionarioFrame actualizarFrame = new ActualizarFuncionarioFrame(funcionarioId);
                actualizarFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Por favor, selecciona un funcionario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizarFuncionario = new JButton("Actualizar Funcionario");
        btnActualizarFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int funcionarioId = obtenerFuncionarioId(); // Obtener el ID del funcionario seleccionado
                if (funcionarioId != -1) { // Verificar si se obtuvo un ID válido
                    ActualizarFuncionarioFrame actualizarFrame = new ActualizarFuncionarioFrame(funcionarioId);
                    actualizarFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Por favor, selecciona un funcionario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnEliminar = new JButton("Eliminar Funcionario");
        btnEliminar.addActionListener(e -> {
            int funcionarioId = obtenerFuncionarioId(); // Obtener el ID del funcionario seleccionado
            if (funcionarioId != -1) { // Verificar si se obtuvo un ID válido
                int confirm = JOptionPane.showConfirmDialog(MainFrame.this,
                        "¿Está seguro que desea eliminar al funcionario con ID " + funcionarioId + "?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    funcionarioDAO.eliminarFuncionario(funcionarioId); // Llama al método de la instancia
                    listarFuncionarios(); // Refresca la lista después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Por favor, selecciona un funcionario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Añadir estos botones al layout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(listarButton)
                                .addComponent(crearButton)
                                .addComponent(btnAbrirActualizar) // Añadido aqu
                                .addComponent(btnEliminar) // Añadido aquí
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 750,
                                        GroupLayout.PREFERRED_SIZE)));

        // En el setVerticalGroup también se deben incluir los nuevos botones
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(listarButton)
                        .addComponent(crearButton)
                        .addComponent(btnAbrirActualizar) // Añadido aquí
                        .addComponent(btnEliminar) // Añadido aquí
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE));

        pack();
    }

    private int obtenerFuncionarioId() {
        int selectedRow = funcionarioTable.getSelectedRow(); // Cambiado a funcionarioTable

        if (selectedRow != -1) { // Si hay una fila seleccionada
            return (int) funcionarioTable.getValueAt(selectedRow, 0); // Cambiado a funcionarioTable
        } else {
            return -1; // Retornar -1 si no hay selección
        }
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

   
}

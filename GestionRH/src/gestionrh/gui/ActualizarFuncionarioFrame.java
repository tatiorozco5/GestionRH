package gestionrh.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarFuncionarioFrame extends JFrame {
    
    private JTextField idField;
    private JTextField tipoIdentificacionField;
    private JTextField numeroIdentificacionField;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField estadoCivilField;
    private JTextField sexoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField fechaNacimientoField;

    public ActualizarFuncionarioFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Actualizar Funcionario");
        setSize(400, 400);
        setLocationRelativeTo(null); // Centrar la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cerrar solo esta ventana

        // Crear componentes
        idField = new JTextField(20);
        tipoIdentificacionField = new JTextField(20);
        numeroIdentificacionField = new JTextField(20);
        nombresField = new JTextField(20);
        apellidosField = new JTextField(20);
        estadoCivilField = new JTextField(20);
        sexoField = new JTextField(20);
        direccionField = new JTextField(20);
        telefonoField = new JTextField(20);
        fechaNacimientoField = new JTextField(20);
        
        JButton actualizarButton = new JButton("Actualizar Funcionario");
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFuncionario();
            }
        });

        // Configurar el Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(new JLabel("ID"))
                    .addComponent(idField)
                    .addComponent(new JLabel("Tipo de Identificación"))
                    .addComponent(tipoIdentificacionField)
                    .addComponent(new JLabel("Número de Identificación"))
                    .addComponent(numeroIdentificacionField)
                    .addComponent(new JLabel("Nombres"))
                    .addComponent(nombresField)
                    .addComponent(new JLabel("Apellidos"))
                    .addComponent(apellidosField)
                    .addComponent(new JLabel("Estado Civil"))
                    .addComponent(estadoCivilField)
                    .addComponent(new JLabel("Sexo"))
                    .addComponent(sexoField)
                    .addComponent(new JLabel("Dirección"))
                    .addComponent(direccionField)
                    .addComponent(new JLabel("Teléfono"))
                    .addComponent(telefonoField)
                    .addComponent(new JLabel("Fecha de Nacimiento"))
                    .addComponent(fechaNacimientoField)
                    .addComponent(actualizarButton))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(new JLabel("ID"))
                .addComponent(idField)
                .addComponent(new JLabel("Tipo de Identificación"))
                .addComponent(tipoIdentificacionField)
                .addComponent(new JLabel("Número de Identificación"))
                .addComponent(numeroIdentificacionField)
                .addComponent(new JLabel("Nombres"))
                .addComponent(nombresField)
                .addComponent(new JLabel("Apellidos"))
                .addComponent(apellidosField)
                .addComponent(new JLabel("Estado Civil"))
                .addComponent(estadoCivilField)
                .addComponent(new JLabel("Sexo"))
                .addComponent(sexoField)
                .addComponent(new JLabel("Dirección"))
                .addComponent(direccionField)
                .addComponent(new JLabel("Teléfono"))
                .addComponent(telefonoField)
                .addComponent(new JLabel("Fecha de Nacimiento"))
                .addComponent(fechaNacimientoField)
                .addComponent(actualizarButton)
        );

        pack(); // Ajustar el tamaño de la ventana según los componentes
    }

    private void actualizarFuncionario() {
        // Lógica para actualizar un funcionario
        int id = Integer.parseInt(idField.getText());
        String tipoIdentificacion = tipoIdentificacionField.getText();
        String numeroIdentificacion = numeroIdentificacionField.getText();
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String estadoCivil = estadoCivilField.getText();
        String sexo = sexoField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String fechaNacimiento = fechaNacimientoField.getText();
        
        // Aquí llamarías a tu método DAO para actualizar el funcionario
        // FuncionarioDAO.actualizarFuncionario(id, ...);
        
        JOptionPane.showMessageDialog(this, "Funcionario actualizado exitosamente!");
        dispose(); // Cerrar la ventana después de actualizar
}
}

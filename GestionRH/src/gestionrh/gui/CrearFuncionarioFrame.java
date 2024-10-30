package gestionrh.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearFuncionarioFrame extends JFrame {
    
    private JTextField tipoIdentificacionField;
    private JTextField numeroIdentificacionField;
    private JTextField nombresField;
    private JTextField apellidosField;
    private JTextField estadoCivilField;
    private JTextField sexoField;
    private JTextField direccionField;
    private JTextField telefonoField;
    private JTextField fechaNacimientoField;

    public CrearFuncionarioFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Crear Funcionario");
        setSize(400, 400);
        setLocationRelativeTo(null); // Centrar la ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Cerrar solo esta ventana

        // Crear componentes
        tipoIdentificacionField = new JTextField(20);
        numeroIdentificacionField = new JTextField(20);
        nombresField = new JTextField(20);
        apellidosField = new JTextField(20);
        estadoCivilField = new JTextField(20);
        sexoField = new JTextField(20);
        direccionField = new JTextField(20);
        telefonoField = new JTextField(20);
        fechaNacimientoField = new JTextField(20);
        
        JButton crearButton = new JButton("Crear Funcionario");
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearFuncionario();
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
                    .addComponent(crearButton))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
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
                .addComponent(crearButton)
        );

        pack(); // Ajustar el tamaño de la ventana según los componentes
    }

    private void crearFuncionario() {
        // Lógica para crear un funcionario
        String tipoIdentificacion = tipoIdentificacionField.getText();
        String numeroIdentificacion = numeroIdentificacionField.getText();
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String estadoCivil = estadoCivilField.getText();
        String sexo = sexoField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String fechaNacimiento = fechaNacimientoField.getText();
        
        // Aquí llamarías a tu método DAO para crear el funcionario
        // FuncionarioDAO.crearFuncionario(...);
        
        JOptionPane.showMessageDialog(this, "Funcionario creado exitosamente!");
        dispose(); // Cerrar la ventana después de crear
}
}
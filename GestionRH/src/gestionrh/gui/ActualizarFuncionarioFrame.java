package gestionrh.gui;

import gestionrh.Model.Funcionario;
import gestionrh.dao.FuncionarioDAO;
import gestionrh.dao.FuncionarioDAOImpl;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ActualizarFuncionarioFrame extends JFrame {

    private JLabel labelTipoIdentificacion, labelNumeroIdentificacion, labelNombres, labelApellidos, labelEstadoCivil, labelSexo, labelDireccion, labelTelefono, labelFechaNacimiento;
    private JTextField txtNumeroIdentificacion, txtNombres, txtApellidos, txtDireccion, txtTelefono;
    private JComboBox<String> comboTipoIdentificacion, comboEstadoCivil, comboSexo;
    private JSpinner spinnerFechaNacimiento;
    private JButton btnActualizar;
    private int funcionarioId; 

    public ActualizarFuncionarioFrame(int funcionarioId) {
        this.funcionarioId = funcionarioId;
        initComponents();
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        labelTipoIdentificacion = new JLabel("Tipo Identificación:");
        labelNumeroIdentificacion = new JLabel("Número Identificación:");
        labelNombres = new JLabel("Nombres:");
        labelApellidos = new JLabel("Apellidos:");
        labelEstadoCivil = new JLabel("Estado Civil:");
        labelSexo = new JLabel("Sexo:");
        labelDireccion = new JLabel("Dirección:");
        labelTelefono = new JLabel("Teléfono:");
        labelFechaNacimiento = new JLabel("Fecha Nacimiento:");

        comboTipoIdentificacion = new JComboBox<>(new String[]{"CC", "TI", "CE"}); // Ejemplo de tipos de identificación
        txtNumeroIdentificacion = new JTextField(20);
        txtNombres = new JTextField(20);
        txtApellidos = new JTextField(20);
        comboEstadoCivil = new JComboBox<>(new String[]{"Soltero", "Casado", "Divorciado"}); // Ejemplo de estados civiles
        comboSexo = new JComboBox<>(new String[]{"M", "F"});
        txtDireccion = new JTextField(20);
        txtTelefono = new JTextField(15);
        spinnerFechaNacimiento = new JSpinner(new SpinnerDateModel());

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labelTipoIdentificacion)
                    .addComponent(labelNumeroIdentificacion)
                    .addComponent(labelNombres)
                    .addComponent(labelApellidos)
                    .addComponent(labelEstadoCivil)
                    .addComponent(labelSexo)
                    .addComponent(labelDireccion)
                    .addComponent(labelTelefono)
                    .addComponent(labelFechaNacimiento))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(comboTipoIdentificacion)
                    .addComponent(txtNumeroIdentificacion)
                    .addComponent(txtNombres)
                    .addComponent(txtApellidos)
                    .addComponent(comboEstadoCivil)
                    .addComponent(comboSexo)
                    .addComponent(txtDireccion)
                    .addComponent(txtTelefono)
                    .addComponent(spinnerFechaNacimiento)
                    .addComponent(btnActualizar))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoIdentificacion)
                    .addComponent(comboTipoIdentificacion))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumeroIdentificacion)
                    .addComponent(txtNumeroIdentificacion))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombres)
                    .addComponent(txtNombres))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelApellidos)
                    .addComponent(txtApellidos))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEstadoCivil)
                    .addComponent(comboEstadoCivil))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSexo)
                    .addComponent(comboSexo))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDireccion)
                    .addComponent(txtDireccion))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTelefono)
                    .addComponent(txtTelefono))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaNacimiento)
                    .addComponent(spinnerFechaNacimiento))
                .addComponent(btnActualizar)
        );
    }

    private void btnActualizarActionPerformed(ActionEvent evt) {
        Funcionario funcionario = new Funcionario();
        
        // Rellena el objeto Funcionario con los datos ingresados
        funcionario.setId(funcionarioId); // Asigna el ID del funcionario
        funcionario.setTipoIdentificacion((String) comboTipoIdentificacion.getSelectedItem());
        funcionario.setNumeroIdentificacion(txtNumeroIdentificacion.getText());
        funcionario.setNombres(txtNombres.getText());
        funcionario.setApellidos(txtApellidos.getText());
        funcionario.setEstadoCivil((String) comboEstadoCivil.getSelectedItem());
        funcionario.setSexo((String) comboSexo.getSelectedItem());
        funcionario.setDireccion(txtDireccion.getText());
        funcionario.setTelefono(txtTelefono.getText());
        funcionario.setFechaNacimiento(new java.sql.Date(((java.util.Date) spinnerFechaNacimiento.getValue()).getTime())); // Convierte a java.sql.Date
    
        // Aquí llamas a tu método para actualizar el funcionario
        try {
            FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl(); // Cambia a la implementación
    
            funcionarioDAO.ActualizarFuncionario(funcionario); // Actualiza el funcionario en la base de datos
            JOptionPane.showMessageDialog(this, "Funcionario actualizado con éxito.");
        } catch (Exception e) {
            e.printStackTrace(); // Maneja el error adecuadamente
            JOptionPane.showMessageDialog(this, "Error al actualizar el funcionario.");
        }
    
        dispose(); // Cierra la ventana después de actualizar
    }
}

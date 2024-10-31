package gestionrh.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gestionrh.Model.Funcionario;
import gestionrh.dao.FuncionarioDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CrearFuncionarioFrame extends JFrame {
    // Componentes de la interfaz
    private JTextField txtTipoIdentificacion;
    private JTextField txtNumeroIdentificacion;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtEstadoCivil;
    private JTextField txtSexo;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtFechaNacimiento;

    private FuncionarioDAOImpl funcionarioDAO;

    public CrearFuncionarioFrame() {
        funcionarioDAO = new FuncionarioDAOImpl(); // Inicializar el DAO
        initComponents();
    }

    private void initComponents() {
        // Crear etiquetas y campos de texto
        JLabel lblTipoIdentificacion = new JLabel("Tipo de Identificación:");
        txtTipoIdentificacion = new JTextField(20);

        JLabel lblNumeroIdentificacion = new JLabel("Número de Identificación:");
        txtNumeroIdentificacion = new JTextField(20);

        JLabel lblNombres = new JLabel("Nombres:");
        txtNombres = new JTextField(20);

        JLabel lblApellidos = new JLabel("Apellidos:");
        txtApellidos = new JTextField(20);

        JLabel lblEstadoCivil = new JLabel("Estado Civil:");
        txtEstadoCivil = new JTextField(20);

        JLabel lblSexo = new JLabel("Sexo:");
        txtSexo = new JTextField(20);

        JLabel lblDireccion = new JLabel("Dirección:");
        txtDireccion = new JTextField(20);

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField(20);

        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        txtFechaNacimiento = new JTextField(20);

        JButton btnGuardar = new JButton("Guardar");

        // Configurar el layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Layout horizontal
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoIdentificacion)
                    .addComponent(lblNumeroIdentificacion)
                    .addComponent(lblNombres)
                    .addComponent(lblApellidos)
                    .addComponent(lblEstadoCivil)
                    .addComponent(lblSexo)
                    .addComponent(lblDireccion)
                    .addComponent(lblTelefono)
                    .addComponent(lblFechaNacimiento)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipoIdentificacion)
                    .addComponent(txtNumeroIdentificacion)
                    .addComponent(txtNombres)
                    .addComponent(txtApellidos)
                    .addComponent(txtEstadoCivil)
                    .addComponent(txtSexo)
                    .addComponent(txtDireccion)
                    .addComponent(txtTelefono)
                    .addComponent(txtFechaNacimiento)
                    .addComponent(btnGuardar)
                )
        );

        // Layout vertical
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoIdentificacion)
                    .addComponent(txtTipoIdentificacion)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroIdentificacion)
                    .addComponent(txtNumeroIdentificacion)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombres)
                    .addComponent(txtNombres)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidos)
                    .addComponent(txtApellidos)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstadoCivil)
                    .addComponent(txtEstadoCivil)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(txtSexo)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(txtDireccion)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(txtTelefono)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtFechaNacimiento)
                )
                .addComponent(btnGuardar)
        );

        // Evento del botón "Guardar"
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void guardarDatos() {
        Funcionario funcionario = new Funcionario();
        funcionario.setTipoIdentificacion(txtTipoIdentificacion.getText());
        funcionario.setNumeroIdentificacion(txtNumeroIdentificacion.getText());
        funcionario.setNombres(txtNombres.getText());
        funcionario.setApellidos(txtApellidos.getText());
        funcionario.setEstadoCivil(txtEstadoCivil.getText());
        funcionario.setSexo(txtSexo.getText());
        funcionario.setDireccion(txtDireccion.getText());
        funcionario.setTelefono(txtTelefono.getText());
        
        // Parsear la fecha de nacimiento desde el texto
        String fechaStr = txtFechaNacimiento.getText();
        try {
            java.util.Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
            funcionario.setFechaNacimiento(fechaNacimiento);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Usa 'yyyy-MM-dd'.");
            return;
        }

        // Guardar el funcionario en la base de datos
        funcionarioDAO.CrearFuncionario(funcionario);
        JOptionPane.showMessageDialog(this, "Funcionario guardado exitosamente.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CrearFuncionarioFrame::new);
    }
}

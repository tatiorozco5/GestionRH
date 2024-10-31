package gestionrh.Model;

import java.util.Date;

public class Funcionario {
    private int id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String estadoCivil;
    private String sexo;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;

    public Funcionario(int id, String tipoIdentificacion, String numeroIdentificacion, String nombres, String apellidos, String estadoCivil, String sexo, String direccion, String telefono, Date fechaNacimiento) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Funcionario() {}

    
    public int getId() { return id; }
    public void setId(int funcionarioId) { this.id = funcionarioId; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        return "Funcionario ID: " + id +
               "\nTipo de Identificacion: " + tipoIdentificacion +
               "\nNumero de Identificacion: " + numeroIdentificacion +
               "\nNombres: " + nombres +
               "\nApellidos: " + apellidos +
               "\nEstado Civil: " + estadoCivil +
               "\nSexo: " + sexo +
               "\nDireccion: " + direccion +
               "\nTelefono: " + telefono +
               "\nFecha de Nacimiento: " + fechaNacimiento +
               "\n-------------------------------------------\n";
    }
}





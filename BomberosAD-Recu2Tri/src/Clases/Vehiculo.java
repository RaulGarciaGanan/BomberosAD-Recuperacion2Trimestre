package Clases;

public class Vehiculo {
    private String codigo;
    private String matricula;
    private String tipo;
    private String modelo;
    private String disponibilidad;
    private Boolean baja;

    public Vehiculo(String codigo, String matricula, String tipo, String modelo, String disponibilidad, Boolean baja) {
        this.codigo = codigo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.modelo = modelo;
        this.disponibilidad = disponibilidad;
        this.baja = baja;
    }

    public Vehiculo(String codigo, String matricula, String tipo, String modelo, String disponibilidad) {
        this.codigo = codigo;
        this.matricula = matricula;
        this.tipo = tipo;
        this.modelo = modelo;
        this.disponibilidad = disponibilidad;
    }

    public Vehiculo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        return codigo + " " + matricula + " " + tipo + " " + modelo + " " + disponibilidad + ", baja = " + baja  ;
    }
}

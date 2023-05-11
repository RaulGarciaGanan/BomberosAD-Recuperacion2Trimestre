package Clases;

public class Bombero {
    private String codigo;
    private String nombre;
    private String apellidos;
    private int edad;
    private String estado_actual;
    private String rango;
    private Boolean baja;

    public Bombero(String codigo, String nombre, String apellidos, int edad, String estado_actual, String rango, Boolean baja) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.estado_actual = estado_actual;
        this.rango = rango;
        this.baja = baja;
    }

    public Bombero(String codigo, String nombre, String apellidos, int edad, String estado_actual, String rango) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.estado_actual = estado_actual;
        this.rango = rango;
    }

    public Bombero(String codigo) {
        this.codigo = codigo;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        return codigo + " " + nombre + " " + apellidos + " " + edad + " " + estado_actual + " " + rango + ", baja = " + baja;
    }
}

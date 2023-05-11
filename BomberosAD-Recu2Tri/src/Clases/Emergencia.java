package Clases;

public class Emergencia {
    private String codigo;
    private String ubicacion;
    private String ciudad;
    private String tipo;
    private String estado_actual;
    private String gravedad;

    private boolean baja;

    public Emergencia(String codigo, String ubicacion, String ciudad, String tipo, String estado_actual, String gravedad, boolean baja) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.estado_actual = estado_actual;
        this.gravedad = gravedad;
        this.baja = baja;
    }

    public Emergencia(String codigo, String ubicacion, String ciudad, String tipo, String estado_actual, String gravedad) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.estado_actual = estado_actual;
        this.gravedad = gravedad;
    }

    public Emergencia(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado_actual() {
        return estado_actual;
    }

    public void setEstado_actual(String estado_actual) {
        this.estado_actual = estado_actual;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }

    @Override
    public String toString() {
        return codigo + " " + ubicacion + " " + ciudad + " " + tipo + " " + estado_actual + " " + gravedad + ", baja = " + baja;
    }
}

package Clases;

import java.sql.Time;
import java.util.Date;

public class gestionParque {
    private String codBombero;
    private String codVehiculo;
    private String codEmergencia;
    private Date fecha;
    private Time hora;

    public gestionParque(String codBombero, String codVehiculo, String codEmergencia, Date fecha, Time hora) {
        this.codBombero = codBombero;
        this.codVehiculo = codVehiculo;
        this.codEmergencia = codEmergencia;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCodBombero() {
        return codBombero;
    }

    public void setCodBombero(String codBombero) {
        this.codBombero = codBombero;
    }

    public String getCodVehiculo() {
        return codVehiculo;
    }

    public void setCodVehiculo(String codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public String getCodEmergencia() {
        return codEmergencia;
    }

    public void setCodEmergencia(String codEmergencia) {
        this.codEmergencia = codEmergencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return
                "codBombero = " + codBombero +
                " ,codVehiculo = " + codVehiculo +
                " ,codEmergencia = " + codEmergencia +
                " ,fecha = " + fecha +
                " ,hora = " + hora ;
    }
}

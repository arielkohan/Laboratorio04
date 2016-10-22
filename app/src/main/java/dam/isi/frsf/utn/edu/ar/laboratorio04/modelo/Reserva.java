package dam.isi.frsf.utn.edu.ar.laboratorio04.modelo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by mdominguez on 22/09/16.
 */
public class Reserva implements Serializable {
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static int lastId = 0;
    private Integer id = 0;
    private Date fechaInicio = new Date(1900,01,01,00,00);
    private Date fechaFin = new Date();
    private Departamento departamento;
    private Double precio;
    private Usuario usuario;
    private Boolean confirmada = false;

    public Reserva(){}

    public Reserva(int id, Date fechaInicio, Date fechaFin, Departamento departamento){
        this.id = ++lastId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.departamento = departamento;
        calcularPrecio();
    }

    public Reserva(Date fechaInicio, Date fechaFin, Departamento departamento){
        this.id = ++lastId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.departamento = departamento;
        calcularPrecio();
    }

    public Reserva(Date fechaInicio, Date fechaFin, Departamento departamento, Usuario usuario) {
        this.id = ++lastId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.departamento = departamento;
        this.usuario = usuario;
        calcularPrecio();
    }

    public Reserva(int i, Date fechaInicio, Date fechaFin, Departamento aux, Usuario instance) {
        this(fechaInicio, fechaFin, aux, instance);
    }

    private void calcularPrecio() {
        int days = (int) ((fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24));
        this.precio = departamento.getPrecio() * days;
    }

    @Override
    public String toString() {
        return "Reserva ID: " + this.id + ". Fecha Inicio: " + fechaInicio + ". Fecha fin: " + fechaFin + ". Departamento numero: " +
                departamento.getId() + ". Precio: $" + df.format(precio) + ". Confirmada: " + (confirmada? "Sí." : "No.");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Departamento getAlojamiento() {
        return departamento;
    }

    public void setAlojamiento(Departamento alojamiento) {
        this.departamento = alojamiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;


    }


    public Boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Boolean confirmada) {
        this.confirmada = confirmada;
    }

}

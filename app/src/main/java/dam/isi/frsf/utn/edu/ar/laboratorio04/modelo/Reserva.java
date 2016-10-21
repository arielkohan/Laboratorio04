package dam.isi.frsf.utn.edu.ar.laboratorio04.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mdominguez on 22/09/16.
 */
public class Reserva implements Serializable {
    public static int lastId = 0;
    private Integer id;
    private Date fechaInicio;
    private Date fechaFin;
    private Departamento departamento;
    private Double precio;
    private Usuario usuario;
    private Boolean confirmada;

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

    private void calcularPrecio() {
        int days = (int) ((fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24));
        this.precio = departamento.getPrecio() * days;
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

    public Departamento  getAlojamiento() {
        return departamento;
    }

    public void setAlojamiento(Departamento  alojamiento) {
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

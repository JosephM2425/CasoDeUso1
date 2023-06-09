package cr.ac.ucenfotec.bl.entities;

import java.util.Date;

public class Prestamo {
    private int id;
    private Libro libro;
    private Usuario usuario;
    private Date fecha_prestamo;
    private Date fecha_vencimiento;
    private Date fecha_devolucion;

    public Prestamo(int id, Libro libro, Usuario usuario, Date fecha_prestamo, Date fecha_vencimiento, Date fecha_devolucion) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }
}

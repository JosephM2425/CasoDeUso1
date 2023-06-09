package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;

/**
 * @author Joseph Murillo
 * @version 1.0
 * @since 08/06/2023
 *
 * Esta clase se encarga de gestionar todos los objetos Prestamo
 */
public class Prestamo {
    /**
     * Declaracion de atributos del objeto
     */
    private int id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fecha_prestamo;
    private LocalDate fecha_vencimiento;
    private LocalDate fecha_devolucion;

    //Seteo de los constructores

    /**
     * Este es el constructor por defecto
     */
    public Prestamo() {

    }

    public Prestamo(int id, Libro libro, Usuario usuario, LocalDate fecha_prestamo, LocalDate fecha_vencimiento) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    /**
     * Crea una nueva instancia de la clase Prestamo
     *
     * @param id es de tipo int y corresponde al ID del préstamo
     * @param libro es de tipo Libro y corresponde al libro prestado en el préstamo
     * @param usuario es de tipo Usuario y corresponde al usuario que solicita el préstamo
     * @param fecha_prestamo es de tipo LocalDate y corresponde a la fecha en que se realiza el préstamo
     * @param fecha_vencimiento es de tipo LocalDate y corresponde a la fecha límite para la devolución del libro
     * @param fecha_devolucion es de tipo LocalDate y corresponde a la fecha en que se devuelve el libro
     */
    public Prestamo(int id, Libro libro, Usuario usuario, LocalDate fecha_prestamo, LocalDate fecha_vencimiento, LocalDate fecha_devolucion) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Getters y setters de los atributos del objeto
     */
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

    public LocalDate getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(LocalDate fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    /**
     * Metodo que devuelve los atributos en formato String
     * @return devuelve todos los atributos del objeto en formato String
     */
    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", libro=" + libro +
                ", usuario=" + usuario +
                ", fecha_prestamo=" + fecha_prestamo +
                ", fecha_vencimiento=" + fecha_vencimiento +
                ", fecha_devolucion=" + fecha_devolucion +
                '}';
    }
}

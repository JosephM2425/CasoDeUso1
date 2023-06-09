package cr.ac.ucenfotec.bl.entities;

/**
 * @author Joseph Murillo
 * @version 1.0
 * @since 08/06/2023
 *
 * Esta clase se encarga de gestionar todos los objetos Autor
 */
public class Autor {
    /**
     * Declaracion de atributos del objeto
     */
    private int id;
    private String nombre;

    //Seteo de los constructores

    /**
     * Este es el constructor por defecto
     */
    public Autor() {
    }

    public Autor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuelve los atributos en formato String
     * @return devuelve todos los atributos del objeto en formato String
     */
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

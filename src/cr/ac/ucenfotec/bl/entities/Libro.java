package cr.ac.ucenfotec.bl.entities;

/**
 * @author Joseph Murillo
 * @version 1.0
 * @since 08/06/2023
 *
 * Esta clase se encarga de gestionar todos los objetos Libro
 */
public class Libro {
    /**
     * Declaracion de atributos del objeto
     */
    private int id;
    private String titulo;
    private Boolean estado;
    private Autor autor;
    private Categoria categoria;

    //Seteo de los constructores

    /**
     * Este es el constructor por defecto
     */
    public Libro() {
    }

    /**
     * Crea una nueva instancia de la clase Libro
     *
     * @param id es de tipo int y corresponde al ID del libro
     * @param titulo es de tipo String y corresponde al título del libro
     * @param estado es de tipo Boolean y corresponde al estado del libro
     * @param autor es de tipo Autor y corresponde al autor del libro
     * @param categoria es de tipo Categoria y corresponde a la categoría del libro
     */
    public Libro(int id, String titulo, Boolean estado, Autor autor, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.estado = estado;
        this.autor = autor;
        this.categoria = categoria;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo que devuelve los atributos en formato String
     * @return devuelve todos los atributos del objeto en formato String
     */
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", estado=" + estado +
                ", autor=" + autor +
                ", categoria=" + categoria +
                '}';
    }
}

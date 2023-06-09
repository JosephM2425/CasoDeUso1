package cr.ac.ucenfotec.bl.entities;

public class Libro {
    private int id;
    private String nombre;
    private boolean estado;
    private Autor autor;
    private Categoria categoria;

    public Libro() {
    }
    public Libro(int id, String nombre, boolean estado, Autor autor, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.autor = autor;
        this.categoria = categoria;
    }

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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
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
}

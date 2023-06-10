package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.AutorDAO;
import cr.ac.ucenfotec.bl.DAO.LibroDAO;
import cr.ac.ucenfotec.bl.entities.Autor;
import cr.ac.ucenfotec.bl.entities.Categoria;
import cr.ac.ucenfotec.bl.entities.Libro;

import java.util.ArrayList;

public class LibroGestor {
    private LibroDAO libroDAO;
    private AutorGestor autorGestor;
    private CategoriaGestor categoriaGestor;

    public LibroGestor() {
        this.libroDAO = new LibroDAO();
        this.autorGestor = new AutorGestor();
        this.categoriaGestor = new CategoriaGestor();
    }

    public String agregarLibro(String titulo, String autorNombre, String categoriaNombre) {
        Autor autor =  autorGestor.buscarAutorPorNombre(autorNombre);
        Categoria categoria = categoriaGestor.buscarCategoriaPorNombre(categoriaNombre);

        if (autor != null && categoria != null) {
            Libro libro = new Libro(titulo,autor, categoria);
            int resultado = libroDAO.agregarLibro(libro);
            if (resultado == 0) {
                return "Registro Exitoso!";
            } else {
                return "Hubo un error en el registro, porfavor verifique los datos";
            }
        } else {
            return "Hubo un error en el registro, porfavor verifique los datos";
        }

    }

    public String agregarLibro(String titulo, int autorId, int categoriaId) {
        Autor autor =  autorGestor.buscarAutorPorID(autorId);
        Categoria categoria = categoriaGestor.buscarCategoriaPorId(categoriaId);

        if (autor != null && categoria != null) {
            Libro libro = new Libro(titulo,autor, categoria);
            int resultado = libroDAO.agregarLibro(libro);
            if (resultado == 0) {
                return "Registro Exitoso!";
            } else {
                return "Hubo un error en el registro, porfavor verifique los datos";
            }
        } else {
            return "Hubo un error en el registro, porfavor verifique los datos";
        }

    }

    public ArrayList<Libro> listarLibros() {
        return libroDAO.listarLibros();
    }

    public Libro buscarLibroPorID (int libroID) {
        return libroDAO.buscarLibro(libroID);
    }

    public Libro buscarLibroPorTitulo (String tituloLibro) {
        return libroDAO.buscarLibro(tituloLibro);
    }

    public String eliminarLibro (int libroID) {
        Libro libro = libroDAO.buscarLibro(libroID);
        if (libro != null) {
            libroDAO.eliminarLibro(libro);
        } else {
            return "El libro por eliminar no existe";
        }
        if (libroDAO.buscarLibro(libroID) == null) {
            return "El libro \"" + libro.getTitulo() + "\" ha sido eliminado con exito!";
        } else {
            return "El libro \"" + libro.getTitulo() + "\" no pudo ser eliminado";
        }
    }

    public String modificarLibro (int idLibro, String titulo, Boolean estado, int idAutor, int idCategoria) {
        Libro libro = libroDAO.buscarLibro(idLibro);
        if (libro != null) {
            if (!titulo.equals("")) {
                libro.setTitulo(titulo);
            }
            if (estado) {
                libro.setEstado(estado);
            }

            if (idAutor != 0) {
                libro.setAutor(autorGestor.buscarAutorPorID(idAutor));
            }

            if (idCategoria != 0) {
                libro.setCategoria(categoriaGestor.buscarCategoriaPorId(idCategoria));
            }

            libroDAO.modificarLibro(libro);

            libro = libroDAO.buscarLibro(idLibro);
            return "Cambio realizado!" +
                    "\n- ID: " + libro.getId() +
                    "\n- Titulo: " + libro.getTitulo() +
                    "\n- Estado: " + libro.getEstado() +
                    "\n- Autor: " + libro.getAutor().getNombre() +
                    "\n- Categoria: " + libro.getCategoria().getNombre();
        } else {
            return "No se encontro el libro con el ID: " + idLibro;
        }
    }


}

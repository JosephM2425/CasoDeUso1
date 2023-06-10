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

    public String modificarLibro (int idLibro, Boolean estadoPorCambiar) {
        Libro libro = libroDAO.buscarLibro(idLibro);

        if (libro != null) {
            libro.setEstado(estadoPorCambiar);
            System.out.println(libro.toString());
            libroDAO.modificarLibro(libro);
            libro = libroDAO.buscarLibro(idLibro);
            return "Cambio realizado!" +
                    "\n- ID: " + libro.getId() +
                    "\n- Titulo: " + libro.getTitulo() +
                    "\n- Estado: " + libro.getEstado();
        } else {
            return "Hubo un error, porfavor verifique los datos ingresados";
        }
    }


}

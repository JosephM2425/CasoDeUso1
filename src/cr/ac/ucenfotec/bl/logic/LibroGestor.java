package cr.ac.ucenfotec.bl.logic;


import cr.ac.ucenfotec.bl.DAO.LibroDAO;
import cr.ac.ucenfotec.bl.entities.Autor;
import cr.ac.ucenfotec.bl.entities.Categoria;
import cr.ac.ucenfotec.bl.entities.Libro;

import java.util.ArrayList;

/**
 * Clase LibroGestor que se encarga de la gestión de libros
 * @version 1.0
 * @since 06/06/2022
 * @author Andres Soza
 */
public class LibroGestor {
    private LibroDAO libroDAO;
    private AutorGestor autorGestor;
    private CategoriaGestor categoriaGestor;

    public LibroGestor() {
        this.libroDAO = new LibroDAO();
        this.autorGestor = new AutorGestor();
        this.categoriaGestor = new CategoriaGestor();
    }

    public String agregarLibro(Libro libro) {
        if (libro != null) {
            if(!existeLibro(libro.getTitulo())) {
                Autor autor = new Autor(libro.getAutor().getNombre());
                Categoria categoria = new Categoria(libro.getCategoria().getNombre());

                if (autorGestor.buscarAutorPorNombre(libro.getAutor().getNombre()) == null) {
                    autorGestor.agregarAutor(autor);
                }

                if (categoriaGestor.buscarCategoriaPorNombre(libro.getCategoria().getNombre()) == null) {
                    categoriaGestor.agregarCategoria(categoria);
                }

                Autor autorGenerado = autorGestor.buscarAutorPorNombre(libro.getAutor().getNombre());
                Categoria categoriaGenerada = categoriaGestor.buscarCategoriaPorNombre(libro.getCategoria().getNombre());
                libro.setAutor(autorGenerado);
                libro.setCategoria(categoriaGenerada);

                int resultado = libroDAO.agregarLibro(libro);
                if (resultado == 0) {
                    return "Registro exitoso!";
                } else {
                    return "Hubo un error en el registro, por favor verifique los datos";
                }
            } else {
                return "Ya existe un libro con ese título.";
            }
        } else {
            return "El libro no puede ser nulo.";
        }
    }

    public String modificarLibro(Libro libro) {
        if (libro != null) {
            if(existeLibro(libro.getId())) {
                Autor autor = new Autor(libro.getAutor().getNombre());
                Categoria categoria = new Categoria(libro.getCategoria().getNombre());

                if (!autorGestor.existeAutor(libro.getAutor().getNombre())) {
                    autorGestor.agregarAutor(autor);
                } else {
                    autor = autorGestor.buscarAutorPorNombre(libro.getAutor().getNombre());
                }

                if (!categoriaGestor.existeCategoria(libro.getCategoria().getNombre())) {
                    categoriaGestor.agregarCategoria(categoria);
                } else {
                    categoria = categoriaGestor.buscarCategoriaPorNombre(libro.getCategoria().getNombre());
                }

                libro.setAutor(autor);
                libro.setCategoria(categoria);

                int resultado = libroDAO.modificarLibro(libro);
                if (resultado == 0) {
                    return "Modificacón exitosa!";
                } else {
                    return "Hubo un error en la modificacón, por favor verifique los datos.";
                }
            } else {
                return "No existe un libro con ese título.";
            }
        } else {
            return "El libro no puede ser nulo.";
        }
    }

    public String modificarLibro(Libro libro, Boolean estado) {
        if (libro != null) {
            if(existeLibro(libro.getId())) {
                int resultado = libroDAO.modificarLibro(libro, estado);
                if (resultado == 0) {
                    return "Modificacón exitosa!";
                } else {
                    return "Hubo un error en la modificacón, por favor verifique los datos.";
                }
            } else {
                return "No existe un libro con ese título.";
            }
        } else {
            return "El libro no puede ser nulo.";
        }
    }

    public ArrayList<Libro> listarLibros() {
        return libroDAO.listarLibros();
    }

    public ArrayList<Libro> listarLibros(Boolean estado) {
        return libroDAO.listarLibros(estado);
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

    public Boolean existeLibro (String tituloLibro) {
        Boolean existeLibro = false;
        if(libroDAO.buscarLibro(tituloLibro) != null) {
            existeLibro = true;
        } else {
            existeLibro = false;
        }
        return existeLibro;
    }

    public Boolean existeLibro (int idLibro) {
        Boolean existeLibro = false;
        if(libroDAO.buscarLibro(idLibro) != null) {
            existeLibro = true;
        } else {
            existeLibro = false;
        }
        return existeLibro;
    }

}

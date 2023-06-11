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

                libro.toString();

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

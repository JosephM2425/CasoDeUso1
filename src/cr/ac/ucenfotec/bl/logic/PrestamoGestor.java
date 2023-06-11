package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.PrestamoDAO;
import cr.ac.ucenfotec.bl.entities.Libro;
import cr.ac.ucenfotec.bl.entities.Prestamo;
import cr.ac.ucenfotec.bl.entities.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase PrestamoGestor que se encarga de la gestión de prestamos
 * @version 1.0
 * @since 06/06/2022
 * @author Andres Soza
 */
public class PrestamoGestor {
    private PrestamoDAO prestamoDAO;
    private LibroGestor libroGestor;
    private UsuarioGestor usuarioGestor;

    public PrestamoGestor() {
        this.prestamoDAO = new PrestamoDAO();
        this.libroGestor = new LibroGestor();
        this.usuarioGestor = new UsuarioGestor();
    }

    public String registrarPrestamo(Prestamo prestamo) {

        if (prestamo != null) {
            int resultado = prestamoDAO.registrarPrestamo(prestamo);

            if (resultado == 0) {
                return "Prestamo registrado con exito!";
            } else {
                return "Hubo un error, revisa los datos!";
            }
        } else {
            return "El prestamo no puede ser nulo.";
        }
    }

    public String registrarPrestamo(String tituloLibro, String nombreUsuario, LocalDate fecha_vencimiento) {
        Libro libro = libroGestor.buscarLibroPorTitulo(tituloLibro);
        Usuario usuario = usuarioGestor.buscarUsuarioPorNombreUsuario(nombreUsuario);

        if (libro != null && usuario != null) {
            Prestamo prestamo = new Prestamo(libro, usuario, LocalDate.now(), fecha_vencimiento);
            int resultado = prestamoDAO.registrarPrestamo(prestamo);

            if (resultado == 0) {
                return "Prestamo registrado con exito!";
            } else {
                return "Hubo un error, revisa los datos!";
            }
        } else {
            if (libro == null) {
                return "No se encontro el libro mencionado";
            } else if (usuario == null) {
                return "No se encontro el usuario mencionado";
            } else {
                return "Revisa el libro y usuario";
            }
        }
    }

    public String registrarPrestamo(int idLibro, String nombreUsuario, LocalDate fecha_vencimiento) {
        Libro libro = libroGestor.buscarLibroPorID(idLibro);
        Usuario usuario = usuarioGestor.buscarUsuarioPorNombreUsuario(nombreUsuario);

        if (libro != null && usuario != null) {
            Prestamo prestamo = new Prestamo(libro, usuario, LocalDate.now(), fecha_vencimiento);
            int resultado = prestamoDAO.registrarPrestamo(prestamo);

            if (resultado == 0) {
                return "Prestamo registrado con exito!";
            } else {
                return "Hubo un error, revisa los datos!";
            }
        } else {
            if (libro == null) {
                return "No se encontro el libro mencionado";
            } else if (usuario == null) {
                return "No se encontro el usuario mencionado";
            } else {
                return "Revisa el libro y usuario";
            }
        }
    }

    public ArrayList<Prestamo> listarPrestamos() {
        return prestamoDAO.listarPrestamos();
    }

    public ArrayList<Prestamo> listarLibrosPrestados() { return prestamoDAO.listarLibrosPrestados(); }

    public ArrayList<Prestamo> listarLibrosPrestados(String nombreUsuario) { return prestamoDAO.listarLibrosPrestados(nombreUsuario); }

    public String modificarPrestamo(int idPrestamo, LocalDate fechaDevolucionModificar) {
        Prestamo prestamo = prestamoDAO.buscarPrestamo(idPrestamo);

        if (prestamo != null) {
            prestamo.setFecha_devolucion(fechaDevolucionModificar);
            prestamoDAO.modificarPrestamo(prestamo);
            prestamo = prestamoDAO.buscarPrestamo(idPrestamo);
            return "Libro devuelto con exito!";
        } else {
            return "El prestamo no existe";
        }
    }

    public Prestamo buscarPrestamo(int idPrestamo) {
        return prestamoDAO.buscarPrestamo(idPrestamo);
    }

    public Prestamo buscarPrestamoVigente(Libro libro, Usuario usuario) {
        return prestamoDAO.buscarPrestamoVigente(libro, usuario);
    }

    public String eliminarPrestamo(int idPrestamo) {
        Prestamo prestamo = prestamoDAO.buscarPrestamo(idPrestamo);
        prestamoDAO.eliminarPrestamo(prestamo);

        if (prestamoDAO.buscarPrestamo(idPrestamo) ==  null) {
            return "Prestamo eliminado!";
        } else {
            return "Prestamo no pudo ser eliminado!";
        }
    }

}

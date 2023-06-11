package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.entities.Libro;
import cr.ac.ucenfotec.bl.entities.Prestamo;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.logic.PrestamoGestor;
import cr.ac.ucenfotec.bl.logic.LibroGestor;
import cr.ac.ucenfotec.bl.logic.UsuarioGestor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase PrestamoUI que imprime el menú de opciones de prestamos y realiza la opción elegida por el usuario
 * @version 1.0
 * @since 10/06/2022
 * @author Carolina Arias
 */
public class PrestamoUI {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;
    public static PrestamoGestor prestamoGestor = new PrestamoGestor();
    public static LibroGestor libroGestor = new LibroGestor();
    public static UsuarioGestor usuarioGestor = new UsuarioGestor();

    /**
    * Método que imprime el menú de opciones de prestamos
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
    */
    static void impMenuPrestamos() throws IOException {
        out.println("[1] Listar libros prestados");
        out.println("[2] Libros prestados por usuario");
        out.println("[3] Libros disponibles");
        out.println("[4] Prestar un libro");
        out.println("[5] Devolver un libro");
        out.println("[6] Regresar");
        out.print("Digite la opción que desea -> ");
    }

    /**
     * Método que realiza la opción elegida por el usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void menuPrestamos(int opcion) throws IOException{
        switch (opcion) {
            case 1:
                Main.header();
                listarLibrosPrestados();
                break;
            case 2:
                Main.header();
                librosPrestadosAUsuario();
                break;
            case 3:
                Main.header();
                listarLibrosDisponibles();
                break;
            case 4:
                Main.header();
                prestarLibro();
                break;
            case 5:
                Main.header();
                devolverLibro();
                break;
                case 6:
                Main.menuPrincipalCompleto();
                break;
            default:
                out.println("Opción inválida");
                break;
        }
    }

    /**
     * Método que realiza la funcionalidad completa de prestamos
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void menuPrestamosCompleto() throws IOException {
        int opcion = 0;
        do {
            Main.header();
            impMenuPrestamos();
            opcion = Main.leerOpcion();
            menuPrestamos(opcion);
        } while (opcion != 6);
    }


    /**
     * Método que presta un libro
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void prestarLibro() throws IOException {
        int opcion = 0;

        out.println("Digite el libro que desea prestar -> ");

        do {
            LibroUI.listarLibrosSoloTitulo(false);
            out.print("Digite la opción que desea -> ");
            opcion = Main.leerOpcion();
            if(opcion > LibroUI.cantidadDeLibros(false) || opcion < 1) {
                out.println("Opción inválida.");
                Main.esperarTecla();
            }
        } while (opcion > LibroUI.cantidadDeLibros(false) || opcion < 1);

        Libro libro = LibroUI.devolverLibroDisponible(opcion);

        opcion = 0;
        out.println("Digite el usuario al que desea prestar el libro -> ");
        do {
            UsuarioUI.listarUsuarios();
            out.print("Digite la opción que desea -> ");
            opcion = Main.leerOpcion();
            if(opcion > UsuarioUI.cantidadDeUsuarios() || opcion < 1) {
                out.println("Opción inválida.");
                Main.esperarTecla();
            }
        } while (opcion > UsuarioUI.cantidadDeUsuarios() || opcion < 1);

        Usuario usuario = UsuarioUI.devolverUsuarioSeleccionado(opcion);
        libroGestor.modificarLibro(libro, true);
        Prestamo prestamo = new Prestamo(libro, usuario, LocalDate.now(), LocalDate.now().plusDays(7));
        String resultado = prestamoGestor.registrarPrestamo(prestamo);
        out.println(resultado);
        Main.esperarTecla();
    }

    /**
     * Método que devuelve un libro prestado
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void devolverLibro() throws IOException {
        int opcion = 0;

        out.println("Digite el usuario que desea devolver el libro -> ");
        do {
            UsuarioUI.listarUsuarios();
            out.print("Digite la opción que desea -> ");
            opcion = Main.leerOpcion();
            if(opcion > UsuarioUI.cantidadDeUsuarios() || opcion < 1) {
                out.println("Opción inválida.");
                Main.esperarTecla();
            }
        } while (opcion > UsuarioUI.cantidadDeUsuarios() || opcion < 1);

        Usuario usuario = UsuarioUI.devolverUsuarioSeleccionado(opcion);
        String nombreUsuario = usuario.getNombre_usuario();
        int cantidadLibrosPrestados = cantidadDeLibrosPrestados(nombreUsuario);

        if (cantidadLibrosPrestados > 0) {
            opcion = 0;
            out.println("Digite el libro que desea devolver -> ");

            do {
                listarLibrosPrestados(nombreUsuario);
                out.print("Digite la opción que desea -> ");
                opcion = Main.leerOpcion();
                if(opcion > cantidadLibrosPrestados || opcion < 1) {
                    out.println("Opción inválida.");
                    Main.esperarTecla();
                }
            } while (opcion > cantidadLibrosPrestados || opcion < 1);
        } else {
            listarLibrosPrestados(nombreUsuario);
        }

        if (cantidadLibrosPrestados > 0) {
            Libro libro = obtenerLibroPrestado(opcion, nombreUsuario);
            libroGestor.modificarLibro(libro, false);
            Prestamo prestamo = prestamoGestor.buscarPrestamoVigente(libro, usuario);
            String resultado = prestamoGestor.modificarPrestamo(prestamo.getId(), LocalDate.now());
            out.println(resultado);
            Main.esperarTecla();
        }
    }

    /**
     * Método que imprime los libros disponibles
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void listarLibrosDisponibles() throws IOException {
        ArrayList<Libro> libros = libroGestor.listarLibros(false);

        if(!libros.isEmpty()) {
            for (Libro libro : libros) {
                out.printf("[%d] Título: %s. \n", libros.indexOf(libro) + 1 , libro.getTitulo());
                out.printf("     Autor: %s. \n", libro.getAutor().getNombre());
            }
            Main.esperarTecla();
        } else {
            out.println("No hay libros disponibles.");
            Main.esperarTecla();
        }
    }

    /**
     * Método que imprime los libros prestados
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void listarLibrosPrestados() throws IOException {
        ArrayList<Prestamo> prestamos = prestamoGestor.listarLibrosPrestados();

        if(!prestamos.isEmpty()) {
            for (Prestamo prestamo : prestamos) {
                out.printf("[%d] Título: %s. \n", prestamos.indexOf(prestamo) + 1 , prestamo.getLibro().getTitulo());
                out.printf("     Usuario: %s. \n", prestamo.getUsuario().getNombre_completo());
                out.printf("     Fecha de vencimiento: %s. \n", prestamo.getFecha_vencimiento());
            }
            Main.esperarTecla();
        } else {
            out.println("No hay libros prestados.");
            Main.esperarTecla();
        }
    }

    /**
     * Método que imprime los libros prestados a un usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void librosPrestadosAUsuario() throws IOException {
        int opcion = 0;
        do {
            UsuarioUI.listarUsuarios();
            out.print("Digite la opción que desea -> ");
            opcion = Main.leerOpcion();
            if(opcion > UsuarioUI.cantidadDeUsuarios() || opcion < 1) {
                out.println("Opción inválida.");
                Main.esperarTecla();
            }
        } while (opcion > UsuarioUI.cantidadDeUsuarios() || opcion < 1);
        String nombreUsuario = UsuarioUI.devolverUsuarioSeleccionado(opcion).getNombre_usuario();
        listarLibrosPrestados(nombreUsuario);
        Main.esperarTecla();
    }

    /**
     * Método que imprime los libros prestados a un usuario
     * @param nombreUsuario es el nombre del usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void listarLibrosPrestados(String nombreUsuario) throws IOException {
        ArrayList<Prestamo> prestamos = prestamoGestor.listarLibrosPrestados(nombreUsuario);
        Usuario usuario = usuarioGestor.buscarUsuarioPorNombreUsuario(nombreUsuario);

        if(!prestamos.isEmpty()) {
            String estadoString =  "";
            out.printf("Libros prestados a: %s. \n", prestamos.get(0).getUsuario().getNombre_completo());
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getLibro().getEstado()) {
                    estadoString = "En préstamo";
                } else {
                    estadoString = "Devuelto";
                }
                out.printf("[%d] Título: %s. | Estado: %s\n", prestamos.indexOf(prestamo) + 1 , prestamo.getLibro().getTitulo(), estadoString);
            }
        } else {
            out.printf("No hay libros prestados a: %s. \n", usuario.getNombre_completo());
            Main.esperarTecla();
        }
    }

    /**
     * Método que devuelve la cantidad de libros prestaos a un usuario
     * @return un entero con la cantidad de libros
     */
    static int cantidadDeLibrosPrestados(String nombreUsuario) {
        ArrayList<Prestamo> libros = prestamoGestor.listarLibrosPrestados(nombreUsuario);

        return libros.size();
    }

    /**
     * Método que devuelve el título de un libro prestado
     * @param opcion es de tipo int y es la opción elegida por el usuario
     * @return un objeto de tipo Libro
     */
    static Libro obtenerLibroPrestado (int opcion, String nombreUsuario) throws IOException {
        ArrayList<Prestamo> prestamos = prestamoGestor.listarLibrosPrestados(nombreUsuario);
        Prestamo prestamoSeleccionado = prestamos.get(opcion - 1);
        Libro libro = new Libro();
        libro.setId(prestamoSeleccionado.getLibro().getId());
        return libro;
    }

}

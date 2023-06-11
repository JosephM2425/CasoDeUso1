package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.entities.Libro;
import cr.ac.ucenfotec.bl.entities.Prestamo;
import cr.ac.ucenfotec.bl.logic.PrestamoGestor;
import cr.ac.ucenfotec.bl.logic.LibroGestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;


public class PrestamoUI {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;
    public static PrestamoGestor prestamoGestor = new PrestamoGestor();
    public static LibroGestor libroGestor = new LibroGestor();

    /**
    * Método que imprime el menú de opciones de prestamos
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
    * Método que realiza la opción elegida por el prestamo
    * @param opcion es de tipo int y es la opción elegida por el prestamo
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

                break;
            case 5:
                Main.menuPrincipalCompleto();
                break;
            default:
                out.println("Opción inválida");
                break;
        }
    }

    static void menuPrestamosCompleto() throws IOException {
        int opcion = 0;
        do {
            Main.header();
            impMenuPrestamos();
            opcion = Main.leerOpcion();
            menuPrestamos(opcion);
        } while (opcion != 6);
    }


//    /**
//     * Método que lee los datos de un prestamo
//     * @return un objeto de tipo Prestamo
//     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
//     */
//    static Prestamo leerDatosDePrestamo() throws IOException {
//        out.print("Digite el título del prestamo: ");
//        String tituloPrestamo = in.readLine();
//
//        out.print("Digite el nombre del autor del prestamo: ");
//        String nombreAutor = in.readLine();
//        Autor autor = new Autor();
//        autor.setNombre(nombreAutor);
//
//        out.print("Digite la categoría del prestamo: ");
//        String nombreCategoria = in.readLine();
//        Categoria categoria = new Categoria();
//        categoria.setNombre(nombreCategoria);
//
//        Prestamo prestamo = new Prestamo(tituloPrestamo, autor, categoria);
//        return prestamo;
//    }
//
//    /**
//     * Método que registra un prestamo
//     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
//     */
//    static void registrarPrestamo() throws IOException {
//        String existePrestamo = "Ya existe un prestamo con ese título.";
//        String resultado = "";
//        do {
//        Prestamo prestamo = leerDatosDePrestamo();
//        resultado = prestamoGestor.agregarPrestamo(prestamo);
//        out.println(resultado);
//        Main.esperarTecla();
//        } while (resultado.equals(existePrestamo));
//    }
//
//    /**
//     * Método que modifica un prestamo
//     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
//     */
//    static void modificarPrestamo() throws IOException {
//        listarPrestamosSoloTitulo();
//        out.print("Digite la opción que desea ->  ");
//        int opcion = Main.leerOpcion();
//        Prestamo prestamo = leerDatosDePrestamo();
//        prestamo.setId(devolverTituloPrestamo(opcion).getId());
//        prestamo.setEstado(devolverTituloPrestamo(opcion).getEstado());
//        String resultado = prestamoGestor.modificarPrestamo(prestamo);
//        out.println(resultado);
//        Main.esperarTecla();
//    }
//
//    /**
//     * Método que elimina un prestamo
//     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
//     */
//    static void eliminarPrestamo() throws IOException {
//        listarPrestamosSoloTitulo();
//        out.print("Digite la opción que desea ->  ");
//        int opcion = Main.leerOpcion();
//        Prestamo prestamo = devolverTituloPrestamo(opcion);
//        String resultado = prestamoGestor.eliminarPrestamo(prestamo.getId());
//        out.println(resultado);
//        Main.esperarTecla();
//    }
//
//
//    static void listarPrestamosSoloTitulo() throws IOException {
//        ArrayList<Prestamo> prestamos = prestamoGestor.listarPrestamos();
//
//        if(!prestamos.isEmpty()) {
//            for (Prestamo prestamo : prestamos) {
//                out.printf("[%d] %s. \n", prestamos.indexOf(prestamo) + 1 , prestamo.getTitulo());
//            }
//        } else {
//            out.println("No existen prestamos registrados.");
//            Main.esperarTecla();
//        }
//    }
//
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

    static void librosPrestadosAUsuario() throws IOException {
        String nombreUsuario = UsuarioUI.leerNombreUsuario();
        listarLibrosPrestados(nombreUsuario);
    }

    static void listarLibrosPrestados(String nombreUsuario) throws IOException {
        ArrayList<Prestamo> prestamos = prestamoGestor.listarLibrosPrestados(nombreUsuario);

        if(!prestamos.isEmpty()) {
            out.printf("Libros prestados a: %s. \n", prestamos.get(0).getUsuario().getNombre_completo());
            for (Prestamo prestamo : prestamos) {
                out.printf("[%d] Título: %s. \n", prestamos.indexOf(prestamo) + 1 , prestamo.getLibro().getTitulo());
            }
            Main.esperarTecla();
        } else {
            out.printf("No hay libros prestados a: %s. \n", prestamos.get(0).getUsuario().getNombre_completo());
            Main.esperarTecla();
        }
    }

}

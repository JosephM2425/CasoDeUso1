package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.entities.Autor;
import cr.ac.ucenfotec.bl.entities.Categoria;
import cr.ac.ucenfotec.bl.entities.Libro;
import cr.ac.ucenfotec.bl.logic.LibroGestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;


public class LibroUI {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;
    public static LibroGestor libroGestor = new LibroGestor();

    /**
    * Método que imprime el menú de opciones de libros
    */
    static void impMenuLibros() throws IOException {
        out.println("[1] Registrar libro");
        out.println("[2] Modificar libro");
        out.println("[3] Eliminar libro");
        out.println("[4] Listar libros");
        out.println("[5] Regresar");
        out.print("Digite la opción que desea -> ");
    }

    /**
    * Método que realiza la opción elegida por el libro
    * @param opcion es de tipo int y es la opción elegida por el libro
    */
    static void menuLibros(int opcion) throws IOException{
        switch (opcion) {
            case 1:
                Main.header();
                registrarLibro();
                break;
            case 2:
                Main.header();
                modificarLibro();
                break;
            case 3:
                Main.header();
                eliminarLibro();
                break;
            case 4:
                Main.header();
                listarLibros();
                break;
            case 5:
                Main.menuPrincipalCompleto();
                break;
            default:
                out.println("Opción inválida");
                break;
        }
    }

    static void menuLibrosCompleto() throws IOException {
        int opcion = 0;
        do {
            Main.header();
            impMenuLibros();
            opcion = Main.leerOpcion();
            menuLibros(opcion);
        } while (opcion != 5);
    }


    /**
     * Método que lee los datos de un libro
     * @return un objeto de tipo Libro
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static Libro leerDatosDeLibro() throws IOException {
        out.print("Digite el título del libro: ");
        String tituloLibro = in.readLine();

        out.print("Digite el nombre del autor del libro: ");
        String nombreAutor = in.readLine();
        Autor autor = new Autor();
        autor.setNombre(nombreAutor);

        out.print("Digite la categoría del libro: ");
        String nombreCategoria = in.readLine();
        Categoria categoria = new Categoria();
        categoria.setNombre(nombreCategoria);

        Libro libro = new Libro(tituloLibro, autor, categoria);
        return libro;
    }

    /**
     * Método que registra un libro
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void registrarLibro() throws IOException {
        String existeLibro = "Ya existe un libro con ese título.";
        String resultado = "";
        do {
        Libro libro = leerDatosDeLibro();
        resultado = libroGestor.agregarLibro(libro);
        out.println(resultado);
        Main.esperarTecla();
        } while (resultado.equals(existeLibro));
    }

    /**
     * Método que modifica un libro
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void modificarLibro() throws IOException {
        listarLibrosSoloTitulo();
        out.print("Digite la opción que desea ->  ");
        int opcion = Main.leerOpcion();
        Libro libro = leerDatosDeLibro();
        libro.setId(devolverTituloLibro(opcion).getId());
        libro.setEstado(devolverTituloLibro(opcion).getEstado());
        String resultado = libroGestor.modificarLibro(libro);
        out.println(resultado);
        Main.esperarTecla();
    }

    /**
     * Método que elimina un libro
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void eliminarLibro() throws IOException {
        listarLibrosSoloTitulo();
        out.print("Digite la opción que desea ->  ");
        int opcion = Main.leerOpcion();
        Libro libro = devolverTituloLibro(opcion);
        String resultado = libroGestor.eliminarLibro(libro.getId());
        out.println(resultado);
        Main.esperarTecla();
    }


    static void listarLibrosSoloTitulo() throws IOException {
        ArrayList<Libro> libros = libroGestor.listarLibros();

        if(!libros.isEmpty()) {
            for (Libro libro : libros) {
                out.printf("[%d] %s. \n", libros.indexOf(libro) + 1 , libro.getTitulo());
            }
        } else {
            out.println("No existen libros registrados.");
            Main.esperarTecla();
        }
    }

    static void listarLibros() throws IOException {
        ArrayList<Libro> libros = libroGestor.listarLibros();

        if(!libros.isEmpty()) {
            for (Libro libro : libros) {
                out.printf("[%d] Título: %s. \n", libros.indexOf(libro) + 1 , libro.getTitulo());
                out.printf("     Autor: %s. \n", libro.getAutor().getNombre());
            }
            Main.esperarTecla();
        } else {
            out.println("No existen libros registrados.");
            Main.esperarTecla();
        }
    }

    static Libro devolverTituloLibro (int opcion) {
        ArrayList<Libro> libros = libroGestor.listarLibros();
        Libro libro = libros.get(opcion - 1);
        return libro;
    }
}

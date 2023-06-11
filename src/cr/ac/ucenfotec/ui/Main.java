package cr.ac.ucenfotec.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Clase Main que contiene el método main
 * @version 1.0
 * @since 10/06/2022
 * @author Carolina Arias
 */
public class Main {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        menuPrincipalCompleto();
    }

    /**
     * Método que imprime el encabezado del programa
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void header() throws IOException {
        out.println("==========================================================================");
        out.println("||                   Sistema de Gestión de Bibliotecas                  ||");
        out.println("==========================================================================");
    }

    /**
     * Método que lee la opción digitada por el usuario
     * @return un entero con la opción digitada por el usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static int leerOpcion() throws IOException {
        Boolean esNumero = false;
        int opcion = 0;
        do {
        try {
            opcion = Integer.parseInt(in.readLine());
            esNumero = true;
        } catch (NumberFormatException e) {
            out.println("Debe digitar un número.");
            out.print("Digite la opción que desea -> ");
            esNumero = false;
        }
        } while (!esNumero);
        return opcion;
    }

    /**
     * Método que imprime el menú de usuarios
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void impMenuPrincipal() throws IOException{
        out.println("[1] Usuarios");
        out.println("[2] Libros");
        out.println("[3] Préstamos");
        out.println("[4] Salir");
        out.print("Digite la opción que desea -> ");
    }

    /**
     * Método que realiza la opción elegida por el usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void menuPrincipal(int opcion) throws IOException{
        switch (opcion) {
            case 1:
                UsuarioUI.menuUsuariosCompleto();
                break;
            case 2:
                LibroUI.menuLibrosCompleto();
                break;
            case 3:
                PrestamoUI.menuPrestamosCompleto();
                break;
            case 4:
                out.println("Saliendo del programa...");
                System.exit(0);
                break;
            default:
                out.println("Opción inválida");
                break;
        }
    }

    /**
     * Método que realiza la funcionalidad completa del menú principal
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void menuPrincipalCompleto() throws IOException {
        int opcMenu = 0;
        do {
            header();
            impMenuPrincipal();
            opcMenu = leerOpcion();
            menuPrincipal(opcMenu);
        } while (opcMenu != 4);
    }

    /**
     * Método que espera a que el usuario presione la tecla enter para continuar
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void esperarTecla() throws IOException {
        out.print("Presione la tecla enter para continuar...");
        in.readLine();
    }
}

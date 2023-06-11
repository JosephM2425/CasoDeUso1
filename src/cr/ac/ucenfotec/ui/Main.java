package cr.ac.ucenfotec.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class Main {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        menuPrincipalCompleto();
    }

    static void header() throws IOException {
        out.println("==========================================================================");
        out.println("||                   Sistema de Gestión de Bibliotecas                  ||");
        out.println("==========================================================================");
    }

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

    static void impMenuPrincipal() throws IOException{
        out.println("[1] Usuarios");
        out.println("[2] Libros");
        out.println("[3] Préstamos");
        out.println("[4] Salir");
        out.print("Digite la opción que desea -> ");
    }

    static void menuPrincipal(int opcion) throws IOException{
        switch (opcion) {
            case 1:
                UsuarioUI.menuUsuariosCompleto();
                break;
            case 2:
                impMenuLibros();
                break;
            case 3:

                break;
            case 4:
                out.println("Saliendo del programa...");
                break;
            default:
                out.println("Opción inválida");
                break;
        }
    }

    static void menuPrincipalCompleto() throws IOException {
        int opcMenu = 0;
        do {
            header();
            impMenuPrincipal();
            opcMenu = leerOpcion();
            menuPrincipal(opcMenu);
        } while (opcMenu != 4);
    }

    static void esperarTecla() throws IOException {
        out.print("Presione la tecla enter para continuar...");
        in.readLine();
    }


    static void impMenuLibros() throws IOException{
        out.println("[1] Registrar libro");
        out.println("[2] Modificar libro");
        out.println("[3] Eliminar libro");
        out.println("[4] Listar libros");
        out.println("[5] Buscar libro");
        out.println("[6] Regresar");
        out.print("Digite la opción que desea -> ");
    }

}

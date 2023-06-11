package cr.ac.ucenfotec.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.logic.UsuarioGestor;



public class UsuarioUI {
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static PrintStream out = System.out;
    public static UsuarioGestor usuarioGestor = new UsuarioGestor();

    /**
    * Método que imprime el menú de opciones de usuarios
    */
    static void impMenuUsuarios() throws IOException {
        out.println("[1] Registrar usuario");
        out.println("[2] Modificar usuario");
        out.println("[3] Eliminar usuario");
        out.println("[4] Listar usuarios");
        out.println("[5] Regresar");
        out.print("Digite la opción que desea -> ");
    }

    /**
    * Método que realiza la opción elegida por el usuario
    * @param opcion es de tipo int y es la opción elegida por el usuario
    */
    static void menuUsuarios(int opcion) throws IOException{
        switch (opcion) {
            case 1:
                Main.header();
                registrarUsuario();
                break;
            case 2:
                Main.header();
                modificarUsuario();
                break;
            case 3:
                Main.header();
                eliminarUsuario();
                break;
            case 4:
                Main.header();
                listarUsuarios();
                break;
            case 5:
                Main.menuPrincipalCompleto();
                break;
            default:
                out.println("Opción inválida");
                break;
        }
    }

    static void menuUsuariosCompleto() throws IOException {
        int opcion = 0;
        do {
            Main.header();
            impMenuUsuarios();
            opcion = Main.leerOpcion();
            menuUsuarios(opcion);
        } while (opcion != 5);
    }


    static String leerNombreUsuario() throws IOException {
        out.print("Digite el nombre de usuario: ");
        String nombreUsuario = in.readLine();
        return nombreUsuario;
    }

    static String leerRolUsuario() throws IOException {
        out.println("Elija el rol deseado: ");
        out.println("[1] Administrador");
        out.println("[2] Cliente");
        out.print("Digite la opción que desea -> ");
        int tipoUsuario = Main.leerOpcion();
        String tipoUsuarioString = "";

        switch (tipoUsuario) {
            case 1:
                tipoUsuarioString = "Administrador";
                break;
            case 2:
                tipoUsuarioString = "Cliente";
                break;
            default:
                out.println("Opción inválida");
                break;
        }
        return tipoUsuarioString;
    }

    /**
     * Método que lee los datos de un usuario
     * @return un objeto de tipo Usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static Usuario leerDatosDeUsuario() throws IOException {
        String nombreUsuario = leerNombreUsuario();

        out.print("Digite el nombre completo del usuario: ");
        String nombreCompleto = in.readLine();

        out.print("Digite la dirección del usuario: ");
        String direccion = in.readLine();

        out.print("Digite el número de teléfono del usuario: ");
        String telefono = in.readLine();

        out.print("Digite la contraseña del usuario: ");
        String contrasenna = in.readLine();

        String tipoUsuarioString = leerRolUsuario();

        Usuario usuario = new Usuario(nombreCompleto, direccion, telefono, nombreUsuario, contrasenna, tipoUsuarioString);
        return usuario;
    }

    /**
     * Método que registra un usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void registrarUsuario() throws IOException {
        String mensajeYaExiste = "Usuario ya existente. Por favor elija otro nombre de usuario.";
        String resultado = "";
        do {
            Usuario usuario = leerDatosDeUsuario();
            resultado = usuarioGestor.registrarUsuario(usuario);
            out.println(resultado);
            Main.esperarTecla();
        } while (resultado.equals(mensajeYaExiste));
    }

    /**
     * Método que modifica un usuario
     * @throws IOException es una excepción que se lanza cuando hay un error de entrada/salida
     */
    static void modificarUsuario() throws IOException {
        String mensajeNoExiste = "Usuario no existe. Por favor verifique el nombre de usuario.";
        String resultado = "";
        do {
            Usuario usuario = leerDatosDeUsuario();
            resultado = usuarioGestor.modificarUsuario(usuario);
            out.println(resultado);
            Main.esperarTecla();
        } while (resultado.equals(mensajeNoExiste));
    }

    static void eliminarUsuario () throws IOException {
        String mensajeNoExiste = "Usuario no existe. Por favor verifique el nombre de usuario.";
        String resultado = "";
        do {
            String nombreUsuario = leerNombreUsuario();
            resultado = usuarioGestor.eliminarUsuario(nombreUsuario);
            out.println(resultado);
            Main.esperarTecla();
        } while (resultado.equals(mensajeNoExiste));
    }

    static void listarUsuarios() throws IOException {
        String rolUsuario = leerRolUsuario();
        ArrayList<Usuario> usuarios = usuarioGestor.listarUsuarios(rolUsuario);

        if(!usuarios.isEmpty()) {
            out.printf("============ Usuarios de tipo %s ============\n", rolUsuario);
            for (Usuario usuario : usuarios) {
                out.printf("[%d] Nombre: %s | Nombre de usuario: %s\n", usuarios.indexOf(usuario), usuario.getNombre_completo(), usuario.getNombre_usuario());
            }
            Main.esperarTecla();
        } else {
            out.println("No existen usuarios de este tipo.");
            Main.esperarTecla();
        }
    }
}

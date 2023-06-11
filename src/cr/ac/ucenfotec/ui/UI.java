package cr.ac.ucenfotec.ui;

import java.util.Scanner;

public class UI {
    public UI() {
    }

    public void mostrarMenuPrincipal() {
        System.out.println("----MENU PRINCIPAL----");
        System.out.println("Que desea administrar? Dijite una opcion:");
        System.out.println("1. Prestamos");
        System.out.println("2. Usuarios");
        System.out.println("3. Libros");
        System.out.println("4. Autores");
        System.out.println("5. Categorias");
        System.out.print("\nR/ ");
    }

    public void mostrarMenuPrestamos() {
        System.out.println("----PRESTAMOS----");
        System.out.println("Que desea administrar en PRESTAMOS? Dijite una opcion:");
        System.out.println("1. Crear prestamo");
        System.out.println("2. Ver lista de prestamos");
        System.out.println("3. Editar un prestamo");
        System.out.println("4. Buscar prestamo por ID");
        System.out.print("\nR/ ");
    }

    public void mostrarMenuUsuarios() {
        System.out.println("----USUARIOS----");
        System.out.println("Que desea administrar en USUARIOS? Dijite una opcion:");
        System.out.println("1. Crear usuario");
        System.out.println("2. Ver lista de usuarios");
        System.out.println("3. Modificar Usuario");
        System.out.println("4. Buscar por medio del nombre de usaurio");
        System.out.println("5. Buscar por el ID de usuario");
        System.out.println("6. Eliminar Usuario");
        System.out.print("\nR/ ");
    }

    public void mostrarMenuLibros() {
        System.out.println("----LIBROS----");
        System.out.println("Que desea administrar en LIBROS? Dijite una opcion:");
        System.out.println("1. Ingresar nuevo libro");
        System.out.println("2. Ver lista de libros");
        System.out.println("3. Modificar un libro");
        System.out.println("4. Buscar por medio del titulo");
        System.out.println("5. Buscar por el ID de libro");
        System.out.println("6. Eliminar Usuario");
        System.out.print("\nR/ ");
    }

    public void mostrarMenuAutores() {
        System.out.println("----AUTORES----");
        System.out.println("Que desea administrar en AUTORES? Dijite una opcion:");
        System.out.println("1. Ingresar nuevo autor");
        System.out.println("2. Ver lista de autores");
        System.out.println("3. Buscar autor por nombre");
        System.out.println("4. Buscar autor por ID");
        System.out.print("\nR/ ");

    }

    public void mostrarMenuCategorias() {
        System.out.println("----CATEGORIAS----");
        System.out.println("Que desea administrar en CATEGORIAS? Dijite una opcion:");
        System.out.println("1. Ingresar nueva categoria");
        System.out.println("2. Ver lista de categorias");
        System.out.println("3. Buscar categoria por nombre");
        System.out.println("4. Buscar categoria por ID");
        System.out.print("\nR/ ");
    }

    public int leerNumero() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        opcion = scanner.nextInt();
        return opcion;
    }

    public String leerString() {
        Scanner scanner = new Scanner(System.in);
        String opcion;
        opcion = scanner.nextLine();
        return opcion;
    }

    public void imprimirMsj (String mensaje) {
        System.out.print(mensaje);
    }

}

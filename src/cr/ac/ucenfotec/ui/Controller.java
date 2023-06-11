package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.logic.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    UI ui;
    PrestamoGestor prestamoGestor;
    UsuarioGestor usuarioGestor;
    LibroGestor libroGestor;
    AutorGestor autorGestor;
    CategoriaGestor categoriaGestor;
    public Controller() {
        this.ui = new UI();
        this.prestamoGestor = new PrestamoGestor();
        this.usuarioGestor = new UsuarioGestor();
        this.libroGestor = new LibroGestor();
        this.autorGestor = new AutorGestor();
        this.categoriaGestor = new CategoriaGestor();
    }

    public void start() {
        int bandera = -1;
        do {
            ui.mostrarMenuPrincipal();
            procesarMenuPrincipal(ui.leerNumero());
        } while (bandera < 0);
    }

    public void procesarMenuPrincipal(int opcion) {
        switch(opcion){
            case 1:
                ui.mostrarMenuPrestamos();
                procesarMenuPrestamos(ui.leerNumero());
                break;
            case 2:
                ui.mostrarMenuUsuarios();
                procesarMenuUsuarios(ui.leerNumero());
                break;
            case 3:
                ui.mostrarMenuLibros();
                procesarMenuLibros(ui.leerNumero());
                break;
            case 4:
                ui.mostrarMenuAutores();
                procesarMenuAutores(ui.leerNumero());
                break;
            case 5:
                ui.mostrarMenuCategorias();
                procesarMenuCategorias(ui.leerNumero());
                break;
            default:
                System.out.println("Opción inválida, por favor intente de nuevo");
                break;
        }
    }

    public void procesarMenuPrestamos(int opcion) {
        switch(opcion){
            case 1:
                System.out.println("Ingrese el ID del Libro");
                int idLibro = ui.leerNumero();
                System.out.println("Ingrese el ID del Usuario");
                int idUsuario = ui.leerNumero();
                System.out.println("Ingrese la fecha de vencimiento:");
                System.out.println("Dia:");
                int diaVencimiento = ui.leerNumero();
                System.out.println("Mes:");
                int mesVencimiento = ui.leerNumero();
                System.out.println("Anio:");
                int anioVencimiento = ui.leerNumero();
                LocalDate fechaVencimiento= LocalDate.of(anioVencimiento, mesVencimiento, diaVencimiento);
                prestamoGestor.registrarPrestamo(idLibro, idUsuario, fechaVencimiento);
                break;
            case 2:
                ArrayList<Prestamo> listaPrestamos = prestamoGestor.listarPrestamos();;
                for (Prestamo prestamo:listaPrestamos) {
                    System.out.println(prestamo.getId() + ", " + prestamo.getUsuario().getNombre_usuario() + ", " + prestamo.getFecha_prestamo() + ", " + prestamo.getFecha_vencimiento() + ", " + prestamo.getLibro());
                }
                break;
            case 3:
                System.out.println("Ingrese el ID del prestamo:");
                int idPrestamoEdit = ui.leerNumero();
                System.out.println("Ingrese la fecha de devolucion: ");
                System.out.println("Dia: ");
                int diaDevolucion = ui.leerNumero();
                System.out.println("Mes: ");
                int mesDevolucion = ui.leerNumero();
                System.out.println("Anio: ");
                int anioDevolucion = ui.leerNumero();
                LocalDate fechaDevolucion = LocalDate.of(anioDevolucion, mesDevolucion, diaDevolucion);
                prestamoGestor.modificarPrestamo(idPrestamoEdit, fechaDevolucion);
                break;
            case 4:
                System.out.println("Ingrese el ID del prestamo");
                int idPrestamoBuscar = ui.leerNumero();
                prestamoGestor.buscarPrestamo(idPrestamoBuscar);
                break;
            default:
                System.out.println("Opción inválida, por favor intente de nuevo");
                break;
        }
    }

    public void procesarMenuUsuarios(int opcion) {
        switch(opcion){
            case 1:
                System.out.println("Ingrese el nombre completo del usuario");
                String nombreCompletoReg = ui.leerString();
                System.out.println("Ingrese la direccion del usuario");
                String direccionReg = ui.leerString();
                System.out.println("Ingrese el numero telefonico del usuario");
                String telReg = ui.leerString();
                System.out.println("Ingrese el nombre del usuario");
                String nombreUsuarioReg = ui.leerString();
                System.out.println("Ingrese una contrasena");
                String contrasenaReg = ui.leerString();
                System.out.println("Ingrese el rol de usuario (Cliente/Administrador)");
                String rolReg = ui.leerString();
                System.out.println(usuarioGestor.registrarUsuario(nombreCompletoReg, direccionReg, telReg, nombreUsuarioReg, contrasenaReg, rolReg));
                break;
            case 2:
                ArrayList<Usuario> listaUsuarios = usuarioGestor.listarUsuarios();
                for (Usuario usuario : listaUsuarios) {
                    System.out.println(usuario.toString());
                }
                break;
            case 3:
                System.out.println("Ingrese el id de usuario que desea modificar:");
                int idUsuarioMod = ui.leerNumero();
                System.out.println("Ingrese el nombre completo del usuario");
                String nombreCompletoMod = ui.leerString();
                System.out.println("Ingrese la direccion del usuario");
                String direccionMod = ui.leerString();
                System.out.println("Ingrese el numero telefonico del usuario");
                String telMod = ui.leerString();
                System.out.println("Ingrese el nombre del usuario");
                String nombreUsuarioMod = ui.leerString();
                System.out.println("Ingrese una contrasena");
                String contrasenaMod = ui.leerString();
                System.out.println("Ingrese el rol de usuario (Cliente/Administrador)");
                String rolMod = ui.leerString();

                Usuario usuarioToMod = new Usuario(idUsuarioMod, nombreCompletoMod, direccionMod, telMod, nombreUsuarioMod, contrasenaMod, rolMod);
                usuarioGestor.modificarUsuario(usuarioToMod);
                break;
            case 4:
                System.out.println("Ingrese el nombre del usuario a buscar");
                String nombreBusqueda = ui.leerString();
                Usuario usuarioEncontrado = usuarioGestor.buscarUsuarioPorNombreUsuario(nombreBusqueda);
                System.out.println(usuarioEncontrado.toString());
                break;
            case 5:
                System.out.println("Ingrese el ID del usuario a buscar");
                int idBusqueda = ui.leerNumero();
                Usuario usuarioPorId = usuarioGestor.buscarUsuarioPorId(idBusqueda);
                System.out.println(usuarioPorId.toString());
                break;
            case 6:
                System.out.println("Ingrese el ID del usuario a eliminar");
                int idUsuarioEliminar = ui.leerNumero();
                usuarioGestor.eliminarUsuario(idUsuarioEliminar);
                break;
            default:
                System.out.println("Opción inválida, por favor intente de nuevo");
                break;
        }
    }

    public void procesarMenuLibros(int opcion) {
        switch(opcion){
            case 1:
                System.out.println("Ingrese el título del libro");
                String tituloLibro = ui.leerString();
                System.out.println("Ingrese el id del autor");
                int idAutor = ui.leerNumero();
                System.out.println("Ingrese el id de la categoria");
                int idCategoria = ui.leerNumero();
                System.out.println(libroGestor.agregarLibro(tituloLibro, idAutor, idCategoria));
                break;
            case 2:
                ArrayList<Libro> listaLibros = libroGestor.listarLibros();
                for (Libro libro : listaLibros) {
                    System.out.println(libro.toString());
                }
                break;
            case 3:
                System.out.println("Digite el id del libro a modificar: ");
                int idLibroMod = ui.leerNumero();
                Libro libro = libroGestor.buscarLibroPorID(idLibroMod);

                // Si el libro es null, interrumpe el proceso.
                if(libro == null) {
                    System.out.println("Libro no encontrado");
                    break;
                }

                // Usamos estos valores por defecto para saber si el usuario ha hecho algún cambio en cada atributo.
                String tituloLibroMod = libro.getTitulo();
                Boolean estadoLibroMod = libro.getEstado();  // Change the boolean to Boolean
                int idAutorLibroMod = libro.getAutor().getId();
                int idCategoriaLibroMod = libro.getCategoria().getId();
                boolean haCambiado = false;
                boolean bandera = true;

                while (bandera) {
                    System.out.println("\n\nSeleccione que desea cambiar: " +
                            "\n1. Titulo" +
                            "\n2. Estado " +
                            "\n3. Autor" +
                            "\n4. Categoria" +
                            "\n5. Salir");

                    switch (ui.leerNumero()) {
                        case 1:
                            System.out.print("Ingrese el nuevo titulo:");
                            tituloLibroMod = ui.leerString();
                            haCambiado = true;
                            break;
                        case 2:
                            System.out.print("Ingrese el nuevo estado (true/false):");
                            estadoLibroMod = Boolean.parseBoolean(ui.leerString());
                            haCambiado = true;
                            break;
                        case 3:
                            System.out.println("Ingrese el ID del autor:");
                            idAutorLibroMod = ui.leerNumero();
                            haCambiado = true;
                            break;
                        case 4:
                            System.out.println("Ingrese el ID de la categoria:");
                            idCategoriaLibroMod = ui.leerNumero();
                            haCambiado = true;
                            break;
                        case 5:
                            if (haCambiado) {
                                System.out.println(libroGestor.modificarLibro(idLibroMod, tituloLibroMod, estadoLibroMod, idAutorLibroMod, idCategoriaLibroMod));
                            }
                            return;
                        default:
                            bandera = false;
                            System.out.println("Opcion Invalida");
                    }
                }
                break;
            case 4:
                System.out.println("Ingrese el título del libro a buscar");
                String tituloBusqueda = ui.leerString();
                Libro libroEncontrado = libroGestor.buscarLibroPorTitulo(tituloBusqueda);
                System.out.println(libroEncontrado.toString());
                break;
            case 5:
                System.out.println("Ingrese el ID del libro a buscar");
                int idLibroBusqueda = ui.leerNumero();
                Libro libroPorId = libroGestor.buscarLibroPorID(idLibroBusqueda);
                System.out.println(libroPorId.toString());
                break;
            case 6:
                System.out.println("Ingrese el ID del libro a eliminar");
                int idLibroEliminar = ui.leerNumero();
                libroGestor.eliminarLibro(idLibroEliminar);
                break;
            default:
                System.out.println("Opción inválida, por favor intente de nuevo");
                break;
        }
    }

    public void procesarMenuAutores(int opcion) {
        switch(opcion){
            case 1:
                System.out.println("Ingrese el nombre del autor");
                String nombreAutor = ui.leerString();
                System.out.println(autorGestor.agregarAutor(nombreAutor));
                break;
            case 2:
                ArrayList<Autor> listaAutores = autorGestor.listarAutores();
                for (Autor autor : listaAutores) {
                    System.out.println(autor.toString());
                }
                break;
            case 3:
                System.out.println("Ingrese el nombre del autor a buscar");
                String nombreBusqueda = ui.leerString();
                Autor autorEncontrado = autorGestor.buscarAutorPorNombre(nombreBusqueda);
                System.out.println(autorEncontrado.toString());
                break;
            case 4:
                System.out.println("Ingrese el ID del autor a buscar");
                int idBusqueda = ui.leerNumero();
                Autor autorPorId = autorGestor.buscarAutorPorID(idBusqueda);
                System.out.println(autorPorId.toString());
                break;
            default:
                System.out.println("Opción inválida, por favor intente de nuevo");
                break;
        }
    }

    public void procesarMenuCategorias(int opcion) {
        switch(opcion){
            case 1:
                System.out.println("Ingrese el nombre de la categoría");
                String nombreCategoria = ui.leerString();
                System.out.println(categoriaGestor.agregarCategoria(nombreCategoria));
                break;
            case 2:
                ArrayList<Categoria> listaCategorias = categoriaGestor.listarCategorias();
                for (Categoria categoria : listaCategorias) {
                    System.out.println(categoria.toString());
                }
                break;
            case 3:
                System.out.println("Ingrese el nombre de la categoría a buscar");
                String nombreBusqueda = ui.leerString();
                Categoria categoriaEncontrada = categoriaGestor.buscarCategoriaPorNombre(nombreBusqueda);
                System.out.println(categoriaEncontrada.toString());
                break;
            case 4:
                System.out.println("Ingrese el ID de la categoría a buscar");
                int idBusqueda = ui.leerNumero();
                Categoria categoriaPorId = categoriaGestor.buscarCategoriaPorId(idBusqueda);
                System.out.println(categoriaPorId.toString());
                break;
            default:
                System.out.println("Opción inválida, por favor intente de nuevo");
                break;
        }
    }

}

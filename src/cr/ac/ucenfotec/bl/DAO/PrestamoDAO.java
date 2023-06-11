package cr.ac.ucenfotec.bl.DAO;
import cr.ac.ucenfotec.bl.entities.Libro;
import cr.ac.ucenfotec.bl.entities.Prestamo;
import cr.ac.ucenfotec.bl.config.Configuracion;
import cr.ac.ucenfotec.bl.entities.Usuario;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Carolina Arias
 * @version 1.0
 * @since 09/06/2023
 *
 * Esta clase se encarga de gestionar el acceso a datos de los objetos Prestamo
 */
public class PrestamoDAO {

        /**
         * Metodo para registrar un prestamo
         * @param prestamo es de tipo Prestamo y corresponde al prestamo por registrar
         * @return 0 si se registro correctamente, 1 si no se pudo registrar
         */
        public int registrarPrestamo(Prestamo prestamo) {
            try {
                //Se crea una nueva instancia del archivo de configuración
                Configuracion configuracion = new Configuracion();
                //Lo lee del archivo de configuracion
                Class.forName(configuracion.getClaseJDBC());
                Connection conn = null;
                PreparedStatement stmt = null;

                String strConexion = configuracion.getStringConexion();
                String query = "EXECUTE sp_registrar_prestamo ?, ?, ?, ?";
                conn = DriverManager.getConnection(strConexion);
                stmt = conn.prepareStatement(query);;
                stmt.setDate(1, Date.valueOf(prestamo.getFecha_prestamo()));
                stmt.setDate(2, Date.valueOf(prestamo.getFecha_vencimiento()));
                stmt.setInt(3, prestamo.getLibro().getId());
                stmt.setInt(4, prestamo.getUsuario().getId());
                stmt.execute();

                return 0;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return 1;
            }
        }

    /**
     * Metodo para listar los prestamos
     * @return un ArrayList con los prestamos
     */
    public ArrayList<Prestamo> listarPrestamos()
    {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        try {
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            String query = "SELECT * FROM vw_prestamos";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(rs.getInt("id_prestamo"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo").toLocalDate());
                prestamo.setFecha_vencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
                if (rs.getDate("fecha_devolucion") != null) {
                    prestamo.setFecha_devolucion(rs.getDate("fecha_devolucion").toLocalDate());
                }
                LibroDAO libroDAO = new LibroDAO();
                prestamo.setLibro(libroDAO.buscarLibro(rs.getInt("ID_LIBRO")));
                UsuarioDAO prestamoDAO = new UsuarioDAO();
                prestamo.setUsuario(prestamoDAO.buscarUsuario(rs.getInt("ID_USUARIO")));
                prestamos.add(prestamo);
            }
            conn.close();
        } catch (Exception e){
            return null;
        }
        return prestamos;
    }

    /**
     * Metodo para modificar un prestamo
     * @param prestamo es de tipo Prestamo y corresponde al prestamo por modificar
     * @return 0 si se modifico correctamente, 1 si no se pudo modificar
     */
    public int modificarPrestamo(Prestamo prestamo) {
        try {
            //Se crea una nueva instancia del archivo de configuración
            Configuracion configuracion= new Configuracion();
            //Lo lee del archivo de configuracion
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            PreparedStatement stmt = null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_modificar_prestamo ?, ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);;
            stmt.setInt(1,prestamo.getId());
            stmt.setDate(2, Date.valueOf(prestamo.getFecha_devolucion()));
            stmt.execute();
            return 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Metodo para buscar un prestamo por su id
     * @param idPrestamo es de tipo int y corresponde al id del prestamo por buscar
     * @return prestamo es de tipo Prestamo y corresponde al prestamo por buscar
     */
    public Prestamo buscarPrestamo(int idPrestamo)
    {
        Configuracion configuracion = new Configuracion();
        Prestamo prestamo = new Prestamo();
        try {
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_prestamo_por_id ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idPrestamo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                prestamo.setId(rs.getInt("id_prestamo"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo").toLocalDate());
                prestamo.setFecha_vencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
                if (rs.getDate("fecha_devolucion") != null) {
                    prestamo.setFecha_devolucion(rs.getDate("fecha_devolucion").toLocalDate());
                }
                LibroDAO libroDAO = new LibroDAO();
                prestamo.setLibro(libroDAO.buscarLibro(rs.getInt("ID_LIBRO")));
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                prestamo.setUsuario(usuarioDAO.buscarUsuario(rs.getInt("ID_USUARIO")));
            }
            conn.close();
            return prestamo;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * Metodo para eliminar un prestamo
     * @param prestamo es de tipo Prestamo y corresponde al prestamo por eliminar
     */
    public void eliminarPrestamo(Prestamo prestamo) {
        try {
            //Se crea una nueva instancia del archivo de configuración
            Configuracion configuracion= new Configuracion();
            //Lo lee del archivo de configuracion
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            PreparedStatement stmt = null;
            String strConexion = configuracion.getStringConexion();
            conn = DriverManager.getConnection(strConexion);

            String query = "EXECUTE sp_eliminar_prestamo ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,prestamo.getId());
            stmt.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Prestamo> listarLibrosPrestados()
    {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        try {
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            String query = "SELECT titulo, nombre_completo, fecha_vencimiento FROM Prestamo INNER JOIN Usuario ON Prestamo.id_usuario=Usuario.id_usuario INNER JOIN Libro ON Prestamo.id_libro=Libro.id_libro WHERE Libro.estado=1";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                Libro libro = new Libro();
                Usuario usuario = new Usuario();
                libro.setTitulo(rs.getString("titulo"));
                usuario.setNombre_completo(rs.getString("nombre_completo"));
                prestamo.setLibro(libro);
                prestamo.setUsuario(usuario);
                prestamo.setFecha_vencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
                prestamos.add(prestamo);
            }
            conn.close();
        } catch (Exception e){
            return null;
        }
        return prestamos;
    }

    public ArrayList<Prestamo> listarLibrosPrestados(String nombreUsuario)
    {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        try {
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "SELECT titulo, nombre_completo FROM Prestamo INNER JOIN Usuario ON Prestamo.id_usuario=Usuario.id_usuario INNER JOIN Libro ON Prestamo.id_libro=Libro.id_libro WHERE nombre_usuario= ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                Libro libro = new Libro();
                Usuario usuario = new Usuario();
                libro.setTitulo(rs.getString("titulo"));
                usuario.setNombre_completo(rs.getString("nombre_completo"));
                prestamo.setLibro(libro);
                prestamo.setUsuario(usuario);
                prestamos.add(prestamo);
            }
            conn.close();
        } catch (Exception e){
            return null;
        }
        return prestamos;
    }
}

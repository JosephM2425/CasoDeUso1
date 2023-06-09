package cr.ac.ucenfotec.bl.entities;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO Autor
 * @author Ruben Cantillo
 * @version 1.0
 * @since 09/06/2023
 */
public class LibroDAO {

    /**
     * Método para agregar un libro a la base de datos
     * @param tmpLibro el libro a insertar
     */
    public void agregarLibro(Libro tmpLibro){
        try{
            Configuracion configuracion = new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs=null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_registrar_libro ?,?,?,?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmpLibro.getNombre());
            stmt.setBoolean(2,tmpLibro.getEstado());
            stmt.setInt(3,tmpLibro.getAutor().getId());
            stmt.setInt(4,tmpLibro.getCategoria().getId());
            try {
                stmt.execute();
            }
            catch (SQLServerException e){
                e.printStackTrace();
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo para listar los libros
     * @return una lista de libros
     */
    public ArrayList<Libro> listarLibros(){
        ArrayList<Libro> libros = new ArrayList<>();
        try {
            Configuracion configuracion=new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn=null;
            String query = "SELECT * FROM vw_libros";
            Statement stmt;
            ResultSet rs;
            String strConexion = configuracion.getStringConexion();
            conn=DriverManager.getConnection(strConexion);
            stmt=conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Libro libro = new Libro();
                libro.setId(Integer.parseInt(rs.getString("id_libro")));
                libro.setNombre(rs.getString("nombre"));
                libro.setEstado(rs.getBoolean("estado"));
                AutorDAO autorDAO = new AutorDAO();
                libro.setAutor(autorDAO.buscarAutor(rs.getString("id_autor")));
                CategoríaDAO categoríaDAO = new CategoríaDAO();
                libro.setCategoria(categoríaDAO.buscarCategoria(rs.getString("id_categoria")));
                libros.add(libro);
            }
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return libros;
    }

    /**
     * Método para buscar un libro con su ID
     * @param tmpLibro el ID del libro a buscar
     * @return el libro
     */
    public Libro buscarLibro(String tmpLibro)  {
        Configuracion configuracion = new Configuracion();
        Libro libro = new Libro();
        try{
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_categoria_por_id ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, tmpLibro);
            rs = stmt.executeQuery();
            if (rs.next()) {
                libro.setNombre(rs.getString("nombre_categoria"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return libro;
    }
    /**
     * Metodo para eliminar un libro en la base de datos
     * @param tmpLibro el libro a eliminar
     */
    public void eliminarLibro (Libro tmpLibro){
        try {
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_eliminar_libro ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,tmpLibro.getId());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo para actualizar un libro en la base de datos
     * @param tmpLibro el libro a actualizar
     */
    public void modificarLibro(Libro tmpLibro){
        Configuracion configuracion=new Configuracion();
        try {
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_modificar_libro ?,?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,tmpLibro.getId());
            stmt.setBoolean(2,tmpLibro.getEstado());
            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
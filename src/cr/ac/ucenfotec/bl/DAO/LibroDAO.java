package cr.ac.ucenfotec.bl.DAO;
import cr.ac.ucenfotec.bl.entities.Autor;
import cr.ac.ucenfotec.bl.entities.Categoria;
import cr.ac.ucenfotec.bl.entities.Libro;
import cr.ac.ucenfotec.bl.config.Configuracion;
import cr.ac.ucenfotec.bl.entities.Prestamo;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO Libro
 * @author Ruben Cantillo
 * @version 1.0
 * @since 09/06/2023
 */
public class LibroDAO {

    /**
     * Método para agregar un libro a la base de datos
     * @param tmpLibro el libro a insertar
     */
    public int agregarLibro(Libro tmpLibro){
        try{
            Configuracion configuracion = new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs=null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_registrar_libro ?,?,?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmpLibro.getTitulo());
            stmt.setInt(2,tmpLibro.getAutor().getId());
            stmt.setInt(3,tmpLibro.getCategoria().getId());
            stmt.execute();
            return 0;
        }
        catch (SQLException | ClassNotFoundException e) {
            return 1;
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
                libro.setTitulo(rs.getString("titulo"));
                libro.setEstado(rs.getBoolean("estado"));
                Autor autor = new Autor();
                autor.setId(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre_autor"));
                libro.setAutor(autor);
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                libro.setCategoria(categoria);
                libros.add(libro);
            }
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            return null;
        }
        return libros;
    }

    public ArrayList<Libro> listarLibros(Boolean estado){
        ArrayList<Libro> libros = new ArrayList<>();
        try {
            Configuracion configuracion = new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "SELECT * FROM Libro WHERE estado = ?";
            stmt = conn.prepareStatement(query);
            stmt.setBoolean(1, estado);
            rs = stmt.executeQuery();
            while (rs.next()){
                Libro libro = new Libro();
                libro.setId(Integer.parseInt(rs.getString("id_libro")));
                libro.setTitulo(rs.getString("titulo"));
                libro.setEstado(rs.getBoolean("estado"));
                AutorDAO autorDAO = new AutorDAO();
                libro.setAutor(autorDAO.buscarAutor(rs.getInt("id_autor")));
                CategoriaDAO categoriaDAO = new CategoriaDAO();
                libro.setCategoria(categoriaDAO.buscarCategoria(rs.getInt("id_categoria")));
                libros.add(libro);
            }
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            return null;
        }
        return libros;
    }


    /**
     * Método para buscar un libro con su ID
     * @param idLibro el ID del libro a buscar
     * @return el libro
     */
    public Libro buscarLibro(int idLibro)  {
        Configuracion configuracion = new Configuracion();
        Libro libro = new Libro();
        try{
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_libro_por_id ? ";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idLibro);
            rs = stmt.executeQuery();
            if (rs.next()) {
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setEstado(rs.getBoolean("estado"));
                Autor autor = new Autor();
                autor.setId(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre_autor"));
                libro.setAutor(autor);
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                libro.setCategoria(categoria);
            }
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }
        return libro;
    }

    /**
     * Método para buscar un libro con su titulo
     * @param tituloLibro el titulo del libro a buscar
     * @return el libro
     */
    public Libro buscarLibro(String tituloLibro)  {
        Configuracion configuracion = new Configuracion();
        Libro libro = new Libro();
        try{
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_libro_por_titulo ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, tituloLibro);
            rs = stmt.executeQuery();
            if (rs.next()) {
                libro.setId(rs.getInt("id_libro"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setEstado(rs.getBoolean("estado"));
                Autor autor = new Autor();
                autor.setId(rs.getInt("id_autor"));
                autor.setNombre(rs.getString("nombre_autor"));
                libro.setAutor(autor);
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                libro.setCategoria(categoria);
            }
        } catch (SQLException | ClassNotFoundException e) {
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
    public int modificarLibro(Libro tmpLibro){
        Configuracion configuracion=new Configuracion();
        try {
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_modificar_libro ?,?,?,?,?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,tmpLibro.getId());
            stmt.setString(2,tmpLibro.getTitulo());
            stmt.setBoolean(3,tmpLibro.getEstado());
            stmt.setInt(4,tmpLibro.getAutor().getId());
            stmt.setInt(5,tmpLibro.getCategoria().getId());
            stmt.execute();
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            return 1;
        }
    }

    /**
     * Metodo para actualizar el estado de un libro en la base de datos
     * @param tmpLibro el libro a actualizar
     */
    public int modificarLibro(Libro tmpLibro, Boolean estado){
        Configuracion configuracion=new Configuracion();
        try {
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_modificar_estado_libro ?,?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,tmpLibro.getId());
            stmt.setBoolean(2,estado);
            stmt.execute();
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            return 1;
        }
    }
}

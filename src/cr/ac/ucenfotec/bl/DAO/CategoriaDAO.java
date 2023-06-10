package cr.ac.ucenfotec.bl.DAO;
import cr.ac.ucenfotec.bl.config.Configuracion;
import cr.ac.ucenfotec.bl.entities.Categoria;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO Categoría
 * @author Ruben Cantillo
 * @version 1.0
 * @since 09/06/2023
 */
public class CategoriaDAO {

    /**
     * Método para agregar una categoria a la base de datos
     * @param tmpCategoria la categoria a insertar
     */
    public int agregarCategoria(Categoria tmpCategoria){
        try{
            Configuracion configuracion = new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs=null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_registrar_categoria ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmpCategoria.getNombre());
            stmt.execute();
            return 0;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Metodo para listar las categorías
     * @return una lista de categorías
     */
    public ArrayList<Categoria> listarCategorias(){
        ArrayList<Categoria> categorias = new ArrayList<>();
        try {
            Configuracion configuracion=new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn=null;
            String query = "SELECT * FROM vw_categorias";
            Statement stmt;
            ResultSet rs;
            String strConexion = configuracion.getStringConexion();
            conn=DriverManager.getConnection(strConexion);
            stmt=conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));
                categorias.add(categoria);
            }
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    /**
     * Método para buscar una categoria con su nombre
     * @param nombreCategoria el nombre de la categoria a buscar
     * @return la categoria
     */
    public Categoria buscarCategoria(String nombreCategoria)  {
        Configuracion configuracion = new Configuracion();
        Categoria categoria = new Categoria();
        try{
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_categoria_por_nombre ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreCategoria);
            rs = stmt.executeQuery();
            if (rs.next()) {
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));
            }
            return categoria;
        } catch (SQLException | ClassNotFoundException e) {
            return null;
        }

    }

    /**
     * Método para buscar una categoria con su id
     * @param idCategoria el id de la categoria a buscar
     * @return la categoria
     */
    public Categoria buscarCategoria(int idCategoria)  {
        Configuracion configuracion = new Configuracion();
        Categoria categoria = new Categoria();
        try{
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_categoria_por_id ? ";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idCategoria);
            rs = stmt.executeQuery();
            if (rs.next()) {
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre_categoria"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categoria;
    }
}

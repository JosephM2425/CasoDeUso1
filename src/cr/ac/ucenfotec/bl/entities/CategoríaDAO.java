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
public class CategoríaDAO {

    /**
     * Método para agregar una categoria a la base de datos
     * @param tmpCategoria la categoria a insertar
     */
    public void agregarCategoria(Categoria tmpCategoria){
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
                categoria.setId(Integer.parseInt(rs.getString("id")));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    /**
     * Método para buscar una categoria con su ID
     * @param tmpCategoria el ID de la categoria a buscar
     * @return la categoria
     */
    public Categoria buscarCategoria(String tmpCategoria)  {
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
            stmt.setString(1, tmpCategoria);
            rs = stmt.executeQuery();
            if (rs.next()) {
                categoria.setNombre(rs.getString("nombre_categoria"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categoria;
    }
}

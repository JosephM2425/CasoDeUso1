package cr.ac.ucenfotec.bl.DAO;
import cr.ac.ucenfotec.bl.entities.Autor;
import cr.ac.ucenfotec.bl.config.Configuracion;

import java.sql.*;
import java.util.ArrayList;

/**
 * DAO Autor
 * @author Ruben Cantillo
 * @version 1.0
 * @since 09/06/2023
 */
public class AutorDAO {

    /**
     * Método para agregar un autor a la base de datos
     * @param tmpAutor el autor a insertar
     */
    public int agregarAutor(Autor tmpAutor){
        try{
            Configuracion configuracion = new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn;
            PreparedStatement stmt;
            ResultSet rs=null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_registrar_autor ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmpAutor.getNombre());
            stmt.execute();
            return 0;
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Metodo para listar los autores
     * @return una lista de autores
     */
    public ArrayList<Autor> listarAutores(){
        ArrayList<Autor> autores = new ArrayList<>();
        try {
            Configuracion configuracion=new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn=null;
            String query = "SELECT * FROM vw_autores";
            Statement stmt;
            ResultSet rs;
            String strConexion = configuracion.getStringConexion();
            conn=DriverManager.getConnection(strConexion);
            stmt=conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                Autor autor = new Autor();
                autor.setId(Integer.parseInt(rs.getString("id")));
                autor.setNombre(rs.getString("nombre"));
                autores.add(autor);
            }
            conn.close();
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return autores;
    }
    /**
     * Método para buscar un autor con su ID
     * @param tmpAutor el ID del autor a buscar
     * @return el autor
     */
    public Autor buscarAutor(String tmpAutor)  {
        Configuracion configuracion = new Configuracion();
        Autor autor = new Autor();
        try{
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_autor_por_id ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, tmpAutor);
            rs = stmt.executeQuery();
            if (rs.next()) {
                autor.setNombre(rs.getString("nombre_autor"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return autor;
    }
}

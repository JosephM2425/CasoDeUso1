package cr.ac.ucenfotec.bl.DAO;
import cr.ac.ucenfotec.bl.entities.Usuario;
import cr.ac.ucenfotec.bl.config.Configuracion;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Carolina Arias
 * @version 1.0
 * @since 08/06/2023
 *
 * Esta clase se encarga de gestionar el acceso a datos de los objetos Usuario
 */
public class UsuarioDAO {
    /**
     * Metodo para registrar un usuario
     * @param usuario es de tipo Usuario y corresponde al usuario por registrar
     * @return 0 si se registro correctamente, 1 si no se pudo registrar
     */
    public int registrarUsuario(Usuario usuario) {
        try {
            //Se crea una nueva instancia del archivo de configuración
            Configuracion configuracion= new Configuracion();
            //Lo lee del archivo de configuracion
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            PreparedStatement stmt = null;

            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_registrar_usuario ?, ?, ?, ?, ?, ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);;
            stmt.setString(1,usuario.getNombre_completo());
            stmt.setString(2,usuario.getDireccion());
            stmt.setString(3,usuario.getTelefono());
            stmt.setString(4,usuario.getNombre_usuario());
            stmt.setString(5,usuario.getContrasena());
            stmt.setString(6,usuario.getRol());
            stmt.execute();

            return 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Metodo para listar los usuarios
     * @return un ArrayList con los usuarios
     */
    public ArrayList<Usuario> listarUsuarios()
    {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            String query = "SELECT * FROM vw_usuarios";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStringConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNombre_completo(rs.getString("NOMBRE_COMPLETO"));
                usuario.setDireccion(rs.getString("DIRECCION"));
                usuario.setTelefono(rs.getString("TELEFONO"));
                usuario.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setContrasena(rs.getString("CONTRASENA"));
                usuario.setRol(rs.getString("ROL"));
                usuarios.add(usuario);
            }
            conn.close();
        } catch (Exception e){
            return null;
        }
        return usuarios;
    }

    /**
     * Metodo para modificar un usuario
     * @param usuario es de tipo Usuario y corresponde al usuario por modificar
     * @return 0 si se modifico correctamente, 1 si no se pudo modificar
     */
    public int modificarUsuario(Usuario usuario) {
        try {
            //Se crea una nueva instancia del archivo de configuración
            Configuracion configuracion= new Configuracion();
            //Lo lee del archivo de configuracion
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            PreparedStatement stmt = null;
            String strConexion = configuracion.getStringConexion();
            String query = "EXECUTE sp_modificar_usuario ?, ?, ?, ?, ?, ?, ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);;
            stmt.setInt(1,usuario.getId());
            stmt.setString(2,usuario.getNombre_completo());
            stmt.setString(3,usuario.getDireccion());
            stmt.setString(4,usuario.getTelefono());
            stmt.setString(5,usuario.getNombre_usuario());
            stmt.setString(6,usuario.getContrasena());
            stmt.setString(7,usuario.getRol());
            stmt.execute();
            return 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Metodo para buscar un usuario por su nombre de usuario
     * @param nombreUsuario es de tipo String y corresponde al nombre de usuario del usuario por buscar
     * @return usuario es de tipo Usuario y corresponde al usuario por buscar
     */
    public Usuario buscarUsuario(String nombreUsuario)
    {
        Configuracion configuracion = new Configuracion();
        Usuario usuario = new Usuario();
        try {
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_usuario_por_nombre_de_usuario ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombreUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNombre_completo(rs.getString("NOMBRE_COMPLETO"));
                usuario.setDireccion(rs.getString("DIRECCION"));
                usuario.setTelefono(rs.getString("TELEFONO"));
                usuario.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setContrasena(rs.getString("CONTRASENA"));
                usuario.setRol(rs.getString("ROL"));
            }
            conn.close();
        } catch (Exception e){
            return null;
        }
        return usuario;
    }

    /**
     * Metodo para buscar un usuario por su id
     * @param idUsuario es de tipo int y corresponde al id del usuario por buscar
     * @return usuario es de tipo Usuario y corresponde al usuario por buscar
     */
    public Usuario buscarUsuario(int idUsuario)
    {
        Configuracion configuracion = new Configuracion();
        Usuario usuario = new Usuario();
        try {
            Class.forName(configuracion.getClaseJDBC());
            String strConexion = configuracion.getStringConexion();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            conn = DriverManager.getConnection(strConexion);
            String query = "EXECUTE sp_buscar_usuario_por_id ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("ID_USUARIO"));
                usuario.setNombre_completo(rs.getString("NOMBRE_COMPLETO"));
                usuario.setDireccion(rs.getString("DIRECCION"));
                usuario.setTelefono(rs.getString("TELEFONO"));
                usuario.setNombre_usuario(rs.getString("NOMBRE_USUARIO"));
                usuario.setContrasena(rs.getString("CONTRASENA"));
                usuario.setRol(rs.getString("ROL"));
            }
            conn.close();
        } catch (Exception e){
            return null;
        }
        return usuario;
    }

    /**
     * Metodo para eliminar un usuario
     * @param usuario es de tipo Usuario y corresponde al usuario por eliminar
     */
    public void eliminarUsuario(Usuario usuario) {
        try {
            //Se crea una nueva instancia del archivo de configuración
            Configuracion configuracion= new Configuracion();
            //Lo lee del archivo de configuracion
            Class.forName(configuracion.getClaseJDBC());
            Connection conn = null;
            PreparedStatement stmt = null;
            String strConexion = configuracion.getStringConexion();
            conn = DriverManager.getConnection(strConexion);

            String query = "EXECUTE sp_eliminar_usuario ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,usuario.getId());
            stmt.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}

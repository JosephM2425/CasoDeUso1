package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.UsuarioDAO;
import cr.ac.ucenfotec.bl.entities.Prestamo;
import cr.ac.ucenfotec.bl.entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioGestor {
    private UsuarioDAO usuarioDAO;

    public UsuarioGestor() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public String registrarUsuario(String nombre_completo, String direccion, String telefono, String nombre_usuario, String contrasena, String rol) {
        Usuario usuario = new Usuario(nombre_completo, direccion, telefono, nombre_usuario, contrasena, rol);
        if (!existeUsuario(usuario)) {
            int resultadoRegistro = usuarioDAO.registrarUsuario(usuario);
            if (resultadoRegistro == 0) {
                return "Registro Exitoso!";
            } else {
                return "Hubo un error en el registro, porfavor verifique los datos";
            }
        } else {
            return "Usuario ya existente";
        }
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public String modificarUsuario(Usuario usuario) {
        if (existeUsuario(usuario)) {
            int resultadoRegistro = usuarioDAO.modificarUsuario(usuario);
            if (resultadoRegistro == 0) {
                return "Registro Exitoso!";
            } else {
                return "Hubo un error en el registro, porfavor verifique los datos";
            }
        } else {
            return "Usuario ya existente";
        }
    }

    public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioDAO.buscarUsuario(nombreUsuario);
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
            return usuarioDAO.buscarUsuario(idUsuario);
    }

    public String eliminarUsuario(int idUsuario) {
        Usuario usuario = usuarioDAO.buscarUsuario(idUsuario);
        usuarioDAO.eliminarUsuario(usuario);

        if (usuarioDAO.buscarUsuario(idUsuario) ==  null) {
            return "Usuario eliminado!";
        } else {
            return "Usuario no pudo ser eliminado!";
        }
    }

    public boolean existeUsuario(Usuario usuario) {
        if (usuarioDAO.buscarUsuario(usuario.getId()) == null) {
            return false;
        } else {
            return true;
        }
    }
}

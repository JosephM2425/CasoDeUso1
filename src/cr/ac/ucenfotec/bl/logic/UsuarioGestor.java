package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.UsuarioDAO;
import cr.ac.ucenfotec.bl.entities.Usuario;
import java.util.ArrayList;

/**
 * Clase UsuarioGestor que se encarga de la gestión de usuarios
 * @version 1.0
 * @since 06/06/2022
 * @author Andres Soza
 */
public class UsuarioGestor {
    private UsuarioDAO usuarioDAO;

    public UsuarioGestor() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public String registrarUsuario(Usuario usuario) {
        if (!existeUsuario(usuario.getNombre_usuario())) {
            int resultadoRegistro = usuarioDAO.registrarUsuario(usuario);
            if (resultadoRegistro == 0) {
                return "Registro exitoso!";
            } else {
                return "Hubo un error en el registro, por favor verifique los datos";
            }
        } else {
            return "Usuario ya existente. Por favor elija otro nombre de usuario.";
        }
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public ArrayList<Usuario> listarUsuarios(String tipoUsuario) {
        return usuarioDAO.listarUsuarios(tipoUsuario);
    }

    public String modificarUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getNombre_usuario())) {
            int idUsuario = usuarioDAO.buscarUsuario(usuario.getNombre_usuario()).getId();
            usuario.setId(idUsuario);
            int resultadoRegistro = usuarioDAO.modificarUsuario(usuario);
            if (resultadoRegistro == 0) {
                return "Modificación exitosa!";
            } else {
                return "Hubo un error en la modificación, por favor verifique los datos.";
            }
        } else {
            return "Usuario no existe. Por favor verifique el nombre de usuario.";
        }
    }

    public Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        return usuarioDAO.buscarUsuario(nombreUsuario);
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
            return usuarioDAO.buscarUsuario(idUsuario);
    }


    public String eliminarUsuario(String nombreUsuario) {
        Usuario usuario = usuarioDAO.buscarUsuario(nombreUsuario);
        if (usuario !=  null) {
            usuarioDAO.eliminarUsuario(usuario);
            return "Usuario eliminado!";
        } else {
            return "Usuario no existe. Por favor verifique el nombre de usuario.";
        }
    }

    public boolean existeUsuario(Usuario usuario) {
        if (usuarioDAO.buscarUsuario(usuario.getId()) == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existeUsuario(String nombreUsuario) {
        if (usuarioDAO.buscarUsuario(nombreUsuario) == null) {
            return false;
        } else {
            return true;
        }
    }
}

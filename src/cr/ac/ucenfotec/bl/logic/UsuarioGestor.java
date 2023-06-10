package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.UsuarioDAO;
import cr.ac.ucenfotec.bl.entities.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioGestor {
    private UsuarioDAO usuarioDAO;

    public UsuarioGestor() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public String registrarUsuario(Usuario usuario) {
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
        if (!existeUsuario(usuario)) {
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
        return usuarioDAO.buscarUsuarioPorNombreUsuario(nombreUsuario);
    }

    public Usuario buscarUsuarioPorId(int idUsuario) {
            return usuarioDAO.buscarUsuarioPorId(idUsuario);
    }

    public boolean existeUsuario(Usuario usuario) {
        if (usuarioDAO.buscarUsuarioPorId(usuario.getId()) == null) {
            return false;
        } else {
            return true;
        }
    }
}

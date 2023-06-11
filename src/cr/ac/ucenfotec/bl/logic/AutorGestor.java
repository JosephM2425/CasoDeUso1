package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.AutorDAO;
import cr.ac.ucenfotec.bl.entities.Autor;

import java.util.ArrayList;

public class AutorGestor {
    private AutorDAO autorDAO;

    public AutorGestor() {
        this.autorDAO = new AutorDAO();
    }

    public String agregarAutor(Autor autor) {
        int resultado = autorDAO.agregarAutor(autor);
        if (resultado == 0) {
            return "Autor agregado exitosamente!";
        } else {
            return "Error en el registro del autor. Por favor, verifique los datos.";
        }
    }

    public ArrayList<Autor> listarAutores() {
        return autorDAO.listarAutores();
    }

    public Autor buscarAutorPorNombre (String nombreAutor) {
        return autorDAO.buscarAutor(nombreAutor);
    }
    public Autor buscarAutorPorID (int idAutor) {
        return autorDAO.buscarAutor(idAutor);
    }

    public boolean existeAutor(String nombreAutor) {
        boolean existeAutor = false;
        if(autorDAO.buscarAutor(nombreAutor) != null) {
            existeAutor = true;
        }
        return existeAutor;
    }

}

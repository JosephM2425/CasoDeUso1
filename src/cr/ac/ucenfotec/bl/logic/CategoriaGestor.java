package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.DAO.CategoriaDAO;
import cr.ac.ucenfotec.bl.entities.Categoria;

import java.util.ArrayList;

/**
 * Clase CategoriaGestor que se encarga de la gestión de categorias
 * @version 1.0
 * @since 06/06/2022
 * @author Andres Soza
 */
public class CategoriaGestor {
    private CategoriaDAO categoriaDAO;

    public CategoriaGestor() {
        this.categoriaDAO = new CategoriaDAO();
    }

    public String agregarCategoria(Categoria categoria) {
        int resultado = categoriaDAO.agregarCategoria(categoria);
        if (resultado == 0) {
            return "Categoría agregada exitosamente.";
        } else {
            return "Error al agregar la categoría. Por favor, verifique los datos.";
        }
    }

    public ArrayList<Categoria> listarCategorias() {
        return categoriaDAO.listarCategorias();
    }

    public Categoria buscarCategoriaPorNombre(String nombreCategoria) {
        return categoriaDAO.buscarCategoria(nombreCategoria);
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
        return categoriaDAO.buscarCategoria(idCategoria);
    }

    public boolean existeCategoria(String nombreCategoria) {
        boolean existeCategoria = false;
        if(categoriaDAO.buscarCategoria(nombreCategoria) != null) {
            existeCategoria = true;
        }
        return existeCategoria;
    }
}



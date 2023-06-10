package cr.ac.ucenfotec.bl.DAO;
import cr.ac.ucenfotec.bl.entities.Prestamo;
import cr.ac.ucenfotec.bl.config.Configuracion;


import java.sql.*;
import java.util.ArrayList;

/**
 * @author Carolina Arias
 * @version 1.0
 * @since 09/06/2023
 *
 * Esta clase se encarga de gestionar el acceso a datos de los objetos Prestamo
 */
public class PrestamoDAO {

        /**
         * Metodo para registrar un prestamo
         * @param prestamo es de tipo Prestamo y corresponde al prestamo por registrar
         * @return 0 si se registro correctamente, 1 si no se pudo registrar
         */
        public int registrarPrestamo(Prestamo prestamo) {
            try {
                //Se crea una nueva instancia del archivo de configuración
                Configuracion configuracion = new Configuracion();
                //Lo lee del archivo de configuracion
                Class.forName(configuracion.getClaseJDBC());
                Connection conn = null;
                PreparedStatement stmt = null;

                String strConexion = configuracion.getStringConexion();
                String query = "EXECUTE sp_registrar_prestamo ?, ?, ?, ?";
                conn = DriverManager.getConnection(strConexion);
                stmt = conn.prepareStatement(query);;
                stmt.setDate(1, Date.valueOf(prestamo.getFecha_prestamo()));
                stmt.setDate(2, Date.valueOf(prestamo.getFecha_vencimiento()));
                stmt.setInt(3, prestamo.getLibro().getId());
                stmt.setInt(4, prestamo.getUsuario().getId());
                stmt.execute();

                return 0;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return 1;
            }
        }
}

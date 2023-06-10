package cr.ac.ucenfotec.bl.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Objeto de configuracion
 * @author Ruben Cantillo
 * @version 1.0.0
 * @since 09/06/2023
 */
public class Configuracion {
    //Atributos
    private String claseJDBC;
    private String stringConexion;

    //Constructores

    public Configuracion() {
        leerConfig();
    }

    public Configuracion(String claseJDBC, String stringConexion) {
        this.claseJDBC = claseJDBC;
        this.stringConexion = stringConexion;
    }

    //Getters y Setters
    public String getClaseJDBC() {
        return claseJDBC;
    }

    public void setClaseJDBC(String claseJDBC) {
        this.claseJDBC = claseJDBC;
    }

    public String getStringConexion() {
        return stringConexion;
    }

    public void setStringConexion(String stringConexion) {
        this.stringConexion = stringConexion;
    }

    public void leerConfig(){
        try {
            Properties properties = new Properties();
            String path = "C:\\Users\\caria\\OneDrive - Diagnostika\\Documentos\\U\\CENFOTEC\\9. Progra con Patrones\\Caso de Estudio 1\\Repositorio\\CasoDeUso1\\src\\cr\\ac\\ucenfotec\\bl\\config\\config.properties";
            FileInputStream fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
            this.setClaseJDBC(properties.getProperty("claseJDBC"));
            this.setStringConexion(properties.getProperty("stringConexion"));
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo de propiedades, reinicie");
        } catch (IOException e) {
            System.out.println("No se ha podido cargar la base de datos, por favor reinicie");
        }
    }
}

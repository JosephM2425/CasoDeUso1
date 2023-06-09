package cr.ac.ucenfotec.bl.entities;

/**
 * @author Joseph Murillo
 * @version 1.0
 * @since 08/06/2023
 *
 * Esta clase se encarga de gestionar todos los objetos Usuario
 */
public class Usuario {
    /**
     * Declaracion de atributos del objeto
     */
    private int id;
    private String nombre_completo;
    private String direccion;
    private String telefono;
    private String nombre_usuario;
    private String contrasena;
    private String rol;

    //Seteo de los constructores

    /**
     * Este es el constructor por defecto
     */
    public Usuario() {
    }

    /**
     * Crea una nueva instancia de la clase Usuario
     *
     * @param id es de tipo int y corresponde al ID del usuario
     * @param nombre_completo es de tipo String y corresponde al nombre completo del usuario
     * @param direccion es de tipo String y corresponde a la dirección del usuario
     * @param telefono es de tipo String y corresponde al número de teléfono del usuario
     * @param nombre_usuario es de tipo String y corresponde al nombre de usuario del usuario
     * @param contrasena es de tipo String y corresponde a la contraseña del usuario
     * @param rol es de tipo String y corresponde al rol del usuario
     */
    public Usuario(int id, String nombre_completo, String direccion, String telefono, String nombre_usuario, String contrasena, String rol) {
        this.id = id;
        this.nombre_completo = nombre_completo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.nombre_usuario = nombre_usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    /**
     * Getters y setters de los atributos del objeto
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Metodo que devuelve los atributos en formato String
     * @return devuelve todos los atributos del objeto en formato String
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre_completo='" + nombre_completo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombre_usuario='" + nombre_usuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}

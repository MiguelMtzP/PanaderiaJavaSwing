package Models;

public class Empleado {
    
    private String nombre;
    private String appPat;
    private String appMat;
    private String telefono;
    private int rol;
    private int idEmpleado ;
    private String Password;
    
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the appPat
     */
    public String getAppPat() {
        return appPat;
    }

    /**
     * @param appPat the appPat to set
     */
    public void setAppPat(String appPat) {
        this.appPat = appPat;
    }

    /**
     * @return the appMat
     */
    public String getAppMat() {
        return appMat;
    }

    /**
     * @param appMat the appMat to set
     */
    public void setAppMat(String appMat) {
        this.appMat = appMat;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the rol
     */
    public int getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(int rol) {
        this.rol = rol;
    }
    
    public String getFullName(){
        return nombre+" "+appPat+" "+appMat;
    }
    
    @Override
    public String toString(){
        return nombre+" "+appPat+" "+appMat+" "+telefono+" "+Password+" "+rol;
    }
}

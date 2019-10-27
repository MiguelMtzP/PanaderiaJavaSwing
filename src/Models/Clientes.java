package Models;

/**
 *
 * @author alexis
 */
public class Clientes {
    
    private String nombre;
    private String appPat;
    private String appMat;
    private String telefono;
    private String correo;
    
    public String getnombre(){
        return nombre;
    }
    
    public void setnombre(String nombre){
        this.nombre = nombre;
    }

    public String getAppPat() {
        return appPat;
    }

    public void setAppPat(String appPat) {
        this.appPat = appPat;
    }

    public String getAppMat() {
        return appMat;
    }

    public void setAppMat(String appMat) {
        this.appMat = appMat;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}

package Models;

/**
 *
 * @author alexis
 */
public class Clientes {
    
    private int idCliente;
    private String nombre;
    private String appPat;
    private String appMat;
    private String telefono;
    private String correo;
    private String empresa;
    
    public int getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
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

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    public String FullName(){
        return appPat + " "+appMat+" "+nombre;
    }
    
}

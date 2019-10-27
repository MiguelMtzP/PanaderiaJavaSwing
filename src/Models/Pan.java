package Models;


public class Pan {
    
    private int idPan;
    private String nombre;
    private float costo;
    private int existencia;
    
    public int getidPan(){
        return idPan;
    }
    
    public void setidPan(int idPan) {
        this.idPan = idPan;
    }
    
    public String getnombre(){
        return nombre;
    }
    
    public void setnombre(String nombre){
        this.nombre = nombre;
    }
    
    public float getcosto(){
        return costo;
    }
    
    public void setcosto(float costo){
        this.costo = costo;
    }
    
    public int getexistencia(){
        return existencia;
    }
    
    public void setexistencia(int existencia){
        this.existencia = existencia;
    }
    
    public String toString(){
        return nombre+" "+costo+" "+existencia;
    }
}

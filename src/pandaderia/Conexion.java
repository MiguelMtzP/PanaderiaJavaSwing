package pandaderia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    public Connection conectar;
    
    public Conexion(){ //constructor
        
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3306/panaderia","root","mysqlrootpasswordhere");
        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR: no se encuentra el driver de la base de datos"+e1.getMessage());
        }catch(SQLException e2){
                 System.out.println("ERROR: fallo en SQL"+e2.getMessage());
        
        }  
    }
    
    public void Desconectar(){
        try {
            conectar.close();
        } catch (SQLException ex) {
            System.out.println("ERROR: fallo alcerrar"+ex.getMessage());
        }
    }
}

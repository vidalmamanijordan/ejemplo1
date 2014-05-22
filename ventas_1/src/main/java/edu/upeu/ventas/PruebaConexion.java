package edu.upeu.ventas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PruebaConexion {
  public Connection cn=null;
  public Statement st=null;
  public ResultSet rs=null; 
  
  public Connection conectar() throws Exception{
      try {
          
      String servidor="localhost";
      String puerto="3306";
      String usuario="ventas";
      String password="123456";
      String servicio="root";
      String driver="com.mysql.jdbc.Driver";
      String cadenaconexion="jdbc:mysql"+servidor+":"+puerto+":"+servicio;
      
      Class.forName(driver);      
      cn=(Connection) DriverManager.getConnection(cadenaconexion,usuario,password);
      System.out.println("la coneccion fue exitosa..."); 
      
      } catch (Exception e) {
      System.out.println("herror de conexion "+e.getMessage());
      }
        return cn;
  }
    public ResultSet ejecutarConsulta(String sql) throws Exception{
      st=(Statement) cn.createStatement();
      rs=st.executeQuery(sql);
      return rs;
  }
      public void ejecutarActualizacion(String sql) throws Exception{
      st=(Statement) cn.createStatement();
      st.executeUpdate(sql);
  }
     public void desconectar() throws Exception{
      cn.close();
  }   
}

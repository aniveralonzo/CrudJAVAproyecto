/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import modelo.Producto;

/**
 *
 * @author user hp
 */
public class ProductoDAO {
    public Connection connection;
    public PreparedStatement statement;
    public boolean estadoOperacion;
    
    //guardar 
    public  boolean guardar(Producto  producto) throws SQLException{
        String sql=null;
        estadoOperacion=false;
        connection=obtenerConexion();

        try{
            connection.setAutoCommit(false);
        sql="INSERT INTO productos (id, nombre, cantidad, precio, fecha_crear, fecha_actualizar)VALUES(?,?,?,?,?,?)";   
        statement=connection.prepareStatement(sql);
        
        statement.setString(1,null);//es autoincremento  por es null
        statement.setString(2,producto.getNombre()); 
        statement.setDouble(3,producto.getCantidad());         
        statement.setDouble(4,producto.getPrecio()); 
        statement.setDate(5,producto.getFechaCrear()); 
        statement.setDate(6,producto.getFechaActualizar());
        
        estadoOperacion=statement.executeUpdate()>0;
        
        connection.commit();
        statement.close();
        connection.close();
        
        }catch(SQLException e){
            connection.rollback();
            e.printStackTrace();        
        }

        return estadoOperacion;
    }
    //editar 
    public  boolean editar(Producto  producto) throws SQLException{
        String sql=null;
        estadoOperacion=false;
        connection=obtenerConexion();
        
        try{
            connection.setAutoCommit(false);
            sql="UPDATE productos SET nombre=?,cantidad=?,precio=?,fecha_actualizar=? WHERE id=?";
            statement=connection.prepareStatement(sql);
            
            statement.setString(1,producto.getNombre());
            statement.setDouble(2,producto.getCantidad());
            statement.setDouble(3,producto.getPrecio());
            statement.setDate(4,producto.getFechaActualizar());
            statement.setInt(5,producto.getId());
            
            estadoOperacion=statement.executeUpdate()>0;
            connection.commit();
            statement.close();
            connection.close();
        
        }catch(SQLException e){
            connection.rollback();
            e.printStackTrace();
        }
        
        
        return estadoOperacion;
       }


    //eliminar 
    public  boolean eliminar(int idProducto) throws SQLException{
        String sql=null;
        estadoOperacion=false;
        connection=obtenerConexion();
        
        try{
            connection.setAutoCommit(false);
            sql="DELETE FROM productos WHERE id=?";
            statement=connection.prepareStatement(sql);
            statement.setInt(1,idProducto);

            estadoOperacion=statement.executeUpdate()>0;
            connection.commit();
            statement.close();
            connection.close();
        
        }catch(SQLException e){
            connection.rollback();
            e.printStackTrace();
        }
        
        
        return estadoOperacion;
    }



    //obtener lista producto
    public  List<Producto> obtenerProducto(){
        return null;
    }
    //obtener lista producto
    public Producto obtenerProducto(int idProducto){
        return null;
    }
    
    //obtener conexion 
    public Connection obtenerConexion()throws SQLException{
        return Conexion.getConnection();
    }
    
    
}
 
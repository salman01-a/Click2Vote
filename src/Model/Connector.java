/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author salmanfaris
 */
public class Connector {
    private static String jdbc_driver = "com.mysql.cj.jdbc.Driver";
    private static String nama_db = "Click2Vote";
    private static String url_db = "jdbc:mysql://localhost:3306/"+nama_db;
    private static String username_db ="root";
    private static String pass_db = "";
    
    static Connection conn;
     
    public static  Connection Connect(){
        try{
        Class.forName(jdbc_driver);
        conn = DriverManager.getConnection(url_db,username_db,pass_db);
        return conn;
        }catch(ClassNotFoundException | SQLException err){
            System.out.println("Koneksi Gagal Dengan Eror: " + err);
        }
        return  conn;   
    }
}

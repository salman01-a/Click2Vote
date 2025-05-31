/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.User;

import Model.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author salmanfaris
 */
public class DAOUser implements InterfaceDAOUser{

    @Override
    public ModelUser Login(String Username, String Password) {
        ModelUser user = new ModelUser();
         try{
               String query = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement stmt = Connector.Connect().prepareStatement(query);
                stmt.setString(1, Username);
                stmt.setString(2, Password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    user.setId(rs.getInt("id_user"));
                    user.setFull_name(rs.getString("full_name"));
                    user.setUsername(rs.getString("username")); 
                    user.setRoles(rs.getString("role"));
                    return user;
                }   
                stmt.close();
            }catch(SQLException err){

                System.out.println("Error : " + err.getLocalizedMessage());
            }
            return null;
    }

    @Override
    public void Signin(ModelUser user) {
      try{

               String query = "INSERT into users (username,password, full_name) VALUES (?,?,?)";
                PreparedStatement stmt = Connector.Connect().prepareStatement(query);
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                stmt.setString(3, user.getFull_name());
                stmt.executeUpdate();
                stmt.close();
                
            }catch(SQLException err){
                System.out.println("Error : " + err.getLocalizedMessage());
            }  
    }

    @Override
    public String isVote(int id_user) {
        String id;
        try{
               String query = "SELECT * FROM votes where id_user = ?";
                PreparedStatement stmt = Connector.Connect().prepareStatement(query);
                stmt.setInt(1,id_user);
             
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    id = Integer.toString(rs.getInt("id_user"));
                    return id;
                }   
                stmt.close();
            }catch(SQLException err){

                System.out.println("Error : " + err.getLocalizedMessage());
            }
            return null;
    }
    
}

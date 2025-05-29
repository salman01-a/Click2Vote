/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User.DAOUser;
import Model.User.InterfaceDAOUser;
import Model.User.ModelUser;
import View.*;

import javax.swing.JOptionPane;

/**
 *
 * @author salmanfaris
 */
public class ControllerUser {
    LoginPage Login;
    SigninPage Signin;
    InterfaceDAOUser daoUser;
    
    public ControllerUser(LoginPage Login){
        this.Login = Login;
        this.daoUser = new DAOUser();
    }
    public ControllerUser(SigninPage Signin){
        this.Signin = Signin;
        this.daoUser = new DAOUser();
    }
    
    public void Login( ){
        try{
            JOptionPane.showMessageDialog(Login, "Coba");
          String Name = Login.getUser();
          String Password = Login.getPass();
          String nama = daoUser.Login(Name, Password);
             

         if(nama != null){
             JOptionPane.showMessageDialog(Login, "Berhasil Login");
             
         }else{
             JOptionPane.showMessageDialog(Login, "Salah password");
         }     

        }catch(Exception err){
            System.out.println(err);
        }
    }
    
    public void Sigin(){
        
         
         try{
        ModelUser user = new ModelUser();
        String username = Signin.getUsername();
        String fullname = Signin.getFullName();
        String password = Signin.getPassword();
        
         if ("".equals(username) || "".equals(fullname) || "".equals(password)) {
             throw new Exception("Isi data dengan lengkap");
            }
         
         user.setUsername(username);
         user.setFull_name(fullname);
         user.setPassword(password);
         
         daoUser.Signin(user);
         JOptionPane.showMessageDialog(Signin, "Berhasil Signin");
         }catch(Exception err){
             JOptionPane.showMessageDialog(Signin, err);
         }
                
    }
}

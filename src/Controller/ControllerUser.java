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

    public ControllerUser(LoginPage Login) {
        this.Login = Login;
        this.daoUser = new DAOUser();
    }

    public ControllerUser(SigninPage Signin) {
        this.Signin = Signin;
        this.daoUser = new DAOUser();
    }
    public ControllerUser(){
         this.daoUser = new DAOUser();
    }

    public void Login() {
        try {
            JOptionPane.showMessageDialog(Login, "Coba");
            String Name = Login.getUser();
            String Password = Login.getPass();
            ModelUser user = daoUser.Login(Name, Password);

            if (user != null) {
                if ("admin".equals(user.getRoles())) { 
                    new View.Admin.Dashboard2().setVisible(true);
                } else {
                    new View.User.Dashboard(user.getId()).setVisible(true);
                }
                Login.dispose();
            } else {
                JOptionPane.showMessageDialog(Login, "Salah password");
            }

        } catch (Exception err) {
            System.out.println(err);
        }
    }

    public void Sigin() {

        try {
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
            Signin.dispose();
            new LoginPage();
            
        } catch (Exception err) {
            JOptionPane.showMessageDialog(Signin, err);
        }

    }
    
    public boolean isVote(int id){
        
       String id_user = daoUser.isVote(id);
       
       if(id_user != null){
           return true;
       }
     return false;   
    }
}

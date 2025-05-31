/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.User;

/**
 *
 * @author salmanfaris
 */
public interface InterfaceDAOUser {
    public ModelUser Login(String Username, String Password);
    public void Signin(ModelUser user);
    public String isVote(int id_user);
}

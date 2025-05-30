        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.Votes.*;
import java.util.List;
/**
 *
 * @author salmanfaris
 */
public class ControllerVoter {
     private final InterfaceDAOVotes daoVotes;

    public ControllerVoter() {
        this.daoVotes = new DAOVotes();
    }

    public List<ModelVotes> getAllVotes() {
        return daoVotes.getAll();
    }
}

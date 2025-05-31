        /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.HasilVotes.DAOHasilVotes;
import Model.HasilVotes.InterfaceDAOHasilVotes;
import Model.HasilVotes.ModelHasilVotes;
import View.Admin.*;
import Model.Votes.*;
import java.util.List;
/**
 *
 * @author salmanfaris
 */
public class ControllerVoter {
     private InterfaceDAOVotes daoVotes;
     private InterfaceDAOHasilVotes daoHasil;
     ListVoter Voter;
     HasilVote hasil;
     
    public ControllerVoter(ListVoter Voter) {
        this.Voter = Voter;
        this.daoVotes = new DAOVotes();
    }
    public ControllerVoter(HasilVote hassil) {
        this.hasil = hasil;
        this.daoHasil = new DAOHasilVotes();
    }

    public List<ModelVotes> getAllVotes() {
        return daoVotes.getAll();
    }
    
    
    
    public List<ModelHasilVotes> getHasilVoting() {
    return daoHasil.getHasilVoting();
}
    
}

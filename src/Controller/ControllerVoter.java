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
import View.User.Pilih;
import java.util.List;
/**
 *
 * @author salmanfaris
 */
public class ControllerVoter {
     private InterfaceDAOVotes daoVotes;
     private InterfaceDAOHasilVotes daoHasil;
     
     Pilih pilih;
     ListVoter Voter;
     HasilVote hasil;
     
    public ControllerVoter(ListVoter Voter) {
        this.Voter = Voter;
        this.daoVotes = new DAOVotes();
    }
    public ControllerVoter(HasilVote hasil) {
        this.hasil = hasil;
        this.daoHasil = new DAOHasilVotes();
    }

    public List<ModelVotes> getAllVotes() {
        return daoVotes.getAll();
    }
    
    public ControllerVoter(Pilih pilih) {
        this.pilih = pilih;
        this.daoVotes = new DAOVotes();
    }
    
    
    public List<ModelHasilVotes> getHasilVoting() {
    return daoHasil.getHasilVoting();
}
    
    public void Vote(int id_candidates, int id_user){
        System.out.println("ID USEER : "+id_user);   
        System.out.println("ID Kandidat : "+id_candidates); 
        
        daoVotes.Vote(id_candidates, id_user);
        new View.User.Pilih(id_user);
        
    }
    
    
    
}

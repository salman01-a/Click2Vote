/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Votes;

import Model.Kandidat.ModelKandidat;
import java.util.List;

/**
 *
 * @author salmanfaris
 */
public interface InterfaceDAOVotes {
    public List<ModelVotes> getAll();
    public void Vote (int id_kandidat, int id_user);
}

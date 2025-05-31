    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Votes;

import java.util.List;
import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author salmanfaris
 */
public class DAOVotes implements InterfaceDAOVotes {

    @Override
    public List<ModelVotes> getAll() {
        List<ModelVotes> list = new ArrayList<>();
        try {
            Connection conn = Connector.Connect();
            String query = """
                SELECT v.id_vote, u.full_name AS pemilih, c.name AS kandidat
                FROM votes v
                JOIN users u ON v.id_user = u.id_user
                JOIN candidates c ON v.id_candidate = c.id_candidate
                ORDER BY v.id_vote ASC
            """;
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ModelVotes(
                    rs.getInt("id_vote"),
                    rs.getString("pemilih"),
                    rs.getString("kandidat")
                ));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void Vote(int id_kandidat,int id_user) {
      try{
//          String query = "INSERT INTO candidates (name, photo_url, description, no_urut) VALUES (?, ?, ?, ?);";
//            PreparedStatement stmt = Connector.Connect().prepareStatement(query);
//            stmt.setString(1, kandidat.getNama());
//            stmt.setString(2, kandidat.getPhoto_url());
//            stmt.setString(3, kandidat.getDescription());
//            stmt.setString(4, kandidat.getNo_urut());
    //            stmt.executeUpdate();
//            stmt.close();
          Connection conn = Connector.Connect();
          String query = "INSERT INTO votes (id_user, id_candidate) VALUES (?,?)";
          PreparedStatement stmt = Connector.Connect().prepareStatement(query);
          stmt.setInt(1, id_user);
          stmt.setInt(2, id_kandidat);
            stmt.executeUpdate();
            stmt.close();
      }catch (SQLException e){
          System.out.println(e);
      }
    }
    
    
}

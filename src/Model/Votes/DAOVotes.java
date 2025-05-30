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
}

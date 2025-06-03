/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HasilVotes;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salmanfaris
 */
public class DAOHasilVotes implements InterfaceDAOHasilVotes {

    @Override
    public List<ModelHasilVotes> getHasilVoting() {
List<ModelHasilVotes> list = new ArrayList<>();
    try {
        Connection conn = Connector.Connect();
        String query = """
            SELECT c.name AS kandidat, COUNT(v.id_vote) AS jumlah_suara
            FROM candidates c
            LEFT JOIN votes v ON c.id_candidate = v.id_candidate
            GROUP BY c.id_candidate
            ORDER BY jumlah_suara DESC
        """;
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            list.add(new ModelHasilVotes(
                rs.getString("kandidat"),
                rs.getInt("jumlah_suara")
            ));
        }

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        System.out.println(e);
    }
    return list;
}

 
    
}

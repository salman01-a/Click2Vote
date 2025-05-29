/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Kandidat;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salmanfaris
 */
public class DAOKandidat implements InterfaceDAOKandidat{

    
    @Override
    public void insert(ModelKandidat kandidat) {
        try {
            String query = "INSERT INTO kandidat (nama, photo_url, description, no_urut) VALUES (?, ?, ?, ?);";
            PreparedStatement stmt = Connector.Connect().prepareStatement(query);
            stmt.setString(1, kandidat.getNama());
            stmt.setString(2, kandidat.getPhoto_url());
            stmt.setString(3, kandidat.getDescription());
            stmt.setString(4, kandidat.getNo_urut());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Insert Kandidat Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void update(ModelKandidat kandidat) {
        try {
            String query = "UPDATE kandidat SET nama=?, photo_url=?, description=?, no_urut=? WHERE id=?;";
            PreparedStatement stmt = Connector.Connect().prepareStatement(query);
            stmt.setString(1, kandidat.getNama());
            stmt.setString(2, kandidat.getPhoto_url());
            stmt.setString(3, kandidat.getDescription());
            stmt.setString(4, kandidat.getNo_urut());
            stmt.setInt(5, kandidat.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Update Kandidat Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM kandidat WHERE id=?;";
            PreparedStatement stmt = Connector.Connect().prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Delete Kandidat Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public List<ModelKandidat> getAll() {
        List<ModelKandidat> list = new ArrayList<>();
        try {
            Statement stmt = Connector.Connect().createStatement();
            String query = "SELECT * FROM kandidat ORDER BY no_urut;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ModelKandidat k = new ModelKandidat();
                k.setId(rs.getInt("id"));
                k.setNama(rs.getString("nama"));
                k.setPhoto_url(rs.getString("photo_url"));
                k.setDescription(rs.getString("description"));
                k.setNo_urut(rs.getString("no_urut"));
                list.add(k);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("GetAll Kandidat Failed! (" + e.getMessage() + ")");
        }
        return list;
    }
    
    
    
}

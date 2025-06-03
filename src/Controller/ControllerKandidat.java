/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import View.Admin.Kandidat2;
import Model.Kandidat.*;
import View.User.Kandidat;
import View.User.Pilih;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 *
 * @author salmanfaris
 */

   public class ControllerKandidat {
    private Kandidat2 view;
    DAOKandidat dao;
    Kandidat list;
    Pilih Pilih;
    String imagePath = "";

    public ControllerKandidat(Kandidat2 view) {
        this.view = view;
        this.dao = new DAOKandidat();
    }
    
    public ControllerKandidat(Kandidat list){
        this.list = list;
        dao = new DAOKandidat(); 
        
    }
 public ControllerKandidat(Pilih pilih){
        this.Pilih = pilih;
        dao = new DAOKandidat(); 
        
    }
   
    public List<ModelKandidat> getAllKandidat() {
       List<ModelKandidat> daftarKandidat = dao.getAll();
       return daftarKandidat;
        
   }

    /** Memasukkan kandidat baru */
    public void insert() {
        // Validasi input wajib diisi
        if (view.tfNama.getText().trim().isEmpty() ||
            view.tfNoUrut.getText().trim().isEmpty() ||
            view.tfDescription.getText().trim().isEmpty() ||
            view.tfPhotoUrl.getText().trim().isEmpty())
        {
            view.showWarning("Semua field harus diisi!");
            return;
        }

        String noUrut = view.tfNoUrut.getText().trim();
        // Cek duplikat no_urut
        for (ModelKandidat k : dao.getAll()) {
            if (k.getNo_urut().equalsIgnoreCase(noUrut)) {
                view.showWarning("Nomor urut sudah digunakan!");
                return;
            }
        }

        ModelKandidat k = new ModelKandidat();
        k.setNama(view.tfNama.getText());
        k.setPhoto_url(view.tfPhotoUrl.getText());
        k.setDescription(view.tfDescription.getText());
        k.setNo_urut(noUrut);

        dao.insert(k);
        view.showInfo("Data berhasil diinsert.");
    }

    /** Memperbarui kandidat yang sudah ter‐pilih */
    public void update() {
        if (view.tfId.getText().trim().isEmpty()) {
            view.showWarning("Data belum dipilih!");
            return;
        }

        int currentId;
        try {
            currentId = Integer.parseInt(view.tfId.getText());
        } catch (NumberFormatException e) {
            view.showError("ID harus berupa angka!");
            return;
        }

        String noUrut = view.tfNoUrut.getText().trim();
        for (ModelKandidat k : dao.getAll()) {
            if (k.getNo_urut().equalsIgnoreCase(noUrut) && k.getId() != currentId) {
                view.showWarning("Nomor urut sudah digunakan!");
                return;
            }
        }

        ModelKandidat k = new ModelKandidat();
        k.setId(currentId);
        k.setNama(view.tfNama.getText());
        k.setPhoto_url(view.tfPhotoUrl.getText());
        k.setDescription(view.tfDescription.getText());
        k.setNo_urut(noUrut);

        dao.update(k);
        view.showInfo("Data berhasil diupdate.");
    }

    /** Menghapus kandidat yang sudah ter‐pilih */
    public void delete() {
        if (view.tfId.getText().trim().isEmpty()) {
            view.showWarning("Data belum dipilih!");
            return;
        }

        int confirm = view.confirmDialog("Yakin ingin menghapus data ini?");
        if (confirm != Kandidat2.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(view.tfId.getText());
            dao.delete(id);
            view.showInfo("Data berhasil dihapus.");
        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("foreign key constraint")) {
                view.showError("Kandidat sudah dipilih, tidak bisa menghapus data!");
            } else {
                view.showError("Error saat menghapus data: " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            view.showError("ID harus berupa angka!");
        }
    }
}
    

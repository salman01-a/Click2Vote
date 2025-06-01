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
    Kandidat2 view;
    DAOKandidat dao;
    Kandidat list;
    Pilih Pilih;
    String imagePath = "";

    public ControllerKandidat(Kandidat2 view) {
        this.view = view;
        dao = new DAOKandidat();

        loadTable();

        view.btnInsert.addActionListener(e -> insert());
        view.btnUpdate.addActionListener(e -> update());
        view.btnDelete.addActionListener(e -> delete());
        view.btnReset.addActionListener(e -> reset());
        view.btnUpload.addActionListener(e -> uploadImage());

        view.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.table.getSelectedRow();
                view.tfId.setText(view.table.getValueAt(row, 0).toString());
                view.tfNama.setText(view.table.getValueAt(row, 1).toString());
                view.tfPhotoUrl.setText(view.table.getValueAt(row, 2).toString());
                view.tfDescription.setText(view.table.getValueAt(row, 3).toString());
                view.tfNoUrut.setText(view.table.getValueAt(row, 4).toString());

                String imgPath = view.table.getValueAt(row, 2).toString();
                if (!imgPath.isEmpty()) {
                    ImageIcon icon = new ImageIcon(new ImageIcon(imgPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    view.lblImagePreview.setIcon(icon);
                }
            }
        });
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

    public void insert() {
    // Validasi input wajib diisi
    if(view.tfNama.getText().trim().isEmpty() || 
       view.tfNoUrut.getText().trim().isEmpty() || 
       view.tfDescription.getText().trim().isEmpty() || 
       view.tfPhotoUrl.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(view, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }    
        
    String noUrut = view.tfNoUrut.getText().trim();

    // Cek duplikat no_urut
    for (ModelKandidat k : dao.getAll()) {
        if (k.getNo_urut().equalsIgnoreCase(noUrut)) {
            JOptionPane.showMessageDialog(view, "Nomor urut sudah digunakan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
    }

    ModelKandidat k = new ModelKandidat();
    k.setNama(view.tfNama.getText());
    k.setPhoto_url(view.tfPhotoUrl.getText());
    k.setDescription(view.tfDescription.getText());
    k.setNo_urut(noUrut);

    dao.insert(k);
    loadTable();
    reset();
    JOptionPane.showMessageDialog(view, "Data berhasil diinsert.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

}


    public void update() {
        if (view.tfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Data belum dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String noUrut = view.tfNoUrut.getText().trim();
        int currentId = Integer.parseInt(view.tfId.getText());

        // Cek duplikat no_urut dengan pengecualian id yang sedang diupdate
        for (ModelKandidat k : dao.getAll()) {
            if (k.getNo_urut().equalsIgnoreCase(noUrut) && k.getId() != currentId) {
                JOptionPane.showMessageDialog(view, "Nomor urut sudah digunakan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
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
        loadTable();
        reset();
        JOptionPane.showMessageDialog(view, "Data berhasil diupdate.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    public void delete() {
      if (view.tfId.getText().trim().isEmpty()) {
          JOptionPane.showMessageDialog(view, "Data belum dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
          return;
      }

      int confirm = JOptionPane.showConfirmDialog(view, "Yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

      if (confirm == JOptionPane.YES_OPTION) {
          try {
              int id = Integer.parseInt(view.tfId.getText());
              dao.delete(id);  // <- method ini sekarang bisa melempar SQLException
              loadTable();
              reset();
              JOptionPane.showMessageDialog(view, "Data berhasil dihapus.", "Sukses", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException e) {
              // Ini tangkap error foreign key constraint failure dan tampilkan pesan
              if (e.getMessage().toLowerCase().contains("foreign key constraint")) {
                  JOptionPane.showMessageDialog(view, "Kandidat sudah dipilih, tidak bisa menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
              } else {
                  JOptionPane.showMessageDialog(view, "Error saat menghapus data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
              }
          } catch (NumberFormatException e) {
              JOptionPane.showMessageDialog(view, "ID harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
          }
      }
  }


    public void reset() {
        view.tfId.setText("");
        view.tfNama.setText("");
        view.tfPhotoUrl.setText("");
        view.tfDescription.setText("");
        view.tfNoUrut.setText("");
        view.lblImagePreview.setIcon(null);
    }

    private void uploadImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));

        int result = chooser.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            String fileName = selectedFile.getName();
            String destPath = "src/Assets/FotoKandidat/" + fileName;

            try {
                Files.copy(selectedFile.toPath(), new File(destPath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                imagePath = destPath;
                view.tfPhotoUrl.setText(destPath);

                ImageIcon icon = new ImageIcon(new ImageIcon(destPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                view.lblImagePreview.setIcon(icon);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Gagal upload gambar.");
            }
        }
    }

    private void loadTable() {
        List<ModelKandidat> list = dao.getAll();
        DefaultTableModel model = (DefaultTableModel) view.table.getModel();
        model.setRowCount(0);

        for (ModelKandidat k : list) {
            model.addRow(new Object[]{
                k.getId(), k.getNama(), k.getPhoto_url(), k.getDescription(), k.getNo_urut()
            });
        }
    }
}

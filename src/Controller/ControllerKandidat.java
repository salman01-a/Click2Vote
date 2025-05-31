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
        ModelKandidat k = new ModelKandidat();
        k.setNama(view.tfNama.getText());
        k.setPhoto_url(view.tfPhotoUrl.getText());
        k.setDescription(view.tfDescription.getText());
        k.setNo_urut(view.tfNoUrut.getText());

        dao.insert(k);
        loadTable();
        reset();
    }

    public void update() {
        ModelKandidat k = new ModelKandidat();
        k.setId(Integer.parseInt(view.tfId.getText()));
        k.setNama(view.tfNama.getText());
        k.setPhoto_url(view.tfPhotoUrl.getText());
        k.setDescription(view.tfDescription.getText());
        k.setNo_urut(view.tfNoUrut.getText());

        dao.update(k);
        loadTable();
        reset();
    }

    public void delete() {
        int id = Integer.parseInt(view.tfId.getText());
        dao.delete(id);
        loadTable();
        reset();
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

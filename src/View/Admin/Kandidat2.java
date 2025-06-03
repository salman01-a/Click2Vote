package View.Admin;

import Controller.ControllerKandidat;
import Model.Kandidat.ModelKandidat;
import Model.Kandidat.ModelTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import static javax.swing.JOptionPane.YES_OPTION;

public class Kandidat2 extends JFrame {

    public static int YES_OPTION;

    public JTextField tfId = new JTextField();
    public JTextField tfNama = new JTextField();
    public JTextField tfPhotoUrl = new JTextField();
    public JTextField tfDescription = new JTextField();
    public JTextField tfNoUrut = new JTextField();

    public JButton btnInsert = new JButton("Insert");
    public JButton btnUpdate = new JButton("Update");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnReset = new JButton("Reset");
    public JButton btnUpload = new JButton("Upload");

    public JTable table;
    private ModelTable tableModel;

    public JLabel lblImagePreview = new JLabel();

 
    ControllerKandidat controller;

    public Kandidat2() {

        setTitle("Data Kandidat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(830, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // Buat instance controller
        controller = new ControllerKandidat(this);

        // Susun layout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel atas: Form + Preview Gambar
        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(850, 300));

        JPanel formPanel = createFormPanel();
        formPanel.setBounds(10, 10, 450, 280);
        topPanel.add(formPanel);

        JPanel imagePanel = createImagePanel();
        imagePanel.setBounds(540, 10, 150, 220);
        topPanel.add(imagePanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Panel bawah: Tabel Kandidat
        JPanel tablePanel = createTablePanel();
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);


        add(createNavbar(), BorderLayout.NORTH);

        attachListeners();

        setVisible(true);

        loadTableData();
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(450, 300));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Form Kandidat"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblId = new JLabel("ID:");
        JLabel lblNama = new JLabel("Nama:");
        JLabel lblPhoto = new JLabel("Photo Path:");
        JLabel lblDeskripsi = new JLabel("Deskripsi:");
        JLabel lblNoUrut = new JLabel("No Urut:");

        lblId.setBounds(20, 20, 100, 25);
        tfId.setBounds(130, 20, 250, 25);
//        tfId.setEditable(false); 
        lblNama.setBounds(20, 60, 100, 25);
        tfNama.setBounds(130, 60, 250, 25);

        lblPhoto.setBounds(20, 100, 100, 25);
        tfPhotoUrl.setBounds(130, 100, 250, 25);

        lblDeskripsi.setBounds(20, 140, 100, 25);
        tfDescription.setBounds(130, 140, 250, 25);

        lblNoUrut.setBounds(20, 180, 100, 25);
        tfNoUrut.setBounds(130, 180, 250, 25);

        // Tambahkan komponen label dan textfield
        formPanel.add(lblId);
        formPanel.add(tfId);
        formPanel.add(lblNama);
        formPanel.add(tfNama);
        formPanel.add(lblPhoto);
        formPanel.add(tfPhotoUrl);
        formPanel.add(lblDeskripsi);
        formPanel.add(tfDescription);
        formPanel.add(lblNoUrut);
        formPanel.add(tfNoUrut);

        // Tombol CRUD: Insert, Update, Delete, Reset
        btnInsert.setBounds(20, 230, 80, 30);
        btnUpdate.setBounds(110, 230, 80, 30);
        btnDelete.setBounds(200, 230, 80, 30);
        btnReset.setBounds(290, 230, 80, 30);

        btnInsert.setBackground(new Color(50, 120, 200));
        btnInsert.setForeground(Color.WHITE);
        btnUpdate.setBackground(new Color(40, 150, 100));
        btnUpdate.setForeground(Color.WHITE);
        btnDelete.setBackground(new Color(200, 60, 50));
        btnDelete.setForeground(Color.WHITE);
        btnReset.setBackground(new Color(180, 180, 180));

        formPanel.add(btnInsert);
        formPanel.add(btnUpdate);
        formPanel.add(btnDelete);
        formPanel.add(btnReset);

        return formPanel;
    }

    /** Membentuk panel untuk preview gambar + tombol Upload */
    private JPanel createImagePanel() {
        JPanel panel = new JPanel(null);
        panel.setBorder(BorderFactory.createTitledBorder("Preview Gambar"));
        panel.setPreferredSize(new Dimension(150, 220));

        lblImagePreview.setBounds(10, 20, 120, 160);
        lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        btnUpload.setBounds(10, 185, 120, 25);

        panel.add(lblImagePreview);
        panel.add(btnUpload);

        return panel;
    }


    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Daftar Kandidat"));


        tableModel = new ModelTable(List.of()); 
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    /** Membentuk JPanel navbar (Logout, Hasil Voting, Daftar Pemilih, Kandidat) */
    private JPanel createNavbar() {
        Color baseColor = new Color(111, 0, 162);
        Color hoverColor = new Color(140, 0, 190);

        JPanel navbar = new JPanel(null);
        navbar.setPreferredSize(new Dimension(700, 50));
        navbar.setBackground(baseColor);

        JLabel logo = new JLabel("Click2Vote-Admin");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 16));
        logo.setBounds(20, 15, 150, 20);
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Dashboard2().setVisible(true);
                dispose();
            }
        });
        navbar.add(logo);

        String[] navItems = {"Logout", "Hasil Voting", "Daftar Pemilih", "Kandidat"};
        int x = 725;
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setFocusPainted(false);
            navButton.setBackground(baseColor);
            navButton.setForeground(Color.WHITE);
            navButton.setBorder(null);
            navButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            navButton.setBounds(x, 10, 100, 30);
            navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            navButton.addActionListener(e -> {
                switch (item) {
                    case "Logout":
                        int confirm = confirmDialog("Yakin ingin logout?");
                        if (confirm == YES_OPTION) new View.LoginPage();
                        dispose();
                        break;
                    case "Kandidat":

                        break;
                    case "Daftar Pemilih":
                        new ListVoter().setVisible(true);
                        dispose();
                        break;
                    case "Hasil Voting":
                        new HasilVote().setVisible(true);
                        dispose();
                        break;
                }
            });

            navButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    navButton.setBackground(hoverColor);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    navButton.setBackground(baseColor);
                }
            });

            navbar.add(navButton);
            x -= 95;
        }

        return navbar;
    }

    /** Mendaftarkan semua listener (ActionListener dan MouseListener) */
    private void attachListeners() {

        btnUpload.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String destPath = "src/Assets/FotoKandidat/" + selectedFile.getName();

                try {
                    // Copy file ke folder tujuan
                    java.nio.file.Files.copy(
                        selectedFile.toPath(),
                        new File(destPath).toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                    );
                    tfPhotoUrl.setText(destPath);

                    // Tampilkan preview
                    ImageIcon icon = new ImageIcon(
                        new ImageIcon(destPath)
                            .getImage()
                            .getScaledInstance(100, 100, Image.SCALE_SMOOTH)
                    );
                    lblImagePreview.setIcon(icon);
                } catch (Exception ex) {
                    showError("Gagal upload gambar.");
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row < 0) return;

                // Ambil data langsung dari model
                Object id    = tableModel.getValueAt(row, 0);
                Object nama  = tableModel.getValueAt(row, 1);
                Object url   = tableModel.getValueAt(row, 2);
                Object desc  = tableModel.getValueAt(row, 3);
                Object noUrut = tableModel.getValueAt(row, 4);

                tfId.setText(id.toString());
                tfNama.setText(nama.toString());
                tfPhotoUrl.setText(url.toString());
                tfDescription.setText(desc.toString());
                tfNoUrut.setText(noUrut.toString());

                if (!url.toString().isBlank()) {
                    ImageIcon icon = new ImageIcon(
                        new ImageIcon(url.toString())
                            .getImage()
                            .getScaledInstance(100, 100, Image.SCALE_SMOOTH)
                    );
                    lblImagePreview.setIcon(icon);
                }
            }
        });

        // 3. Listener tombol Insert → panggil controller.insertKandidat(), lalu refresh tabel dan reset form
        btnInsert.addActionListener(e -> {
            controller.insert();
            loadTableData();
            resetForm();
        });

        // 4. Listener tombol Update → panggil controller.updateKandidat(), lalu refresh tabel dan reset form
        btnUpdate.addActionListener(e -> {
            controller.update();
            loadTableData();
            resetForm();
        });

        // 5. Listener tombol Delete → panggil controller.deleteKandidat(), lalu refresh tabel dan reset form
        btnDelete.addActionListener(e -> {
            controller.delete();
            loadTableData();
            resetForm();
        });

        btnReset.addActionListener(e -> resetForm());
    }

    private void loadTableData() {
        List<ModelKandidat> daftar = controller.getAllKandidat();
        tableModel = new ModelTable(daftar);
        table.setModel(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    }

    private void resetForm() {
        tfId.setText("");
        tfNama.setText("");
        tfPhotoUrl.setText("");
        tfDescription.setText("");
        tfNoUrut.setText("");
        lblImagePreview.setIcon(null);
    }

    public void showWarning(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    public void showInfo(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String pesan) {
        JOptionPane.showMessageDialog(this, pesan, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public int confirmDialog(String pesan) {
        return JOptionPane.showConfirmDialog(this, pesan, "Konfirmasi", JOptionPane.YES_NO_OPTION);
    }
}

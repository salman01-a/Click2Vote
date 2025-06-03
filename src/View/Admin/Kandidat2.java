package View.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Kandidat2 extends JFrame {

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
    public DefaultTableModel tableModel;
    public JLabel lblImagePreview = new JLabel();

    public Kandidat2() {
        setTitle("Data Kandidat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(830, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        
        
//        new Controller.ControllerKandidat(this);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(null);
        topPanel.setPreferredSize(new Dimension(850, 300));

        JPanel formPanel = createFormPanel();
        formPanel.setBounds(10, 10, 450, 280);
        topPanel.add(formPanel);

        JPanel imagePanel = createImagePanel();
        imagePanel.setBounds(540, 10, 150, 220);
        topPanel.add(imagePanel);

//        JPanel buttonPanel = createButtonPanel();
        JPanel tablePanel = createTablePanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
//        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        Color baseColor = new Color(111, 0, 162);
        Color hoverColor = new Color(140, 0, 190);


        JPanel navbar = new JPanel(null);
        navbar.setPreferredSize(new Dimension(700, 50));
        navbar.setBackground(baseColor);

        JLabel logo = new JLabel("Click2Vote-Admin");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 16));

        logo.setBounds(20, 15, 150, 20);
        navbar.add(logo);
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        logo.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) {
                new Dashboard2().setVisible(true); // Buka halaman Kandidat2
                dispose(); // Tutup halaman saat ini
            }
        });



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

            String action = item;
            navButton.addActionListener(e -> {
                System.out.println(action + " diklik!");
                if (action.equals("Logout")) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else if (action.equals("Kandidat")) {
                    new Kandidat2().setVisible(true);
                    dispose();

                } else if (action.equals("Daftar Pemilih")) {
                    new ListVoter().setVisible(true);
                    dispose();
                } else if (action.equals("Hasil Voting")) {
                    new HasilVote().setVisible(true);
                    dispose();

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

// Tambahkan navbar ke frame
        add(navbar, BorderLayout.NORTH);
        setVisible(true);

        btnUpload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                tfPhotoUrl.setText(path);
                setImagePreview(path);
            }
        });

       

        btnDelete.addActionListener(e -> {
            if (!tfId.getText().isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
            }
        });
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setPreferredSize(new Dimension(450, 300));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Form Kandidat"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Label dan field
        JLabel lblId = new JLabel("ID:");
        JLabel lblNama = new JLabel("Nama:");
        JLabel lblPhoto = new JLabel("Photo Path:");
        JLabel lblDeskripsi = new JLabel("Deskripsi:");
        JLabel lblNoUrut = new JLabel("No Urut:");

        lblId.setBounds(20, 20, 100, 25);
        tfId.setBounds(130, 20, 250, 25);
        lblNama.setBounds(20, 60, 100, 25);
        tfNama.setBounds(130, 60, 250, 25);
        lblPhoto.setBounds(20, 100, 100, 25);
        tfPhotoUrl.setBounds(130, 100, 250, 25);
        lblDeskripsi.setBounds(20, 140, 100, 25);
        tfDescription.setBounds(130, 140, 250, 25);
        lblNoUrut.setBounds(20, 180, 100, 25);
        tfNoUrut.setBounds(130, 180, 250, 25);

        // Tambah ke panel
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

        // Tambahkan tombol CRUD ke form
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

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "photo_url", "Deskripsi", "No Urut"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 200));

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public void setImagePreview(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(
                lblImagePreview.getWidth(),
                lblImagePreview.getHeight(),
                Image.SCALE_SMOOTH
        );
        lblImagePreview.setIcon(new ImageIcon(img));
    }
}

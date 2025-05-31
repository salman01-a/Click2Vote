package View.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Ayo Pemilu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);

        // Panel utama dengan BorderLayout agar navbar di atas
        JPanel mainContainer = new JPanel(new BorderLayout());

        // === NAVBAR ===
        JPanel navbar = new JPanel(null);
        navbar.setPreferredSize(new Dimension(1100, 50));
        Color baseColor = new Color(111, 0, 162);
        Color hoverColor = new Color(140, 0, 190);
        navbar.setBackground(baseColor);

        JLabel logo = new JLabel("Click2Vote");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 16));
        logo.setBounds(20, 15, 100, 20);
        navbar.add(logo);

        String[] navItems = {"Home", "Kandidat", "Pilih", "Logout"};
        int x = 720;
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setFocusPainted(false);
            navButton.setBackground(baseColor);
            navButton.setForeground(Color.WHITE);
            navButton.setBorder(null);
            navButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            navButton.setBounds(x, 10, 80, 30);
            navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            navButton.addActionListener(e -> {
                System.out.println(item + " diklik!");
                if (item.equals("Logout")) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else if (item.equals("Kandidat")) {
                    new Kandidat();
                     dispose();

                } else if(item.equals("Pilih")){
                    new Pilih();
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
            x += 90;
        }

        // === MAIN CONTENT ===
        JPanel mainPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(baseColor);
            }
        };

        // Judul
        JLabel title = new JLabel("AYO MEMILIH!");
        title.setFont(new Font("SansSerif", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(80, 30, 500, 50);
        mainPanel.add(title);

        // Deskripsi
        JTextArea description = new JTextArea(
                "Pemilihan Umum Desa adalah momen penting untuk menentukan\n" +
                "masa depan desa kita bersama. Dengan satu suara, Anda turut\n" +
                "serta membangun kebijakan yang berpihak pada kemajuan,\n" +
                "kesejahteraan, dan keadilan bagi semua warga.\n\n" +
                "Jangan biarkan kesempatan ini berlalu begitu saja! Pilih dengan bijak,\n" +
                "pilih untuk perubahan yang nyata.\n\n" +
                "Ayo gunakan hak pilih Anda, karena setiap suara adalah langkah\n" +
                "menuju masa depan yang lebih cerah untuk desa tercinta!");
        description.setFont(new Font("SansSerif", Font.PLAIN, 15));
        description.setForeground(Color.WHITE);
        description.setBackground(baseColor);
        description.setEditable(false);
        description.setBounds(80, 90, 520, 280);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        mainPanel.add(description);

        // Tombol "Pilih Sekarang"
        JButton pilihButton = new JButton("Pilih Sekarang");
        pilihButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        pilihButton.setBounds(80, 400, 180, 45);
        pilihButton.setBackground(Color.WHITE);
        pilihButton.setForeground(baseColor);
        pilihButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pilihButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                pilihButton.setBackground(hoverColor);
                pilihButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pilihButton.setBackground(Color.WHITE);
                pilihButton.setForeground(baseColor);
            }
        });

        mainPanel.add(pilihButton);

        // Gambar ilustrasi
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource("Assets/User/vote.png"));
            Image image = imageIcon.getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setBounds(550, 50, 450, 450);
            mainPanel.add(imageLabel);
        } catch (Exception ex) {
            System.err.println("Gambar tidak ditemukan di path: Assets/User/vote.png");
        }

        mainContainer.add(navbar, BorderLayout.NORTH);
        mainContainer.add(mainPanel, BorderLayout.CENTER);

        add(mainContainer);
        setVisible(true);
    }
}

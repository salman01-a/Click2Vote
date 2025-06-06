/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Controller.ControllerKandidat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author salmanfaris
 */
public class Dashboard2 extends JFrame {

    public Dashboard2() {
        setTitle("Data Kandidat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(830, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        Color baseColor = new Color(111, 0, 162);
        Color hoverColor = new Color(140, 0, 190);

        // Panel utama
        JPanel mainContainer = new JPanel(new BorderLayout());

        // === NAVBAR ===
        JPanel navbar = new JPanel(null);
        navbar.setPreferredSize(new Dimension(830, 50));
        navbar.setBackground(baseColor);

        JLabel logo = new JLabel("Click2Vote - Admin");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 16));
        logo.setBounds(20, 15, 200, 20);
        logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Dashboard2().setVisible(true);
                dispose();
            }
        });
        navbar.add(logo);

        // Tombol navigasi
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
                        int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) new View.LoginPage();
                        dispose();
                        break;
                    case "Kandidat":
                        new ControllerKandidat(new Kandidat2());
                        dispose();
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

        // === PANEL UTAMA (TEKS TENGAH) ===
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(baseColor);
            }
        };

        JLabel title = new JLabel("SELAMAT DATANG ADMIN!");
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);

        mainContainer.add(navbar, BorderLayout.NORTH);
        mainContainer.add(mainPanel, BorderLayout.CENTER);
        add(mainContainer);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard2::new);
    }
}

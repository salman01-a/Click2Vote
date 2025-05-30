/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author salmanfaris
 */
public class Dashboard2 extends JFrame {

    JLabel desc = new JLabel("Welkam Admin");
    public Dashboard2() {
         setTitle("Data Kandidat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
//                desc.setBounds(400, 350, 40, 30);
        setLayout(new BorderLayout(15, 15));
        
        
        
        
        Color baseColor = new Color(30, 144, 255);
        Color hoverColor = new Color(65, 105, 225);

        JPanel navbar = new JPanel(null); // pakai null layout agar bisa pakai setBounds
        navbar.setPreferredSize(new Dimension(700, 50));
        navbar.setBackground(baseColor);

        JLabel logo = new JLabel("Admin");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 16));
        logo.setBounds(20, 15, 100, 20);
        navbar.add(logo);
// Tambahkan tombol navigasi
        String[] navItems = {"Logout", "Hasil Voting", "Daftar Pemilih", "Kandidat"};
        int x = 725; // mulai dari kanan
        for (String item : navItems) {
            JButton navButton = new JButton(item);
            navButton.setFocusPainted(false);
            navButton.setBackground(baseColor);
            navButton.setForeground(Color.WHITE);
            navButton.setBorder(null);
            navButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            navButton.setBounds(x, 10, 100, 30);
            navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            String action = item; // harus final untuk lambda
            navButton.addActionListener(e -> {
                System.out.println(action + " diklik!");
                if (action.equals("Logout")) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else if (action.equals("Kandidat")) {
                    new Kandidat().setVisible(true);
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
            x -= 93;
        }


        desc.setFont(new Font("SansSerif", Font.BOLD, 24));
desc.setHorizontalAlignment(JLabel.CENTER);
desc.setVerticalAlignment(JLabel.CENTER); // Biar lebih pas

add(desc, BorderLayout.CENTER);
        add(navbar, BorderLayout.NORTH);
        setVisible(true);

    }
}

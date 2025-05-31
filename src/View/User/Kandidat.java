/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Lenovo
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Kandidat extends JFrame {

    public Kandidat() {
        setTitle("Temui Para Kandidat");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());

        // ========== NAVBAR ==========
        JPanel navbar = new JPanel(null);
        navbar.setPreferredSize(new Dimension(1100, 50));
        navbar.setBackground(new Color(111, 0, 162));

        JLabel logo = new JLabel("GO VOTE");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("SansSerif", Font.BOLD, 16));
        logo.setBounds(20, 15, 100, 20);
        navbar.add(logo);

        String[] navItems = {"Home", "Kandidat", "Pilih", "Logout"};
        int x = 725;
        for (String item : navItems) {
            JButton button = new JButton(item);
            button.setFocusPainted(false);
            button.setFont(new Font("SansSerif", Font.BOLD, 12));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(111, 0, 162));
            button.setBorder(null);
            button.setBounds(x, 10, 70, 30);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            button.addActionListener(e -> {
                System.out.println(item + " diklik!");
                if (item.equals("Home")) {
                    new Dashboard();
                    
                }
            });

            navbar.add(button);
            x += 90;
        }

        mainPanel.add(navbar, BorderLayout.NORTH);
  for(int i = 0; i < 3 ; i++){
                    // ========== CONTENT ==========
        JPanel content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.WHITE);

        JLabel title = new JLabel("Temui Para Kandidat");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(111, 0, 162));
        title.setBounds(430, 30, 300, 30);
        content.add(title);

        // Card kandidat
        JPanel card = new JPanel(null);
        card.setBounds(130, 100, 280, 400);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.setOpaque(true);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        card.setBounds(400, 100, 280, 370);
        card.setBackground(Color.WHITE);
        card.setVisible(true);

      
        // Foto kandidat
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Assets/User/dika.jpg"));
            Image image = icon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH);
            JLabel foto = new JLabel(new ImageIcon(image));
            foto.setBounds(0, 0, 280, 200);
            card.add(foto);
        } catch (Exception ex) {
            JLabel errorLabel = new JLabel("Foto tidak ditemukan");
            errorLabel.setBounds(10, 90, 200, 30);
            card.add(errorLabel);
        }

        // Nama kandidat
        JLabel nama = new JLabel("Dika", SwingConstants.CENTER);
        nama.setFont(new Font("SansSerif", Font.BOLD, 16));
        nama.setForeground(new Color(111, 0, 162));
        nama.setBounds(0, 210, 280, 30);
        card.add(nama);

        // Asal kandidat
        JLabel asal = new JLabel("2 | aceh", SwingConstants.CENTER);
        asal.setFont(new Font("SansSerif", Font.PLAIN, 14));
        asal.setForeground(Color.DARK_GRAY);
        asal.setBounds(0, 240, 280, 30);
        card.add(asal);

        // Tombol rincian
        JButton rincian = new JButton("Rincian");
        rincian.setBounds(90, 300, 100, 35);
        rincian.setBackground(new Color(72, 0, 173));
        rincian.setForeground(Color.WHITE);
        rincian.setFocusPainted(false);
        rincian.setFont(new Font("SansSerif", Font.BOLD, 13));
        rincian.setCursor(new Cursor(Cursor.HAND_CURSOR));

        rincian.addActionListener(e -> {
             if (e.getSource()== rincian) {
                    new DetailKandidat();
                } 
        });

        card.add(rincian);

        content.add(card);
        mainPanel.add(content, BorderLayout.CENTER);
        }
        add(mainPanel);
//        // ========== CONTENT ==========
//        JPanel content = new JPanel();
//        content.setLayout(null);
//        content.setBackground(Color.WHITE);
//
//        JLabel title = new JLabel("Temui Para Kandidat");
//        title.setFont(new Font("SansSerif", Font.BOLD, 24));
//        title.setForeground(new Color(111, 0, 162));
//        title.setBounds(430, 30, 300, 30);
//        content.add(title);
//
//        // Card kandidat
//        JPanel card = new JPanel(null);
//        card.setBounds(130, 100, 280, 400);
//        card.setBackground(Color.WHITE);
//        card.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(230, 230, 230)),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)
//        ));
//        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        card.setOpaque(true);
//        card.setLayout(null);
//        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
//        card.setBounds(400, 100, 280, 370);
//        card.setBackground(Color.WHITE);
//        card.setVisible(true);
//
//      
//        // Foto kandidat
//        try {
//            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Assets/User/dika.jpg"));
//            Image image = icon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH);
//            JLabel foto = new JLabel(new ImageIcon(image));
//            foto.setBounds(0, 0, 280, 200);
//            card.add(foto);
//        } catch (Exception ex) {
//            JLabel errorLabel = new JLabel("Foto tidak ditemukan");
//            errorLabel.setBounds(10, 90, 200, 30);
//            card.add(errorLabel);
//        }
//
//        // Nama kandidat
//        JLabel nama = new JLabel("Dika", SwingConstants.CENTER);
//        nama.setFont(new Font("SansSerif", Font.BOLD, 16));
//        nama.setForeground(new Color(111, 0, 162));
//        nama.setBounds(0, 210, 280, 30);
//        card.add(nama);
//
//        // Asal kandidat
//        JLabel asal = new JLabel("2 | aceh", SwingConstants.CENTER);
//        asal.setFont(new Font("SansSerif", Font.PLAIN, 14));
//        asal.setForeground(Color.DARK_GRAY);
//        asal.setBounds(0, 240, 280, 30);
//        card.add(asal);
//
//        // Tombol rincian
//        JButton rincian = new JButton("Rincian");
//        rincian.setBounds(90, 300, 100, 35);
//        rincian.setBackground(new Color(72, 0, 173));
//        rincian.setForeground(Color.WHITE);
//        rincian.setFocusPainted(false);
//        rincian.setFont(new Font("SansSerif", Font.BOLD, 13));
//        rincian.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        rincian.addActionListener(e -> {
//             if (e.getSource()== rincian) {
//                    new DetailKandidat();
//                } 
//        });
//
//        card.add(rincian);
//
//        content.add(card);
//        mainPanel.add(content, BorderLayout.CENTER);
//        add(mainPanel);
        setVisible(true);
    }

}

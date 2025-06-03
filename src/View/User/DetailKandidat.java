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

public class DetailKandidat extends JFrame {

    public DetailKandidat(String Nama, String no_urut, String desc, String photo_url,int id_user) {
        setTitle("Detail Kandidat");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());

        // ========== NAVBAR ==========
        JPanel navbar = new JPanel(null);
        navbar.setPreferredSize(new Dimension(1100, 50));
        navbar.setBackground(new Color(111, 0, 162));

        JLabel logo = new JLabel("Click2Vote");
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
                    new Dashboard(id_user);
                    dispose();
                } else if (item.equals("Kandidat")) {
                    new Kandidat(id_user);
                    dispose();
                } else if (item.equals("Pilih")) {
                    new Pilih(id_user);
                    dispose();
                } else if (item.equals("Logout")) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        new View.LoginPage();
                        dispose();
                    }
                } 
            });
            
            navbar.add(button);
            x += 90;
        }
        
        
        mainPanel.add(navbar, BorderLayout.NORTH);

        // ========== CONTENT ==========
        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);

        JPanel card = new JPanel(null);
        card.setBounds(400, 60, 300, 450);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Foto kandidat
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(photo_url));
            Image image = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel foto = new JLabel(new ImageIcon(image));
            foto.setBounds(0, 0, 300, 300);
            card.add(foto);
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Foto tidak ditemukan");
            errorLabel.setBounds(50, 130, 200, 30);
            card.add(errorLabel);
        }

        // Nama kandidat
        JLabel nama = new JLabel(Nama, SwingConstants.CENTER);
        nama.setFont(new Font("SansSerif", Font.BOLD, 24));
        nama.setBounds(0, 310, 300, 30);
        card.add(nama);

        // Nomor urut
        JLabel nomor = new JLabel("Nomor Urut "+ no_urut, SwingConstants.CENTER);
        nomor.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nomor.setForeground(Color.DARK_GRAY);
        nomor.setBounds(0, 340, 300, 20);
        card.add(nomor);


        // Info tambahan
        JLabel tambahan = new JLabel(desc, SwingConstants.CENTER);
        tambahan.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tambahan.setForeground(Color.DARK_GRAY);
        tambahan.setBounds(0, 380, 300, 20);
        card.add(tambahan);

        content.add(card);
        mainPanel.add(content, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}
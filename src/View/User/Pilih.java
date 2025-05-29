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

public class Pilih extends JFrame {

    public Pilih() {
        setTitle("Pilih Kandidat");
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
                // Tambahkan navigasi jika dibutuhkan
                if (item.equals("Home")) {
                    new Dashboard();
                } else if (item.equals("Kandidat")) {
                    new Kandidat();
                 }
            });

            navbar.add(button);
            x += 90;
        }

        mainPanel.add(navbar, BorderLayout.NORTH);

        // ========== CONTENT ==========
        JPanel content = new JPanel(null);
        content.setBackground(Color.WHITE);

        // Card kandidat
        JPanel card = new JPanel(null);
        card.setBounds(400, 100, 280, 370);
        card.setBackground(new Color(245, 239, 255)); // warna ungu muda lembut
        card.setBorder(BorderFactory.createLineBorder(new Color(180, 0, 200), 1, true));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
        nama.setForeground(new Color(72, 0, 173));
        nama.setBounds(0, 210, 280, 30);
        card.add(nama);

        // Nomor urut dan nama
        JLabel info = new JLabel("2 | Dika", SwingConstants.CENTER);
        info.setFont(new Font("SansSerif", Font.PLAIN, 14));
        info.setForeground(Color.DARK_GRAY);
        info.setBounds(0, 240, 280, 30);
        card.add(info);

        // Tombol Vote
        JButton vote = new JButton("Vote");
        vote.setBounds(90, 300, 100, 35);
        vote.setBackground(new Color(72, 0, 173));
        vote.setForeground(Color.WHITE);
        vote.setFocusPainted(false);
        vote.setFont(new Font("SansSerif", Font.BOLD, 13));
        vote.setCursor(new Cursor(Cursor.HAND_CURSOR));

        vote.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Anda memilih Dika.");
        });

        card.add(vote);

        content.add(card);
        mainPanel.add(content, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Pilih();
    }
}

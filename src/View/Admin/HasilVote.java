/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Admin;

import Controller.ControllerVoter;
import Model.HasilVotes.ModelHasilVotes;
import Model.HasilVotes.ModelTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author salmanfaris
 */
public class HasilVote extends JFrame{
    
    
    public HasilVote(){
      setTitle("Hasil Voting");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(830, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        

        Color baseColor = new Color(111, 0, 162);
        Color hoverColor = new Color(140, 0, 190);


// Panel Navbar
        JPanel navbar = new JPanel(null); // pakai null layout agar bisa pakai setBounds
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
                    View.Admin.Kandidat2 view = new View.Admin.Kandidat2();
                    new Controller.ControllerKandidat(view);
                    dispose(); 
                    
                } else if (action.equals("Daftar Pemilih")) {
                    new ListVoter().setVisible(true);
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
        
        ControllerVoter controller = new ControllerVoter(this);
        List<ModelHasilVotes> hasil = controller.getHasilVoting();

        JTable table = new JTable(new ModelTable(hasil));
        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Hasil Voting"));
        scrollPane.setPreferredSize(new Dimension(500, 400));

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true); 
    }
}

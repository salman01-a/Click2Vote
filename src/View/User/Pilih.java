/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.User;

/**
 *
 * @author Lenovo
 */
import Controller.ControllerKandidat;
import Controller.ControllerUser;
import Controller.ControllerVoter;
import Model.Kandidat.ModelKandidat;
import Model.User.ModelUser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Pilih extends JFrame {


    public Pilih(int id_user) {

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
                // Tambahkan navigasi jika dibutuhkan
                if (item.equals("Home")) {
                    new Dashboard(id_user);
                    dispose();
                } else if (item.equals("Kandidat")) {
                    new Kandidat(id_user);
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

        ControllerUser User = new ControllerUser();
        if (User.isVote(id_user)) {
            JOptionPane.showMessageDialog(this, "Anda sudah melakukan voting. Terima kasih!");
            new Dashboard(id_user); // Navigasi kembali ke dashboard
            dispose(); // Tutup window ini
            return;
        }
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);

        JLabel title = new JLabel("Pilih", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(111, 0, 162));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        content.add(title, BorderLayout.NORTH);

        JPanel cardContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        cardContainer.setBackground(new Color(245, 239, 255));

        ControllerKandidat controller = new ControllerKandidat(this);
        ControllerVoter voter = new ControllerVoter(this);
        List<ModelKandidat> daftarKandidat = controller.getAllKandidat();

        for (ModelKandidat kandidat : daftarKandidat) {
            JPanel card = new JPanel(null);
            card.setPreferredSize(new Dimension(280, 370));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

            String rawPath = kandidat.getPhoto_url(); // contoh: "src/Assets/FotoKandidat/vote.png"
            final String cleanedPath = rawPath.replaceFirst("src/", "");
            try {

                ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(cleanedPath));
                Image image = icon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH);
                JLabel foto = new JLabel(new ImageIcon(image));
                foto.setBounds(0, 0, 280, 200);
                card.add(foto);
            } catch (Exception ex) {
                JLabel errorLabel = new JLabel("Foto tidak ditemukan");
                errorLabel.setBounds(10, 90, 200, 30);
                card.add(errorLabel);
            }

            JLabel nama = new JLabel(kandidat.getNama(), SwingConstants.CENTER);
            nama.setFont(new Font("SansSerif", Font.BOLD, 16));
            nama.setForeground(new Color(111, 0, 162));
            nama.setBounds(0, 210, 280, 30);
            card.add(nama);

            JLabel noUrut = new JLabel("No. Urut: " + kandidat.getNo_urut(), SwingConstants.CENTER);
            noUrut.setFont(new Font("SansSerif", Font.PLAIN, 14));
            noUrut.setForeground(Color.DARK_GRAY);
            noUrut.setBounds(0, 240, 280, 30);
            card.add(noUrut);

            JButton vote = new JButton("vote");
            vote.setBounds(90, 300, 100, 35);
            vote.setBackground(new Color(72, 0, 173));
            vote.setForeground(Color.WHITE);
            vote.setFocusPainted(false);
            vote.setFont(new Font("SansSerif", Font.BOLD, 13));
            vote.setCursor(new Cursor(Cursor.HAND_CURSOR));

            vote.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    voter.Vote(
                            kandidat.getId(),
                            id_user
                    );
                  dispose();
                }

            });

            card.add(vote);
            cardContainer.add(card);
        }

         // Scroll pane yang membungkus cardContainer
JScrollPane scrollPane = new JScrollPane(cardContainer);
scrollPane.setBorder(null); // opsional, agar tidak ada garis tepi
scrollPane.getVerticalScrollBar().setUnitIncrement(16); // agar scroll lebih halus
scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

// Tambahkan scroll pane ke dalam content panel
content.add(scrollPane, BorderLayout.CENTER);

// Tambahkan content ke main panel, lalu set sebagai konten utama
mainPanel.add(content, BorderLayout.CENTER);
setContentPane(mainPanel);
setVisible(true);

    

    }

}

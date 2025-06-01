package View.Admin;

import Controller.ControllerVoter;
import Model.Votes.ModelTable;
import Model.Votes.ModelVotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ListVoter extends JFrame {

    public ListVoter() {
        setTitle("Data Pemilih");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(830, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

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

            navButton.addActionListener(e -> {
                if (item.equals("Logout")) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else if (item.equals("Kandidat")) {
                    View.Admin.Kandidat2 view = new View.Admin.Kandidat2();
                    new Controller.ControllerKandidat(view);// pastikan class Kandidat ada
                    dispose();
                }else if (item.equals("Hasil Voting")){
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
            x -= 93;
        }

        add(navbar, BorderLayout.NORTH);

        // === Ambil data dari Controller ===
   ControllerVoter controller = new ControllerVoter(this);
        List<ModelVotes> daftarVotes = controller.getAllVotes();
        JTable table = new JTable(new ModelTable(daftarVotes));

        table.setRowHeight(25);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.setEnabled(false);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Pemilih"));
        scrollPane.setPreferredSize(new Dimension(700, 500));

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ListVoter::new);
    }
}

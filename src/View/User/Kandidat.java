package View.User;
    
import Controller.ControllerKandidat;
import Model.Kandidat.ModelKandidat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
public class Kandidat extends JFrame {

    public Kandidat() {
        setTitle("Temui Para Kandidat");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // NAVBAR
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
                } else if (item.equals("Pilih")) {
                    new Pilih();
                    dispose();
                }
            });

            navbar.add(button);
            x += 90;
        }
        
        mainPanel.add(navbar, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.WHITE);

        JLabel title = new JLabel("Temui Para Kandidat", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(new Color(111, 0, 162));
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        content.add(title, BorderLayout.NORTH);

        JPanel cardContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        cardContainer.setBackground(Color.WHITE);
        ControllerKandidat controller = new ControllerKandidat (this);
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

            JButton rincian = new JButton("Rincian");
            rincian.setBounds(90, 300, 100, 35);
            rincian.setBackground(new Color(72, 0, 173));
            rincian.setForeground(Color.WHITE);
            rincian.setFocusPainted(false);
            rincian.setFont(new Font("SansSerif", Font.BOLD, 13));
            rincian.setCursor(new Cursor(Cursor.HAND_CURSOR));

            rincian.addActionListener(e -> new DetailKandidat(
                kandidat.getNama(),
                kandidat.getNo_urut(),
                kandidat.getDescription(),
                cleanedPath
            )
            );
            dispose();
            card.add(rincian);
            cardContainer.add(card);
        }
        
        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.setBorder(null); // opsional
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // untuk smooth scroll

// ⬇️ Masukkan JScrollPane ke content panel
        content.add(scrollPane, BorderLayout.CENTER);
        content.add(cardContainer, BorderLayout.CENTER);
        mainPanel.add(content, BorderLayout.CENTER);
        setContentPane(mainPanel);
        setVisible(true);
    
    }
}

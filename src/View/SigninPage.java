/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ControllerUser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.*    ;

/**
 *
 * @author salmanfaris
 */
public class SigninPage extends JFrame {
    JLabel headerLabel = new JLabel("Sign In - Click2Vote", SwingConstants.CENTER);
    JLabel login= new JLabel("Login");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel fullnameLabel = new JLabel("Full Name:");
    JLabel passwordLabel = new JLabel("Password:");
    
    JTextField usernameTextField = new JTextField();
    JTextField fullnameTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    
    JButton signInButton = new JButton("Sign In");
    JButton resetButton = new JButton("Reset");

     public SigninPage() {
        setTitle("Halaman Sign In");
        setSize(480, 400);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Background image setup
        ImageIcon icon = new ImageIcon(getClass().getResource("/Assets/Background/backgroudn pemilu.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(480, 400, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, 480, 400);
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Header style
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setBounds(0, 20, 480, 30);

        // Form fields
        usernameLabel.setBounds(20, 80, 150, 30);
        usernameTextField.setBounds(160, 80, 280, 30);

        fullnameLabel.setBounds(20, 130, 150, 30);
        fullnameTextField.setBounds(160, 130, 280, 30);

        passwordLabel.setBounds(20, 180, 150, 30);
        passwordField.setBounds(160, 180, 280, 30);

        // Buttons
        signInButton.setBounds(20, 240, 420, 40);
        signInButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        signInButton.setBackground(new java.awt.Color(144, 238, 144));
        signInButton.setForeground(java.awt.Color.BLACK);

        resetButton.setBounds(20, 300, 420, 40);
        resetButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        resetButton.setBackground(new java.awt.Color(255, 182, 193));
        resetButton.setForeground(java.awt.Color.BLACK);

        login.setBounds(395, 200, 150, 30);
        login.setForeground(java.awt.Color.WHITE);
        login.setFont(new Font("SansSerif", Font.PLAIN, 12));

        // Add components to layered pane
        layeredPane.add(headerLabel, Integer.valueOf(1));
        layeredPane.add(usernameLabel, Integer.valueOf(1));
        layeredPane.add(usernameTextField, Integer.valueOf(1));
        layeredPane.add(fullnameLabel, Integer.valueOf(1));
        layeredPane.add(fullnameTextField, Integer.valueOf(1));
        layeredPane.add(passwordLabel, Integer.valueOf(1));
        layeredPane.add(passwordField, Integer.valueOf(1));
        layeredPane.add(signInButton, Integer.valueOf(1));
        layeredPane.add(resetButton, Integer.valueOf(1));
        layeredPane.add(login, Integer.valueOf(1));

        ControllerUser controller = new ControllerUser(this);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new LoginPage();
                dispose();
            }
        });

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.Sigin();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameTextField.setText("");
                fullnameTextField.setText("");
                passwordField.setText("");
            }
        });

        setVisible(true);
    }
    
    public String getFullName(){
        return fullnameTextField.getText();
    }
    public String getUsername(){
        return usernameTextField.getText();
    }
    public String getPassword(){
        return new String(passwordField.getPassword());
    }
}

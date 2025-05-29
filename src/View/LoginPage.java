/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ControllerUser;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 *
 * @author salmanfaris
 */
public class LoginPage extends JFrame {
    JLabel Header = new JLabel("Click2Vote");
    JLabel Signin= new JLabel("SignIn");
    JLabel labelUsername = new JLabel("Username:");
    JLabel labelPassword = new JLabel("Password:");
    JTextField usernameTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();
    
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
     
      public LoginPage() {
        setTitle("Halaman Login");
        setSize(480, 360);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // === Background ===
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Assets/Background/backgroudn pemilu.jpg"));
        Image scaledImage = backgroundImage.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImage));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());

        // === Styling Components ===
        Header.setFont(new Font("SansSerif", Font.BOLD, 26));
        Header.setForeground(Color.WHITE);
        Header.setBounds(160, 10, 300, 40);

        labelUsername.setForeground(Color.WHITE);
        labelUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelUsername.setBounds(40, 60, 100, 25);

        labelPassword.setForeground(Color.WHITE);
        labelPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelPassword.setBounds(40, 110, 100, 25);

        usernameTextField.setBounds(150, 60, 270, 30);
        usernameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameTextField.setBackground(new Color(255, 255, 255, 220));
        usernameTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        passwordTextField.setBounds(150, 110, 270, 30);
        passwordTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordTextField.setBackground(new Color(255, 255, 255, 220));
        passwordTextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        loginButton.setBounds(40, 170, 380, 40);
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(new Color(173, 216, 230));
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);

        resetButton.setBounds(40, 220, 380, 40);
        resetButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resetButton.setBackground(new Color(255, 160, 122));
        resetButton.setForeground(Color.BLACK);
        resetButton.setFocusPainted(false);

        Signin.setForeground(Color.CYAN);
        Signin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        Signin.setBounds(370, 140, 100, 25);
        Signin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // === Layered Pane ===
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(backgroundLabel, Integer.valueOf(0));
        layeredPane.add(Header, Integer.valueOf(1));
        layeredPane.add(labelUsername, Integer.valueOf(1));
        layeredPane.add(usernameTextField, Integer.valueOf(1));
        layeredPane.add(labelPassword, Integer.valueOf(1));
        layeredPane.add(passwordTextField, Integer.valueOf(1));
        layeredPane.add(loginButton, Integer.valueOf(1));
        layeredPane.add(resetButton, Integer.valueOf(1));
        layeredPane.add(Signin, Integer.valueOf(1));

        // === Controller ===
        ControllerUser controller = new ControllerUser(this);

        // === Actions ===
        loginButton.addActionListener(e -> controller.Login());

        resetButton.addActionListener(e -> {
            usernameTextField.setText("");
            passwordTextField.setText("");
        });

        Signin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SigninPage();
                dispose();
            }
        });

        setVisible(true);
    }
    
    public String getUser(){
        return usernameTextField.getText();
    }
    public String getPass(){
        return new String(passwordTextField.getPassword());
    }
}

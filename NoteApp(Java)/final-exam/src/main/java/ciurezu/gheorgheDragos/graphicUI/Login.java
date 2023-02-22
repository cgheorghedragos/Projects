package ciurezu.gheorgheDragos.graphicUI;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private final int WIDTH_FORM_SIZE = 360;
    private final int HEIGHT_FORM_SIZE = 270;
    private final int WIDTH_ELEMENTS = 70;
    private final int HEIGHT_ELEMENTS = 20;

    private static volatile Login instance = null;
    private Dimension screenSize;

    private JButton loginButton;
    private JButton registerButton;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JLabel usernameLabel;
    private JLabel passwordLabel;

    private Login() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH_FORM_SIZE, HEIGHT_FORM_SIZE));
        this.setTitle("Login");
        this.setLayout(null);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
                screenSize.height / 2 - this.getSize().height / 2);
        this.init();
    }

    private void init() {
        this.getContentPane().setBackground(new Color(44, 47, 51));

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 40, WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        usernameLabel.setForeground(new Color(153, 170, 181));

        usernameField = new JTextField();
        usernameField.setBounds(usernameLabel.getX() + WIDTH_ELEMENTS, usernameLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        usernameField.setBackground(new Color(91, 91, 91));
        usernameField.setForeground(new Color(255, 255, 255));
        usernameField.setCaretColor(new Color(255, 255, 255));
        usernameField.setBorder(BorderFactory.createEmptyBorder());

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(usernameLabel.getX(), usernameLabel.getY() + HEIGHT_ELEMENTS * 2,
                WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        passwordLabel.setForeground(new Color(153, 170, 181));

        passwordField = new JPasswordField();
        passwordField.setBounds(usernameField.getX(), passwordLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        passwordField.setEchoChar('*');
        passwordField.setBackground(new Color(91, 91, 91));
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setCaretColor(new Color(255, 255, 255));
        passwordField.setBorder(BorderFactory.createEmptyBorder());

        int lengthFromLabelToField = passwordField.getX() + passwordField.getWidth() - passwordLabel.getX();

        registerButton = new JButton("Register");
        registerButton.setBounds(passwordLabel.getX(), passwordLabel.getY() + HEIGHT_ELEMENTS * 3,
                lengthFromLabelToField / 2 - 1, HEIGHT_ELEMENTS * 2);
        registerButton.setFocusPainted(false);
        registerButton.setBackground(new Color(114, 137, 218));
        registerButton.setForeground(new Color(255, 255, 255));
        registerButton.setBorder(BorderFactory.createEmptyBorder());

        loginButton = new JButton("Login");
        loginButton.setBounds(registerButton.getX() + lengthFromLabelToField / 2, registerButton.getY(),
                lengthFromLabelToField / 2, HEIGHT_ELEMENTS * 2);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(new Color(114, 137, 218));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setBorder(BorderFactory.createEmptyBorder());

        this.add(usernameLabel);
        this.add(passwordLabel);
        this.add(usernameField);
        this.add(passwordField);
        this.add(registerButton);
        this.add(loginButton);
    }

    public static Login getInstance() {
        synchronized (Login.class) {
            if (instance == null) {
                instance = new Login();
            }
        }
        return instance;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
}

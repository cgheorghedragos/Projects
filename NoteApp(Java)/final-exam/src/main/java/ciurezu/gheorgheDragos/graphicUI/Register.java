package ciurezu.gheorgheDragos.graphicUI;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {
    private final int WIDTH_FORM_SIZE = 380;
    private final int HEIGHT_FORM_SIZE = 400;
    private final int WIDTH_ELEMENTS = 80;
    private final int HEIGHT_ELEMENTS = 20;

    private JLabel realNameLabel;
    private JLabel usernameLabel;
    private JLabel ageLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel emailLabel;

    private JTextField realNameField;
    private JTextField usernameField;
    private JTextField ageField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField emailField;

    private JButton confirmButton;
    private JButton backButton;

    private static volatile Register instance = null;

    private Register() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH_FORM_SIZE, HEIGHT_FORM_SIZE));
        this.setTitle("Register");
        this.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
                screenSize.height / 2 - this.getSize().height / 2);
        this.init();
    }

    private void init() {
        this.getContentPane().setBackground(new Color(44, 47, 51));

        realNameLabel = new JLabel("Real Name:");
        realNameLabel.setBounds(45, 40, WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        realNameLabel.setForeground(new Color(153, 170, 181));

        realNameField = new JTextField();
        realNameField.setBounds(realNameLabel.getX() + 3 * WIDTH_ELEMENTS / 2, realNameLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        realNameField.setBackground(new Color(91, 91, 91));
        realNameField.setForeground(new Color(255,255,255));
        realNameField.setCaretColor(new Color(255,255,255));
        realNameField.setBorder(BorderFactory.createEmptyBorder());

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(realNameLabel.getX(), realNameLabel.getY() + HEIGHT_ELEMENTS * 2,
                WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        usernameLabel.setForeground(new Color(153, 170, 181));

        usernameField = new JTextField();
        usernameField.setBounds(realNameField.getX(), usernameLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        usernameField.setBackground(new Color(91, 91, 91));
        usernameField.setForeground(new Color(255,255,255));
        usernameField.setCaretColor(new Color(255,255,255));
        usernameField.setBorder(BorderFactory.createEmptyBorder());

        ageLabel = new JLabel("Age in Years:");
        ageLabel.setBounds(usernameLabel.getX(), usernameLabel.getY() + HEIGHT_ELEMENTS * 2,
                WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        ageLabel.setForeground(new Color(153, 170, 181));

        ageField = new JTextField();
        ageField.setBounds(usernameField.getX(), ageLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        ageField.setBackground(new Color(91, 91, 91));
        ageField.setForeground(new Color(255,255,255));
        ageField.setCaretColor(new Color(255,255,255));
        ageField.setBorder(BorderFactory.createEmptyBorder());

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(ageLabel.getX(), ageLabel.getY() + HEIGHT_ELEMENTS * 2,
                WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        emailLabel.setForeground(new Color(153, 170, 181));

        emailField = new JTextField();
        emailField.setBounds(ageField.getX(), emailLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        emailField.setBackground(new Color(91, 91, 91));
        emailField.setForeground(new Color(255,255,255));
        emailField.setCaretColor(new Color(255,255,255));
        emailField.setBorder(BorderFactory.createEmptyBorder());

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(emailLabel.getX(), emailLabel.getY() + HEIGHT_ELEMENTS * 2,
                WIDTH_ELEMENTS, HEIGHT_ELEMENTS);
        passwordLabel.setForeground(new Color(153, 170, 181));

        passwordField = new JPasswordField();
        passwordField.setBounds(emailField.getX(), passwordLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        passwordField.setBackground(new Color(91, 91, 91));
        passwordField.setEchoChar('*');
        passwordField.setForeground(new Color(255,255,255));
        passwordField.setCaretColor(new Color(255,255,255));
        passwordField.setBorder(BorderFactory.createEmptyBorder());

        confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setBounds(passwordLabel.getX(), passwordLabel.getY() + HEIGHT_ELEMENTS * 2,
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        confirmPasswordLabel.setForeground(new Color(153, 170, 181));

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(passwordField.getX(), confirmPasswordLabel.getY(),
                WIDTH_ELEMENTS * 2, HEIGHT_ELEMENTS);
        confirmPasswordField.setBackground(new Color(91, 91, 91));
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setForeground(new Color(255,255,255));
        confirmPasswordField.setCaretColor(new Color(255,255,255));
        confirmPasswordField.setBorder(BorderFactory.createEmptyBorder());

        int lengthFromLabelToField = passwordField.getX() + passwordField.getWidth() - passwordLabel.getX();

        backButton = new JButton("Back");
        backButton.setBounds(confirmPasswordLabel.getX(), confirmPasswordField.getY() + HEIGHT_ELEMENTS * 3,
                lengthFromLabelToField / 2 - 1, 3 * HEIGHT_ELEMENTS / 2);
        backButton.setFocusPainted(false);
        backButton.setBackground(new Color(114, 137, 218));
        backButton.setForeground(new Color(255,255,255));
        backButton.setBorder(BorderFactory.createEmptyBorder());

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(backButton.getX() + lengthFromLabelToField / 2, backButton.getY(),
                lengthFromLabelToField / 2, 3 * HEIGHT_ELEMENTS / 2);
        confirmButton.setFocusPainted(false);
        confirmButton.setBackground(new Color(114, 137, 218));
        confirmButton.setForeground(new Color(255,255,255));
        confirmButton.setBorder(BorderFactory.createEmptyBorder());

        this.add(realNameLabel);
        this.add(realNameField);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(ageLabel);
        this.add(ageField);
        this.add(emailLabel);
        this.add(emailField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(confirmPasswordLabel);
        this.add(confirmPasswordField);
        this.add(backButton);
        this.add(confirmButton);
        this.setVisible(true);
    }

    public static Register getInstance() {
        synchronized (Register.class) {
            if (instance == null) {
                instance = new Register();
            }
        }
        return instance;
    }

    public JTextField getRealNameField() {
        return realNameField;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JPasswordField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JTextField getAgeField() {
        return ageField;
    }
}

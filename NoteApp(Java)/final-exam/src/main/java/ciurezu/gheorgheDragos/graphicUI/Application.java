package ciurezu.gheorgheDragos.graphicUI;

import ciurezu.gheorgheDragos.models.Note;

import javax.swing.*;
import java.awt.*;


public class Application extends JFrame {
    private final int WIDTH_FORM_SIZE = 800;
    private final int HEIGHT_FORM_SIZE = 600;
    private final int WIDTH_CONTROL_PANEL = 200;

    private JPanel controlPanel;
    private JPanel notePanel;
    private JPanel settingsPanel;

    private JButton noteButton;
    private JButton settingsButton;
    private JButton disconnectButton;
    private JButton addNoteButton;
    private JButton viewNotesButton;
    private JButton deleteNoteButton;
    private JButton editCredentialsButton;

    private JLabel usernameLabel;
    private JLabel realNameLabel;
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel ageLabel;
    private JLabel newNoteLabel;
    private JLabel imageLabel;


    private JTextField usernameField;
    private JTextField realNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField ageField;
    private JTextField newNoteField;

    private JList<Note> noteJList;
    private JScrollPane scrollPaneList;

    private static volatile Application instance = null;
    private Dimension screenSize;

    private Application() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(WIDTH_FORM_SIZE, HEIGHT_FORM_SIZE));
        this.setTitle("Notes");
        this.setLayout(null);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width / 2 - this.getSize().width / 2,
                screenSize.height / 2 - this.getSize().height / 2);
        this.setResizable(false);
        this.init();
    }

    private void init() {
        createControlPanel();
        createNotePanel();
        createSettingsPanel();
        add(controlPanel);
        add(notePanel);
        add(settingsPanel);

    }

    private void createControlPanel() {
        controlPanel = new JPanel();
        controlPanel.setLayout(null);
        controlPanel.setBounds(00, 00, WIDTH_CONTROL_PANEL, HEIGHT_FORM_SIZE + 100);
        controlPanel.setVisible(true);
        controlPanel.setBackground(new Color(35, 39, 42));

        int buttonLength = WIDTH_CONTROL_PANEL - 20;
        int xButtonPosition = 10;
        int heightButton = 50;
        imageLabel = new JLabel();
        Image img = new ImageIcon("src/main/resources/utcn.jpg").getImage();
        imageLabel.setBounds(20, 50, WIDTH_CONTROL_PANEL - 40, WIDTH_CONTROL_PANEL - 40);
        imageLabel.setIcon(new ImageIcon(img));

        noteButton = new JButton("Notes");
        noteButton.setBounds(xButtonPosition, HEIGHT_FORM_SIZE / 2 - HEIGHT_FORM_SIZE / 18, buttonLength, heightButton);
        noteButton.setFocusPainted(false);
        noteButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setBackground(new Color(44, 47, 51));
        noteButton.setForeground(new Color(153, 170, 181));

        settingsButton = new JButton("Settings");
        settingsButton.setBounds(xButtonPosition, noteButton.getY() + heightButton + 5, buttonLength, heightButton);
        settingsButton.setFocusPainted(false);
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setBackground(new Color(44, 47, 51));
        settingsButton.setForeground(new Color(153, 170, 181));

        disconnectButton = new JButton("Disconnect");
        disconnectButton.setBounds(xButtonPosition, settingsButton.getY() + heightButton + 5, buttonLength, heightButton);
        disconnectButton.setFocusPainted(false);
        disconnectButton.setBorder(BorderFactory.createEmptyBorder());
        disconnectButton.setBackground(new Color(44, 47, 51));
        disconnectButton.setForeground(new Color(153, 170, 181));

        controlPanel.add(noteButton);
        controlPanel.add(settingsButton);
        controlPanel.add(disconnectButton);
        controlPanel.add(imageLabel);
    }

    private void createNotePanel() {
        int panelLength = WIDTH_FORM_SIZE - WIDTH_CONTROL_PANEL;

        notePanel = new JPanel();
        notePanel.setLayout(null);
        notePanel.setBounds(WIDTH_CONTROL_PANEL, 0, panelLength, HEIGHT_FORM_SIZE);
        notePanel.setBackground(new Color(44, 47, 51));

        viewNotesButton = new JButton("View all my notes");
        viewNotesButton.setBounds(100, 40, panelLength - 200, 20);
        viewNotesButton.setFocusPainted(false);
        viewNotesButton.setBackground(new Color(114, 137, 218));
        viewNotesButton.setForeground(new Color(255, 255, 255));
        viewNotesButton.setBorder(BorderFactory.createEmptyBorder());

        noteJList = new JList<>();
        scrollPaneList = new JScrollPane(noteJList);
        scrollPaneList.setBounds(viewNotesButton.getX(), viewNotesButton.getY() + 20,
                viewNotesButton.getWidth(), 320);
        noteJList.setBackground(new Color(91, 91, 91));
        noteJList.setForeground((new Color(255, 255, 255)));
        scrollPaneList.setBorder(BorderFactory.createEmptyBorder());


        deleteNoteButton = new JButton("Delete selected note");
        deleteNoteButton.setBounds(scrollPaneList.getX(), scrollPaneList.getY() + scrollPaneList.getHeight(),
                scrollPaneList.getWidth(), 20);
        deleteNoteButton.setFocusPainted(false);
        deleteNoteButton.setBackground(new Color(114, 137, 218));
        deleteNoteButton.setForeground(new Color(255, 255, 255));
        deleteNoteButton.setBorder(BorderFactory.createEmptyBorder());

        newNoteLabel = new JLabel("New note:");
        newNoteLabel.setBounds(deleteNoteButton.getX(), deleteNoteButton.getY() + deleteNoteButton.getHeight() * 3,
                deleteNoteButton.getWidth() / 4, 20);
        newNoteLabel.setForeground(new Color(153, 170, 181));

        newNoteField = new JTextField();
        newNoteField.setBounds(newNoteLabel.getX() + newNoteLabel.getWidth(), newNoteLabel.getY(),
                3 * deleteNoteButton.getWidth() / 4, 20);
        newNoteField.setBackground(new Color(91, 91, 91));
        newNoteField.setForeground(new Color(255, 255, 255));
        newNoteField.setCaretColor(new Color(255, 255, 255));
        newNoteField.setBorder(BorderFactory.createEmptyBorder());

        addNoteButton = new JButton("Add new note");
        addNoteButton.setBounds(newNoteLabel.getX(), newNoteLabel.getY() + newNoteLabel.getHeight() * 2,
                viewNotesButton.getWidth(), 20);
        addNoteButton.setBackground(new Color(114, 137, 218));
        addNoteButton.setForeground(new Color(255, 255, 255));
        addNoteButton.setBorder(BorderFactory.createEmptyBorder());

        notePanel.add(viewNotesButton);
        notePanel.add(scrollPaneList);
        notePanel.add(deleteNoteButton);
        notePanel.add(newNoteLabel);
        notePanel.add(newNoteField);
        notePanel.add(addNoteButton);
        notePanel.setVisible(false);
    }


    private void createSettingsPanel() {
        int xLabelPosition = (WIDTH_FORM_SIZE - WIDTH_CONTROL_PANEL) / 4;
        int labelLength = (WIDTH_FORM_SIZE - WIDTH_CONTROL_PANEL) / 7;
        int fieldLength = labelLength * 2;
        int elementHeight = 25;

        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBounds(WIDTH_CONTROL_PANEL, 0, WIDTH_FORM_SIZE - WIDTH_CONTROL_PANEL, HEIGHT_FORM_SIZE);
        settingsPanel.setBackground(new Color(44, 47, 51));

        realNameLabel = new JLabel("Real Name:");
        realNameLabel.setBounds(xLabelPosition, 120, labelLength, elementHeight);
        realNameLabel.setForeground(new Color(153, 170, 181));

        realNameField = new JTextField();
        realNameField.setBounds(realNameLabel.getX() + labelLength, realNameLabel.getY(),
                fieldLength, elementHeight);
        realNameField.setBorder(BorderFactory.createEmptyBorder());
        realNameField.setBackground(new Color(91, 91, 91));
        realNameField.setForeground(new Color(255, 255, 255));
        realNameField.setCaretColor(new Color(255, 255, 255));

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(realNameLabel.getX(), realNameLabel.getY() + elementHeight * 2,
                labelLength, elementHeight);
        usernameLabel.setForeground(new Color(153, 170, 181));

        usernameField = new JTextField();
        usernameField.setBounds(realNameField.getX(), usernameLabel.getY(),
                fieldLength, elementHeight);
        usernameField.setEnabled(false);
        usernameField.setBorder(BorderFactory.createEmptyBorder());
        usernameField.setBackground(new Color(91, 91, 91));
        usernameField.setForeground(new Color(255, 255, 255));
        usernameField.setCaretColor(new Color(255, 255, 255));

        ageLabel = new JLabel("Age in Years:");
        ageLabel.setBounds(usernameLabel.getX(), usernameLabel.getY() + elementHeight * 2,
                labelLength, elementHeight);
        ageLabel.setForeground(new Color(153, 170, 181));

        ageField = new JTextField();
        ageField.setBounds(usernameField.getX(), ageLabel.getY(),
                fieldLength, elementHeight);
        ageField.setBorder(BorderFactory.createEmptyBorder());
        ageField.setBackground(new Color(91, 91, 91));
        ageField.setCaretColor(new Color(255, 255, 255));
        ageField.setForeground(new Color(255, 255, 255));

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(ageLabel.getX(), ageLabel.getY() + elementHeight * 2,
                labelLength, elementHeight);
        emailLabel.setForeground(new Color(153, 170, 181));

        emailField = new JTextField();
        emailField.setBounds(ageField.getX(), emailLabel.getY(),
                fieldLength, elementHeight);
        emailField.setBorder(BorderFactory.createEmptyBorder());
        emailField.setBackground(new Color(91, 91, 91));
        emailField.setForeground(new Color(255, 255, 255));
        emailField.setCaretColor(new Color(255, 255, 255));

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(emailLabel.getX(), emailLabel.getY() + elementHeight * 2,
                labelLength, elementHeight);
        passwordLabel.setForeground(new Color(153, 170, 181));

        passwordField = new JPasswordField();
        passwordField.setBounds(emailField.getX(), passwordLabel.getY(),
                fieldLength, elementHeight);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordField.setEchoChar('*');
        passwordField.setBackground(new Color(91, 91, 91));
        passwordField.setForeground(new Color(255, 255, 255));
        passwordField.setCaretColor(new Color(255, 255, 255));

        editCredentialsButton = new JButton("Save Changes");
        editCredentialsButton.setBounds(passwordLabel.getX() + labelLength / 2, passwordLabel.getY() + elementHeight * 3,
                fieldLength, elementHeight * 5 / 4);
        editCredentialsButton.setFocusPainted(false);
        editCredentialsButton.setForeground(new Color(255, 255, 255));
        editCredentialsButton.setBackground(new Color(114, 137, 218));

        settingsPanel.add(realNameLabel);
        settingsPanel.add(realNameField);
        settingsPanel.add(usernameLabel);
        settingsPanel.add(usernameField);
        settingsPanel.add(ageLabel);
        settingsPanel.add(ageField);
        settingsPanel.add(emailLabel);
        settingsPanel.add(emailField);
        settingsPanel.add(passwordLabel);
        settingsPanel.add(passwordField);
        settingsPanel.add(editCredentialsButton);
        settingsPanel.setVisible(true);
    }


    public static Application getInstance() {
        synchronized (Application.class) {
            if (instance == null) {
                instance = new Application();
            }
            return instance;
        }
    }

    public JPanel getNotePanel() {
        return notePanel;
    }

    public JPanel getSettingsPanel() {
        return settingsPanel;
    }

    public JButton getNoteButton() {
        return noteButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getDisconnectButton() {
        return disconnectButton;
    }

    public JButton getAddNoteButton() {
        return addNoteButton;
    }

    public JButton getViewNotesButton() {
        return viewNotesButton;
    }

    public JButton getDeleteNoteButton() {
        return deleteNoteButton;
    }

    public JButton getEditCredentialsButton() {
        return editCredentialsButton;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JTextField getRealNameField() {
        return realNameField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JList<Note> getNoteJList() {
        return noteJList;
    }

    public JTextField getNewNoteField() {
        return newNoteField;
    }
}

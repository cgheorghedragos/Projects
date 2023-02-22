package ciurezu.gheorgheDragos.controlers;

import ciurezu.gheorgheDragos.exceptions.AgeException;
import ciurezu.gheorgheDragos.exceptions.InvalidCharactersException;
import ciurezu.gheorgheDragos.exceptions.NullTextFieldException;
import ciurezu.gheorgheDragos.graphicUI.Application;
import ciurezu.gheorgheDragos.graphicUI.Login;
import ciurezu.gheorgheDragos.models.Account;
import ciurezu.gheorgheDragos.models.Note;
import ciurezu.gheorgheDragos.services.AccountsService;
import ciurezu.gheorgheDragos.services.CryptService;
import ciurezu.gheorgheDragos.services.LoggerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

public class ApplicationController {
    private Account currentAccountSession;

    private static volatile ApplicationController instance;

    private final Application APPLICATION_VIEW = Application.getInstance();

    private ApplicationController() {
        APPLICATION_VIEW.getNoteButton().addActionListener(new NoteButtonFunctionality());
        APPLICATION_VIEW.getDisconnectButton().addActionListener(new DisconnectButtonFunctionality());
        APPLICATION_VIEW.getSettingsButton().addActionListener(new SettingsButtonFunctionality());
        APPLICATION_VIEW.getEditCredentialsButton().addActionListener(new SaveChangesButtonFunctionality());
        APPLICATION_VIEW.getAddNoteButton().addActionListener(new AddNewNoteButtonFunctionality());
        APPLICATION_VIEW.getViewNotesButton().addActionListener(new ViewAllNotesButtonFunctionality());
        APPLICATION_VIEW.getDeleteNoteButton().addActionListener(new DeleteNoteButtonFunctionality());
    }

    private class SettingsButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            APPLICATION_VIEW.getNotePanel().setVisible(false);
            APPLICATION_VIEW.getSettingsPanel().setVisible(true);
            APPLICATION_VIEW.getUsernameField().setText(currentAccountSession.getUsername());
            APPLICATION_VIEW.getRealNameField().setText(currentAccountSession.getRealName());
            APPLICATION_VIEW.getAgeField().setText(String.valueOf(currentAccountSession.getAge()));
            APPLICATION_VIEW.getEmailField().setText(currentAccountSession.getEmail());
        }
    }


    private class DisconnectButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            APPLICATION_VIEW.setVisible(false);
            Login.getInstance().setVisible(true);
            try {
                LoggerService.activityLog(currentAccountSession.getUsername() + " disconnected!");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(APPLICATION_VIEW, "LoggerService Failed",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class NoteButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            APPLICATION_VIEW.getSettingsPanel().setVisible(false);
            APPLICATION_VIEW.getNotePanel().setVisible(true);
        }
    }

    private class SaveChangesButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String realName = APPLICATION_VIEW.getRealNameField().getText();
            String password = APPLICATION_VIEW.getPasswordField().getText();
            String email = APPLICATION_VIEW.getEmailField().getText();

            int age = Integer.parseInt(APPLICATION_VIEW.getAgeField().getText());
            try {
                try {
                    checkValidityOfTextBoxes(password, email, age, realName);

                    currentAccountSession.setEmail(email);
                    currentAccountSession.setPassword(CryptService.encryptString(password));
                    currentAccountSession.setRealName(realName);
                    currentAccountSession.setAge(age);

                    AccountsService.saveAccounts(LoginController.getInstance().getACCOUNTS_FILE_LOCATION(),
                            LoginController.getInstance().getACCOUNTS_FILE_NAME(),
                            LoginController.getInstance().getAccountList());

                    JOptionPane.showMessageDialog(APPLICATION_VIEW,"Credentials changed successfully");
                    LoggerService.activityLog(currentAccountSession.getRealName()+" changed credentials successfully");
                } catch (NullTextFieldException nullTextFieldException) {
                    String messageError = nullTextFieldException.getMessage();

                    JOptionPane.showMessageDialog(APPLICATION_VIEW,
                            messageError,
                            "TextFieldError",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                } catch (AgeException ageException) {
                    String messageError = ageException.getMessage();

                    JOptionPane.showMessageDialog(APPLICATION_VIEW,
                            messageError,
                            "Age Error",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                } catch (InvalidCharactersException invalidCharactersException) {
                    String messageError = invalidCharactersException.getMessage();

                    JOptionPane.showMessageDialog(APPLICATION_VIEW,
                            messageError,
                            "Text field errors",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                }
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(APPLICATION_VIEW, "LoggerService Failed",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ViewAllNotesButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Vector<Note> vector = new Vector<>(currentAccountSession.getNoteList());
            APPLICATION_VIEW.getNoteJList().setListData(vector);
        }
    }

    private class AddNewNoteButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            currentAccountSession.addNote(new Note(APPLICATION_VIEW.getNewNoteField().getText(), new Date().toString()));
            JOptionPane.showMessageDialog(APPLICATION_VIEW, "Note added successfully!");
            APPLICATION_VIEW.getNewNoteField().setText("");
            try {
                AccountsService.saveAccounts(LoginController.getInstance().getACCOUNTS_FILE_LOCATION(),
                        LoginController.getInstance().getACCOUNTS_FILE_NAME(),
                        LoginController.getInstance().getAccountList());
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(APPLICATION_VIEW, "Failed to add save new note");
            }
            try {
                LoggerService.activityLog(currentAccountSession.getUsername() + " added a note!");
            } catch (IOException ioException) {
                JOptionPane.showMessageDialog(APPLICATION_VIEW, "LoggerService Failed!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }

            Vector<Note> vector = new Vector<>(currentAccountSession.getNoteList());
            APPLICATION_VIEW.getNoteJList().setListData(vector);
        }
    }

    public class DeleteNoteButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedItem = Application.getInstance().getNoteJList().getSelectedIndex();
            if (selectedItem >= 0) {
                currentAccountSession.getNoteList().remove(selectedItem);

                try {
                    LoggerService.activityLog(currentAccountSession.getUsername() + " deleted a note!");
                    JOptionPane.showMessageDialog(APPLICATION_VIEW, "Note deleted successfully!");

                    AccountsService.saveAccounts(LoginController.getInstance().getACCOUNTS_FILE_LOCATION(),
                            LoginController.getInstance().getACCOUNTS_FILE_NAME(),
                            LoginController.getInstance().getAccountList());
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(APPLICATION_VIEW, "LoggerService Failed!",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                Vector<Note> vector = new Vector<>(currentAccountSession.getNoteList());
                APPLICATION_VIEW.getNoteJList().setListData(vector);
            }
        }
    }

    public static ApplicationController getInstance() {
        synchronized (ApplicationController.class) {
            if (instance == null) {
                instance = new ApplicationController();
            }
        }
        return instance;
    }

    public void setCurrentAccountSession(Account currentAccountSession) {
        this.currentAccountSession = currentAccountSession;
    }

    private void checkValidityOfTextBoxes(String password, String email, int age, String realName)
            throws NullTextFieldException, AgeException, InvalidCharactersException {
        if (password.length() < 1 ||
                email.length() < 1) {
            throw new NullTextFieldException();
        }

        boolean isPasswordInvalid = password.contains(" ") || checkIfACharisNotALetter(password.charAt(0));
        boolean isEmailInvalid = email.contains(" ") || checkIfACharisNotALetter(email.charAt(0));
        boolean isRealNameInvalid = false;
        boolean isAgeInvalid = age < 18;

        String[] emailSplitByArond = email.split("@");

        if (emailSplitByArond.length != 2) {
            isEmailInvalid = true;
        } else {
            String[] emailSplitByPoint = emailSplitByArond[1].split("\\.");

            if (emailSplitByPoint.length > 3 || emailSplitByPoint.length < 2) {
                isEmailInvalid = true;
            } else {
                for (int i = 0; i < emailSplitByPoint.length; i++) {
                    if (emailSplitByPoint[i].length() < 2) {
                        isEmailInvalid = true;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < realName.length(); i++) {
            if (realName.charAt(i) != ' ') {
                if (checkIfACharisNotALetter(realName.charAt(i))) {
                    isRealNameInvalid = true;
                    break;
                }
            }
        }

        if (isAgeInvalid) {
            throw new AgeException(age);
        }

        if (isEmailInvalid || isPasswordInvalid || isRealNameInvalid) {
            throw new InvalidCharactersException(false, isEmailInvalid,
                    isPasswordInvalid, isRealNameInvalid);
        }
    }

    private boolean checkIfACharisNotALetter(char character) {
        if ((int) character >= 'A' && (int) character <= 'Z') {
            return false;
        }

        if ((int) character >= 'a' && (int) character <= 'z') {
            return false;
        }

        return true;
    }
}

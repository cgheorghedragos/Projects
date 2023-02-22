package ciurezu.gheorgheDragos.controlers;

import ciurezu.gheorgheDragos.exceptions.*;
import ciurezu.gheorgheDragos.graphicUI.Login;
import ciurezu.gheorgheDragos.graphicUI.Register;
import ciurezu.gheorgheDragos.models.Account;
import ciurezu.gheorgheDragos.services.AccountsService;
import ciurezu.gheorgheDragos.services.CryptService;
import ciurezu.gheorgheDragos.services.LoggerService;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class RegisterController {

    private final Register REGISTER_VIEW = Register.getInstance();
    private static volatile RegisterController instance = null;
    private List<Account> accountList = LoginController.getInstance().getAccountList();

    private RegisterController() {
        this.REGISTER_VIEW.getBackButton().addActionListener(new BackButtonFunctionality());
        this.REGISTER_VIEW.getConfirmButton().addActionListener(new ConfirmButtonFunctionality());
        this.REGISTER_VIEW.setVisible(false);
    }

    private class BackButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            REGISTER_VIEW.setVisible(false);
            Login.getInstance().setVisible(true);
        }
    }

    private class ConfirmButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                try {
                    int age = Integer.parseInt(REGISTER_VIEW.getAgeField().getText());
                    String realName = REGISTER_VIEW.getRealNameField().getText();
                    String username = REGISTER_VIEW.getUsernameField().getText();
                    String password = REGISTER_VIEW.getPasswordField().getText();
                    String confirmPassword = REGISTER_VIEW.getConfirmPasswordField().getText();
                    String email = REGISTER_VIEW.getEmailField().getText();

                    checkValidityOfTextBoxes(password, confirmPassword, username, email, age,realName);

                    String cryptPassword = CryptService.encryptString(password);
                    Account newAccount = new Account(realName, age, username, cryptPassword, email);

                    if (!checkIfTheUserExists(newAccount)) {
                        accountList.add(newAccount);

                        AccountsService.saveAccounts(LoginController.getInstance().getACCOUNTS_FILE_LOCATION(),
                                LoginController.getInstance().getACCOUNTS_FILE_NAME(), accountList);

                        JOptionPane.showMessageDialog(REGISTER_VIEW, "Account added successfully");

                        REGISTER_VIEW.setVisible(false);
                        Login.getInstance().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(REGISTER_VIEW, "Username exists!");
                    }


                } catch (NullTextFieldException nullTextFieldException) {
                    String messageError = nullTextFieldException.getMessage();

                    JOptionPane.showMessageDialog(REGISTER_VIEW,
                            messageError,
                            "NullTextFields",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                } catch (NumberFormatException numberFormatException) {
                    String messageError = "Age should be a number!";

                    JOptionPane.showMessageDialog(REGISTER_VIEW,
                            messageError,
                            "Invalid age",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                } catch (InvalidCharactersException charactersException) {
                    String messageError = charactersException.getMessage();

                    JOptionPane.showMessageDialog(REGISTER_VIEW,
                            messageError,
                            "Invalid input characters",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                } catch (InvalidPasswordException passwordException) {
                    String messageError = "Password doesn't match";

                    JOptionPane.showMessageDialog(REGISTER_VIEW,
                            messageError,
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                } catch (AgeException ageException) {
                    String messageError = "Age should be greater than 18";

                    JOptionPane.showMessageDialog(REGISTER_VIEW,
                            messageError,
                            "Invalid age",
                            JOptionPane.WARNING_MESSAGE);

                    LoggerService.systemLog(messageError);
                }
            } catch (IOException ioExcept) {
                String messageError = "Logger Service couldn't be use";

                JOptionPane.showMessageDialog(REGISTER_VIEW,
                        messageError,
                        "LoggerService Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static RegisterController getInstance() {
        synchronized (RegisterController.class) {
            if (instance == null) {
                instance = new RegisterController();
            }
        }
        return instance;
    }

    private void checkValidityOfTextBoxes(String password, String confirmPassword, String username, String email,
                                          int age, String realName)
            throws InvalidPasswordException, NullTextFieldException, AgeException, InvalidCharactersException {
        if (password.length() < 1 ||
                confirmPassword.length() < 1 ||
                username.length() < 1 ||
                email.length() < 1) {
            throw new NullTextFieldException();
        }

        boolean isPasswordInvalid = password.contains(" ") || checkIfACharisNotALetter(password.charAt(0));
        boolean isUsernameInvalid = username.contains(" ") || checkIfACharisNotALetter(username.charAt(0));
        boolean isEmailInvalid = email.contains(" ") || checkIfACharisNotALetter(email.charAt(0));
        boolean isRealNameInvalid = false;
        boolean isAgeInvalid = age < 18;
        boolean passwordMatch = password.equals(confirmPassword);

        String[] emailSplitByArond = email.split("@");

        if (emailSplitByArond.length != 2) {
            isEmailInvalid = true;
        } else {
            String[] emailSplitByPoint = emailSplitByArond[1].split("\\.");

            if (emailSplitByPoint.length > 3 || emailSplitByPoint.length < 2) {
                isEmailInvalid = true;
            } else {
                for(int i=0;i<emailSplitByPoint.length;i++){
                    if(emailSplitByPoint[i].length()<2){
                        isEmailInvalid=true;
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

        if (!passwordMatch) {
            throw new InvalidPasswordException(password, confirmPassword);
        }
        if (isAgeInvalid) {
            throw new AgeException(age);
        }

        if (isUsernameInvalid || isEmailInvalid || isPasswordInvalid || isRealNameInvalid) {
            throw new InvalidCharactersException(isUsernameInvalid, isEmailInvalid,
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

    private boolean checkIfTheUserExists(Account newAccount) {
        for (Account account : accountList) {
            if (account.getUsername().equals(newAccount.getUsername())) {
                return true;
            }
        }
        return false;
    }
}

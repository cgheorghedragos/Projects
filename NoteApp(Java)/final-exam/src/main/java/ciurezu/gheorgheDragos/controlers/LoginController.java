package ciurezu.gheorgheDragos.controlers;

import ciurezu.gheorgheDragos.graphicUI.Application;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LoginController {
    private final String ACCOUNTS_FILE_LOCATION = "src/main/resources";
    private final String ACCOUNTS_FILE_NAME = "Accounts.ser";

    private Login loginView = Login.getInstance();
    private List<Account> accountList = new ArrayList<>();

    private static volatile LoginController instance = null;

    private LoginController() {
        this.loginView.getLoginButton().addActionListener(new LoginButtonFunctionality());
        this.loginView.getRegisterButton().addActionListener(new RegisterButtonFunctionality());
        this.importAccounts();
        this.loginView.setVisible(true);
    }

    private class LoginButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsernameField().getText();
            String password = loginView.getPasswordField().getText();
            String encryptedPassword = CryptService.encryptString(password);

            Account account = checkCredentials(username,encryptedPassword);

            if(account!=null){
                loginView.setVisible(false);
                loginView.getUsernameField().setText("");
                loginView.getPasswordField().setText("");

                JOptionPane.showMessageDialog(loginView,"Successfully logged!");
                Application.getInstance().setVisible(true);
                ApplicationController.getInstance().setCurrentAccountSession(account);
                Application.getInstance().getSettingsPanel().setVisible(false);
                Application.getInstance().getNotePanel().setVisible(true);
                Application.getInstance().getNoteJList().setListData(new Vector<>());
                try {
                    LoggerService.activityLog(account.getUsername()+" connected!");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(loginView,"LoggerService Failed",
                            "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(loginView,"Invalid username/password");
            }
        }
    }

    private class RegisterButtonFunctionality implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.setVisible(false);
            Register.getInstance().setVisible(true);
        }
    }

    public static LoginController getInstance() {
        synchronized (LoginController.class) {
            if (instance == null) {
                instance = new LoginController();
            }
        }
        return instance;
    }

    private void importAccounts() {
        try {
            accountList = AccountsService.importAccounts(ACCOUNTS_FILE_LOCATION, ACCOUNTS_FILE_NAME);
        } catch (IOException e) {
            accountList = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            accountList = new ArrayList<>();
        }
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    private Account checkCredentials(String username, String password){
        for(Account account:accountList){
            if(account.getUsername().equals(username)&&account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }

    public String getACCOUNTS_FILE_LOCATION() {
        return ACCOUNTS_FILE_LOCATION;
    }

    public String getACCOUNTS_FILE_NAME() {
        return ACCOUNTS_FILE_NAME;
    }
}


package ciurezu.gheorgheDragos;


import ciurezu.gheorgheDragos.controlers.ApplicationController;
import ciurezu.gheorgheDragos.controlers.LoginController;
import ciurezu.gheorgheDragos.controlers.RegisterController;
import ciurezu.gheorgheDragos.graphicUI.Login;
import ciurezu.gheorgheDragos.services.LoggerService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            LoginController.getInstance();
            RegisterController.getInstance();
            ApplicationController.getInstance();
            Login.getInstance().setVisible(true);
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            try {
                LoggerService.criticalErrorLog("Accounts file are corrupted! Please contact us to " +
                        "verify the file");
                System.out.println("Check the critical log");
            } catch (IOException ioException) {
                System.out.println("LoggerService Failed");
            }
            System.out.println();
        } catch (RuntimeException runtimeException){
            try {
                LoggerService.criticalErrorLog(runtimeException.getMessage());
            } catch (IOException ioException) {
                System.out.println("LoggerService Failed");
            }
        }catch (Error error){
            try {
                LoggerService.criticalErrorLog(error.getMessage());
            } catch (IOException ioException) {
                System.out.println("LoggerService Failed");
            }
        }
    }
}

package ciurezu.gheorgheDragos.exceptions;

public class InvalidPasswordException extends Exception {
    private String password;
    private String confirmPassword;

    public InvalidPasswordException(String password, String confirmPassword) {
        super("Passwords doesn't match!");
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}

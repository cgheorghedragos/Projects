package ciurezu.gheorgheDragos.exceptions;

public class InvalidCharactersException extends Exception {
    private final boolean username;
    private final boolean email;
    private final boolean password;
    private final boolean realName;

    public InvalidCharactersException(boolean username, boolean email, boolean password,boolean realName) {
        super("Invalid characters inserted in the text fields!");
        this.username = username;
        this.email = email;
        this.password = password;
        this.realName = realName;
    }

    @Override
    public String getMessage() {
        String message = "The following boxes have invalid characters:";

        if(realName){
            message+= "\n-realName";
        }
        if (username) {
            message += "\n-username";
        }
        if (email) {
            message += "\n-email";
        }

        if (password) {
            message += "\n-password";
        }

        message = message + "\n\n\n" +
                "!!!Restrictions:\n" +
                "* White Spaces aren't accepted in: password,username,email\n"+
                "* First letter must be a word in : password,username,email\n"+
                "* In Real Name field you should use your real name\n"+
                "* Email should be valid";
        return message;
    }

}

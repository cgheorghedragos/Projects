package ciurezu.gheorgheDragos.exceptions;

public class NullTextFieldException extends Exception{
    public NullTextFieldException() {
        super("Text fields should have something in there!");
    }
}

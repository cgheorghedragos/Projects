package ciurezu.gheorgheDragos.exceptions;

public class AgeException extends Exception{
    public AgeException(int age){
        super("User has under 18 years old. User age: "+age+" years old.");
    }
}

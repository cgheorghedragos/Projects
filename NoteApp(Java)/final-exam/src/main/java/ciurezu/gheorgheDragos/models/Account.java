package ciurezu.gheorgheDragos.models;

import java.util.ArrayList;
import java.util.List;

public class Account  extends Person {
    private String username;
    private String password;
    private String email;
    private List<Note> noteList;

    public Account(String realName, int age, String username, String password, String email) {
        super(realName, age);
        this.username = username;
        this.password = password;
        this.email = email;
        this.noteList = new ArrayList<>();
    }

    public Account(Account account) {
        super(account.getRealName(), account.getAge());
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void addNote(Note note){
        noteList.add(note);
    }

    @Override
    public String toString() {
        return getRealName()+" with username: "+
                username+ " has the following email: "+
                email;
    }

}

package ciurezu.gheorgheDragos.models;

public class Note {
    private String text;
    private String date;

    public Note(String text, String date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.getDate().toString() + " _____ " + this.getText();
    }
}

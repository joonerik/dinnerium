package dinnerium.core;

public class Metadata {

    private String author;
    private double portion;

    public Metadata(String author, double portion) {
        this.author = author;
        this.portion = portion;
    }

    // from User class
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
    public Double getPortion() {
        return portion;
    }

}

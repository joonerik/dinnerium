package dinnerium.core;

public class Metadata {

    private String author;
    private double portion;

    public Metadata(String author, double portion) {
        this.author = author;
        this.portion = portion;
    }

    public String getAuthor() {
        return author;
    }
    public Double getPortion() {
        return portion;
    }

}

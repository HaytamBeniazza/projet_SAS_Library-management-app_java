package Model;

public class Book {
    private String title;
    private int isbn;
    private String author;
    private int quantity;


    public Book(String title, int isbn, String author, int quantity){
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.quantity = quantity;
    }


    public String getTitle(){
        return title;
    }

    public int getIsbn(){ return isbn;}

    public String getAuthor(){
        return author;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Quantity: " + quantity;
    }



}


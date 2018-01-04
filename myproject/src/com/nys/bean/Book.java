package com.nys.bean;

public class Book {
    private String bname;
    private double price;
    private String author;

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}';
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book() {

    }
}

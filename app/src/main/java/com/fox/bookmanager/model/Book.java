package com.fox.bookmanager.model;

public class Book {

    public String ID;
    public String CAT_ID;
    public String NAME;
    public String AUTHOR;
    public String PRODUCER;
    public String PRICE;
    public String QUANTITY;

    public Book(){}

    public Book(String ID, String CAT_ID, String NAME, String AUTHOR, String PRODUCER, String PRICE, String QUANTITY) {
        this.ID = ID;
        this.CAT_ID = CAT_ID;
        this.NAME = NAME;
        this.AUTHOR = AUTHOR;
        this.PRODUCER = PRODUCER;
        this.PRICE = PRICE;
        this.QUANTITY = QUANTITY;
    }

}




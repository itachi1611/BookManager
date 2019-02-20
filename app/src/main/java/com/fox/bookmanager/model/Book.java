package com.fox.bookmanager.model;

public class Book {

    public String ID;
    public String CAT_ID;
    public String NAME;
    public String AUTHOR;
    public String PRODUCER;
    public float PRICE;
    public int QUANTITY;

    public Book(){}

    public Book(String ID, String CAT_ID, String NAME, String AUTHOR, String PRODUCER, float PRICE, int QUANTITY) {
        this.ID = ID;
        this.CAT_ID = CAT_ID;
        this.NAME = NAME;
        this.AUTHOR = AUTHOR;
        this.PRODUCER = PRODUCER;
        this.PRICE = PRICE;
        this.QUANTITY = QUANTITY;
    }

}




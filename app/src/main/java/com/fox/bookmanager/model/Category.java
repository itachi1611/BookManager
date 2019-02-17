package com.fox.bookmanager.model;

public class Category {

    public String ID;
    public String NAME;
    public String DESCRIPTION;
    public String POSITION;

    public Category(){}

    public Category(String ID, String NAME, String DESCRIPTION, String POSITION) {
        this.ID = ID;
        this.NAME = NAME;
        this.DESCRIPTION = DESCRIPTION;
        this.POSITION = POSITION;
    }
}

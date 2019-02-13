package com.fox.bookmanager;

public class Constants {

    public static final boolean isDEBUG = true;

    //User Table
    public static final String USER_TABLE = "user";
    //Column
    public static final String USER_USER_NAME = "userName";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_PHONE = "Phone";
    public static final String USER_FULL_NAME = "fullName";
    //Query create user(userName (nvarchar(50),Password nvarchar(50), Phone nchar(10), fullName nvarchar(50))
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + "(" +
            "" + USER_USER_NAME + " NVARCHAR(50)," +
            "" + USER_PASSWORD + " NVARCHAR(50)," +
            "" + USER_PHONE + " NCHAR(10)," +
            "" + USER_FULL_NAME + " NVARCHAR(50)" +
            ")";


    //Invoice Talbe
    public static final String INVOICE_TABLE = "invoice";
    //Column
    public static final String INVOICE_ID = "i_id";
    public static final String INVOICE_DATE = "i_date";
    //Query create invoice(i_id nchar(7),i_date date)
    public static final String CREATE_INVOICE_TABLE = "CREATE TABLE " + INVOICE_TABLE + "(" +
            "" + INVOICE_ID + " NCHAR(7) PRIMARY KEY," +
            "" + INVOICE_DATE + " DATE)";

    //Book Table
    public static final String BOOK_TABLE = "book";
    //Column
    public static final String BOOK_ID = "b_id";
    public static final String BOOK_TYPE_ID = "cat_id";
    public static final String BOOK_NAME = "b_name";
    public static final String BOOK_AUTHOR = "author";
    public static final String BOOK_PRODUCER = "producer";
    public static final String BOOK_PRICE = "price";
    public static final String BOOK_QUANTITY = "quantity";

    //Query create book(b_id nchar(5) primary key not null,cat_id nchar(50),b_name nvarchar(50),author nvarchar(50),producer nvarchar(50),price float,quantity int)

    public static final String CREATE_BOOK_TABLE = "CREATE TABLE " + BOOK_TABLE + "(" +
            "" + BOOK_ID + " NCHAR(5)," +
            "" + BOOK_TYPE_ID + " NCHAR(50)," +
            "" + BOOK_NAME + " NVARCHAR(50)," +
            "" + BOOK_AUTHOR + " NVARCHAR(50)," +
            "" + BOOK_PRODUCER + " NVARCHAR(50)," +
            "" + BOOK_PRICE + " FLOAT," +
            "" + BOOK_QUANTITY + " INT" +
            ")";

}

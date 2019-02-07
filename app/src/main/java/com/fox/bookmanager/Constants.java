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

}

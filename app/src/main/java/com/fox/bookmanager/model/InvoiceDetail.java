package com.fox.bookmanager.model;

public class InvoiceDetail {

    public String ID;
    public String BOOK_ID;
    public String INVOICE_ID;
    public int QUANTITY;

    public InvoiceDetail() {
    }

    public InvoiceDetail(String ID, String BOOK_ID, String INVOICE_ID, int QUANTITY) {
        this.ID = ID;
        this.BOOK_ID = BOOK_ID;
        this.INVOICE_ID = INVOICE_ID;
        this.QUANTITY = QUANTITY;
    }

}

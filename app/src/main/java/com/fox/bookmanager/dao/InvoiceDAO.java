package com.fox.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Invoice;

import java.util.ArrayList;
import java.util.List;

import static com.fox.bookmanager.Constants.INVOICE_DATE;
import static com.fox.bookmanager.Constants.INVOICE_ID;
import static com.fox.bookmanager.Constants.INVOICE_TABLE;

public class InvoiceDAO {

    private DBHelper dbHelper;

    public InvoiceDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public long insertInvoice(Invoice invoice){

        ContentValues cv = new ContentValues();
        cv.put(INVOICE_ID,invoice.ID);
        cv.put(INVOICE_DATE,invoice.DATE);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long result = sqLiteDatabase.insert(INVOICE_TABLE,null,cv);

        sqLiteDatabase.close();
        return result;
    }

    public long updateInvoice(Invoice invoice){
        ContentValues cv = new ContentValues();
        cv.put(INVOICE_ID,invoice.ID);
        cv.put(INVOICE_DATE,invoice.DATE);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long result = sqLiteDatabase.update(INVOICE_TABLE,cv,INVOICE_ID + " = ?",new String[]{invoice.ID});

        sqLiteDatabase.close();
        return result;
    }

    public long deleteInvoice(String id){
        long result = -1;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(INVOICE_TABLE,INVOICE_ID + "=?",new String[]{id});
        sqLiteDatabase.close();

        return result;
    }

    public List<Invoice> getAllInvoice(){

        List<Invoice> invoices = new ArrayList<Invoice>();

        String QUERY = "SELECT * FROM " + INVOICE_TABLE;
        //Ask permission to read and write
        SQLiteDatabase  sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){

            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){

                    String INVOICE_ID_ = cursor.getString(cursor.getColumnIndex(INVOICE_ID));

                    String INVOICE_DATE_ = cursor.getString(cursor.getColumnIndex(INVOICE_DATE));

                    Invoice invoice = new Invoice(INVOICE_ID_,INVOICE_DATE_);

                    //Add user to array Users
                    invoices.add(invoice);
                    cursor.moveToNext();

                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }

        return invoices;

    }

}

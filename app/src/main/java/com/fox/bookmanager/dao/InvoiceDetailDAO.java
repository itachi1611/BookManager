package com.fox.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fox.bookmanager.Constants;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.InvoiceDetail;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailDAO extends Constants {

    private DBHelper dbHelper;

    public InvoiceDetailDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public InvoiceDetailDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insertInvoiceDetail(InvoiceDetail invoiceDetail){

        ContentValues cv = new ContentValues();
        cv.put(INVOICE_DETAIL_ID,invoiceDetail.ID);
        cv.put(INVOICE_DETAIL_BOOK_ID,invoiceDetail.BOOK_ID);
        cv.put(INVOICE_DETAIL_INVOICE_ID,invoiceDetail.INVOICE_ID);
        cv.put(INVOICE_DETAIL_QUANTITY,invoiceDetail.QUANTITY);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long result = sqLiteDatabase.insert(INVOICE_DETAIL_TABLE,null,cv);

        sqLiteDatabase.close();
        return result;
    }

    public long updateInvoiceDetail(InvoiceDetail invoiceDetail){
        ContentValues cv = new ContentValues();
        cv.put(INVOICE_DETAIL_ID,invoiceDetail.ID);
        cv.put(INVOICE_DETAIL_BOOK_ID,invoiceDetail.BOOK_ID);
        cv.put(INVOICE_DETAIL_INVOICE_ID,invoiceDetail.INVOICE_ID);
        cv.put(INVOICE_DETAIL_QUANTITY,invoiceDetail.QUANTITY);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long result = sqLiteDatabase.update(INVOICE_DETAIL_TABLE,cv,INVOICE_ID + " = ?",new String[]{invoiceDetail.ID});

        sqLiteDatabase.close();
        return result;
    }

    public long deleteInvoiceDetail(String id){
        long result = -1;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(INVOICE_DETAIL_TABLE,INVOICE_DETAIL_ID + "=?",new String[]{id});
        sqLiteDatabase.close();

        return result;
    }

    public List<InvoiceDetail> getAllInvoiceDetailByInvoiceID(String id){

        List<InvoiceDetail> invoiceDetails = new ArrayList<>();

        String QUERY = "SELECT * FROM " + INVOICE_DETAIL_TABLE + " WHERE " + INVOICE_DETAIL_INVOICE_ID + " = '" + id + "'";
        //Ask permission to read and write
        SQLiteDatabase  sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){

            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){

                    String INVOICE_DETAIL_ID_ = cursor.getString(cursor.getColumnIndex(INVOICE_DETAIL_ID));

                    String INVOICE_DETAIL_BOOK_ID_ = cursor.getString(cursor.getColumnIndex(INVOICE_DETAIL_BOOK_ID));

                    String INVOICE_DETAIL_INVOICE_ID_ = cursor.getString(cursor.getColumnIndex(INVOICE_DETAIL_INVOICE_ID));

                    int INVOICE_DETAIL_QUANTITY_ = cursor.getInt(cursor.getColumnIndex(INVOICE_DETAIL_QUANTITY));

                    InvoiceDetail invoiceDetail = new InvoiceDetail(INVOICE_DETAIL_ID_,INVOICE_DETAIL_BOOK_ID_,INVOICE_DETAIL_INVOICE_ID_,INVOICE_DETAIL_QUANTITY_);

                    //Add user to array Users
                    invoiceDetails.add(invoiceDetail);
                    cursor.moveToNext();

                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }

        return invoiceDetails;

    }
}

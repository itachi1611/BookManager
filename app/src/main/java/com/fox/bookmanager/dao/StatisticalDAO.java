package com.fox.bookmanager.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fox.bookmanager.Constants;
import com.fox.bookmanager.database.DBHelper;


public class StatisticalDAO extends Constants {

    private DBHelper dbHelper;

    public StatisticalDAO(Context context){
        this.dbHelper = new DBHelper(context);
    }

    public void testSum(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        String QUERY = "SELECT SUM(TOTAL) FROM (SELECT SUM(book.price * invoice_detail.quantity) AS 'TOTAL'" +
                "" + " FROM " + INVOICE_TABLE +
                "" + " INNER JOIN " + INVOICE_DETAIL_TABLE + " ON " + " invoice.i_id = invoice_detail.invoice_id " +
                "" + " INNER JOIN " + BOOK_TABLE + " ON " + " book.b_id = invoice_detail.book_id " +
                "" + " WHERE strftime(\"%Y-%m-%d\", invoice.i_date / 1000, 'unixepoch') = strftime(\"%Y-%m-%d\", 'now') " +
                "" + " GROUP BY invoice_detail.book_id " +
                ")";

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                double sum = cursor.getDouble(0);
                Log.e("TOTAL",sum + " " + cursor.getCount());
            }
        }
    }

    public void testDateNow(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String QUERY = "SELECT * FROM " + INVOICE_TABLE + " WHERE strftime(\"%Y-%m-%d\",i_date / 1000,'unixepoch') = strftime(\"%Y-%m-%d\",'now')";
        String QUERY_2 = "SELECT strftime(\"%Y-%m-%d\",i_date / 1000,'unixepoch') FROM " + INVOICE_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                String date = cursor.getString(0);
                Log.e("DATE",date);
            }
        }
    }

    // example day = 2018-10-09
    public double getStatisticalByDay(){
        double result = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String QUERY = "SELECT SUM(TOTAL) FROM (SELECT SUM(book.price * invoice_detail.quantity) AS 'TOTAL' " +
                "" + "FROM invoice INNER JOIN invoice_detail ON invoice.i_id = invoice_detail.invoice_id " +
                "" + "INNER JOIN book ON invoice_detail.book_id = book.b_id WHERE invoice.i_date = date('now') GROUP BY invoice_detail.book_id)tmp";
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    result = cursor.getDouble(0);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        return result;
    }

    // format month : %Y-%m  2018-10
    public double getStatisticalByMonth(){
        double result = 0;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String QUERY = "SELECT SUM(TOTAL) FROM (SELECT SUM(book.price * invoice_detail.quantity) AS 'TOTAL' " +
                "" + "FROM invoice INNER JOIN invoice_detail ON invoice.i_id = invoice_detail.invoice_id " +
                "" + "INNER JOIN book ON invoice_detail.book_id = book.b_id WHERE strftime('%m',invoice.i_date) = strftime('%m','now') GROUP BY invoice_detail.book_id)tmp";
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    result = cursor.getDouble(0);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        return result;
    }

    // example year = "2018"
    public double getStatisticalByYear(){
        double result = 0;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String QUERY = "SELECT SUM(TOTAL) FROM (SELECT SUM(book.price * invoice_detail.quantity) AS 'TOTAL' " +
                "" + "FROM invoice INNER JOIN invoice_detail ON invoice.i_id = invoice_detail.invoice_id " +
                "" + "INNER JOIN book ON invoice_detail.book_id = book.b_id WHERE strftime('%Y',invoice.i_date) = strftime('%Y','now') GROUP BY invoice_detail.book_id)tmp";
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    result = cursor.getDouble(0);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        return result;
    }

    // YY-MM-DD
    public double getStatisticalByDate(String dateFormat){
        double result = 0;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String QUERY = "SELECT SUM(TOTAL) FROM (SELECT SUM(book.price * invoice_detail.quantity) AS 'TOTAL' " +
                "" + "FROM invoice INNER JOIN invoice_detail ON invoice.i_id = invoice_detail.invoice_id " +
                "" + "INNER JOIN book ON invoice_detail.book_id = book.b_id WHERE strftime(" + dateFormat + ",invoice.i_date / 1000,'unixepoch') " +
                "" + "= strftime(" + dateFormat + ",'now') " +
                "" + "GROUP BY invoice_detail.book_id";
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while(!cursor.isAfterLast()){
                    result = cursor.getDouble(0);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        return result;
    }


}

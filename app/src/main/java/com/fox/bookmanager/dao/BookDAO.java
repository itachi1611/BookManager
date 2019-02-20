package com.fox.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fox.bookmanager.Constants;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends Constants {

    private DBHelper dbHelper;

    public BookDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public BookDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public List<Book> getAllBook(){
        String QUERY = "SELECT * FROM " + BOOK_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        List<Book> books = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){

                    String ID_ = cursor.getString(cursor.getColumnIndex(BOOK_ID));
                    String CAT_ID_ = cursor.getString(cursor.getColumnIndex(BOOK_TYPE_ID));
                    String NAME_ = cursor.getString(cursor.getColumnIndex(BOOK_NAME));
                    String AUTHOR_ = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR));
                    String PRODUCER_ = cursor.getString(cursor.getColumnIndex(BOOK_PRODUCER));
                    float PRICE_ = cursor.getFloat(cursor.getColumnIndex(BOOK_PRICE));
                    int QUANTITY_ = cursor.getInt(cursor.getColumnIndex(BOOK_QUANTITY));

                    Book book = new Book(ID_,CAT_ID_,NAME_,AUTHOR_,PRODUCER_,PRICE_,QUANTITY_);
                    books.add(book);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return books;
    }

    public long insertBook(Book book){
        long result = -1;

        ContentValues cv = new ContentValues();
        cv.put(BOOK_ID,book.ID);
        cv.put(BOOK_TYPE_ID,book.CAT_ID);
        cv.put(BOOK_NAME,book.NAME);
        cv.put(BOOK_AUTHOR,book.AUTHOR);
        cv.put(BOOK_PRODUCER,book.PRODUCER);
        cv.put(BOOK_PRICE,book.PRICE);
        cv.put(BOOK_QUANTITY,book.QUANTITY);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.insert(BOOK_TABLE,null,cv);

        sqLiteDatabase.close();
        return result;
    }

    public long updateBook(Book book){
        long result = -1;

        ContentValues cv = new ContentValues();
        cv.put(BOOK_ID,book.ID);
        cv.put(BOOK_TYPE_ID,book.CAT_ID);
        cv.put(BOOK_NAME,book.NAME);
        cv.put(BOOK_AUTHOR,book.AUTHOR);
        cv.put(BOOK_PRODUCER,book.PRODUCER);
        cv.put(BOOK_PRICE,book.PRICE);
        cv.put(BOOK_QUANTITY,book.QUANTITY);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.update(BOOK_TABLE,cv,BOOK_ID + " = ?",new String[]{book.ID});

        sqLiteDatabase.close();
        return result;
    }

    public long deleteBook(String id){
        long result = -1;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(BOOK_TABLE,BOOK_ID + "=?",new String[]{id});
        sqLiteDatabase.close();

        return result;
    }

    public Book getBookByID(String id){
        Book book = null;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(BOOK_TABLE,new String[]{BOOK_ID,BOOK_TYPE_ID,BOOK_AUTHOR,BOOK_NAME,BOOK_PRICE,BOOK_PRODUCER,BOOK_QUANTITY},BOOK_ID + " =? ",new String[]{id},null,null,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();

                String id_ = cursor.getString(cursor.getColumnIndex(BOOK_ID));
                String type_id_ = cursor.getString(cursor.getColumnIndex(BOOK_TYPE_ID));
                String author_ = cursor.getString(cursor.getColumnIndex(BOOK_AUTHOR));
                String producer_ = cursor.getString(cursor.getColumnIndex(BOOK_PRODUCER));
                String name_ = cursor.getString(cursor.getColumnIndex(BOOK_NAME));
                float price_ = cursor.getFloat(cursor.getColumnIndex(BOOK_PRICE));
                int quantity_ = cursor.getInt(cursor.getColumnIndex(BOOK_QUANTITY));

                book = new Book(id_,type_id_,name_,author_,producer_,price_,quantity_);

            }
        }

        return book;
    }

}

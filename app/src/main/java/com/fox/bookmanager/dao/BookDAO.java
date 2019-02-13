package com.fox.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Book;

import java.util.ArrayList;
import java.util.List;

import static com.fox.bookmanager.Constants.BOOK_AUTHOR;
import static com.fox.bookmanager.Constants.BOOK_ID;
import static com.fox.bookmanager.Constants.BOOK_NAME;
import static com.fox.bookmanager.Constants.BOOK_PRICE;
import static com.fox.bookmanager.Constants.BOOK_PRODUCER;
import static com.fox.bookmanager.Constants.BOOK_QUANTITY;
import static com.fox.bookmanager.Constants.BOOK_TABLE;
import static com.fox.bookmanager.Constants.BOOK_TYPE_ID;

public class BookDAO {

    private DBHelper dbHelper;

    public BookDAO(Context context) {
        this.dbHelper = new DBHelper(context);
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
                    String PRICE_ = cursor.getString(cursor.getColumnIndex(BOOK_PRICE));
                    String QUANTITY_ = cursor.getString(cursor.getColumnIndex(BOOK_QUANTITY));

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
        ContentValues cv = new ContentValues();
        cv.put(BOOK_ID,book.ID);
        cv.put(BOOK_TYPE_ID,book.CAT_ID);
        cv.put(BOOK_NAME,book.NAME);
        cv.put(BOOK_AUTHOR,book.AUTHOR);
        cv.put(BOOK_PRODUCER,book.PRODUCER);
        cv.put(BOOK_PRICE,book.PRICE);
        cv.put(BOOK_QUANTITY,book.QUANTITY);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long result = sqLiteDatabase.insert(BOOK_TABLE,null,cv);

        sqLiteDatabase.close();
        return result;
    }

    public long updateBook(Book book){
        ContentValues cv = new ContentValues();
        cv.put(BOOK_ID,book.ID);
        cv.put(BOOK_TYPE_ID,book.CAT_ID);
        cv.put(BOOK_NAME,book.NAME);
        cv.put(BOOK_AUTHOR,book.AUTHOR);
        cv.put(BOOK_PRODUCER,book.PRODUCER);
        cv.put(BOOK_PRICE,book.PRICE);
        cv.put(BOOK_QUANTITY,book.QUANTITY);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long result = sqLiteDatabase.update(BOOK_TABLE,cv,BOOK_ID + " = ?",new String[]{book.ID});

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

}

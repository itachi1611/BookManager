package com.fox.bookmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fox.bookmanager.Constants;

import static com.fox.bookmanager.Constants.CREATE_INVOICE_TABLE;
import static com.fox.bookmanager.Constants.CREATE_USER_TABLE;
import static com.fox.bookmanager.Constants.USER_TABLE;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "book_manager.sql", null, 1);
        if(Constants.isDEBUG) Log.i("CREATE_USER_TABLE",CREATE_USER_TABLE);
        if(Constants.isDEBUG) Log.i("CREATE_INVOICE_TABLE",CREATE_INVOICE_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_INVOICE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
    }
}

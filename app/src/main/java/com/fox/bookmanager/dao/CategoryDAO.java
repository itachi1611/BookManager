package com.fox.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fox.bookmanager.Constants;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryDAO extends Constants {

    private DBHelper dbHelper;

    public CategoryDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public List<Category> getAllCategory(){
        String QUERY = "SELECT * FROM " + CATEGORY_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        List<Category> categories = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){

                    String ID_ = cursor.getString(cursor.getColumnIndex(CAT_ID));
                    String NAME_ = cursor.getString(cursor.getColumnIndex(CAT_NAME));
                    String DESCRIPTION_ = cursor.getString(cursor.getColumnIndex(CAT_DESCRIPTION));
                    String POSITION_ = cursor.getString(cursor.getColumnIndex(CAT_POSITION));

                    Category category = new Category(ID_,NAME_,DESCRIPTION_,POSITION_);
                    categories.add(category);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return categories;
    }

    public long insertCategory(Category category){
        long result = -1;
        ContentValues cv = new ContentValues();
        cv.put(CAT_ID,category.ID);
        cv.put(CAT_NAME,category.NAME);
        cv.put(CAT_DESCRIPTION,category.DESCRIPTION);
        cv.put(CAT_POSITION,category.POSITION);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.insert(CATEGORY_TABLE,null,cv);

        sqLiteDatabase.close();
        return result;
    }

    public long updateCategory(Category category){
        long result = -1;
        ContentValues cv = new ContentValues();
        cv.put(CAT_ID,category.ID);
        cv.put(CAT_NAME,category.NAME);
        cv.put(CAT_DESCRIPTION,category.DESCRIPTION);
        cv.put(CAT_POSITION,category.POSITION);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.update(CATEGORY_TABLE,cv,CAT_ID + " = ?",new String[]{category.ID});

        sqLiteDatabase.close();
        return result;
    }

    public long deleteCategory(String id){
        long result = -1;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(CATEGORY_TABLE,CAT_ID + "=?",new String[]{id});
        sqLiteDatabase.close();

        return result;
    }
}

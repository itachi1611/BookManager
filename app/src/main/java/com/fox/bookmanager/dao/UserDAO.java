package com.fox.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fox.bookmanager.Constants;
import com.fox.bookmanager.database.DBHelper;
import com.fox.bookmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Constants {

    private DBHelper dbHelper;

    public UserDAO(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public UserDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public long insertUser(User user){

        long result = -1;

        ContentValues cv = new ContentValues();
        cv.put(USER_USER_NAME,user.USER_USER_NAME);
        cv.put(USER_PASSWORD,user.USER_PASSWORD);
        cv.put(USER_PHONE,user.USER_PHONE);
        cv.put(USER_FULL_NAME,user.USER_FULL_NAME);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.insert(USER_TABLE,null,cv);
        sqLiteDatabase.close();

        return result;

    }

    public long updateUser(User user){

        long result = -1;
        ContentValues cv = new ContentValues();
        cv.put(USER_USER_NAME, user.USER_USER_NAME);
        cv.put(USER_PASSWORD, user.USER_PASSWORD);
        cv.put(USER_PHONE, user.USER_PHONE);
        cv.put(USER_FULL_NAME, user.USER_FULL_NAME);

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.update(USER_TABLE, cv, USER_USER_NAME + "= ?", new String[]{user.USER_USER_NAME});
        sqLiteDatabase.close();

        return result;

    }

    public long deleteUser(String user_name){

        long result = -1;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(USER_TABLE,USER_USER_NAME + " = ?",new String[]{user_name});
        sqLiteDatabase.close();

        return result;

    }

    public List<User> getAllUser(){

        List<User> users = new ArrayList<>();

        String QUERY = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY,null);

        if(cursor != null){

            if(cursor.getCount() > 0){

                cursor.moveToFirst();
                while(!cursor.isAfterLast()){

                    String USER_USER_NAME_ = cursor.getString(cursor.getColumnIndex(USER_USER_NAME));
                    String USER_PASSWORD_ = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
                    String USER_PHONE_ = cursor.getString(cursor.getColumnIndex(USER_PHONE));
                    String USER_FULL_NAME_ = cursor.getString(cursor.getColumnIndex(USER_FULL_NAME));

                    User user = new User(USER_USER_NAME_,USER_PASSWORD_,USER_PHONE_,USER_FULL_NAME_);

                    users.add(user);
                    cursor.moveToNext();
                }
                cursor.close();
                sqLiteDatabase.close();
            }
        }
        return users;
    }

    public User getUser(String username) {

        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(USER_TABLE, new String[]{USER_USER_NAME, USER_PASSWORD, USER_PHONE, USER_FULL_NAME}, USER_USER_NAME + " = ? ", new String[]{username}, null, null, null, null);

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            String user_name = cursor.getString(cursor.getColumnIndex(USER_USER_NAME));

            String password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));

            String name = cursor.getString(cursor.getColumnIndex(USER_PHONE));

            String phoneNumber = cursor.getString(cursor.getColumnIndex(USER_FULL_NAME));

            // khoi tao user voi cac gia tri lay duoc
            user = new User(user_name, password, name, phoneNumber);
        }
        cursor.close();
        return user;
    }

    public int getPosition(String username){
        int pos = 0;
        for(int i = 0;i < new UserDAO(dbHelper).getAllUser().size();i++){
            if(new UserDAO(dbHelper).getAllUser().get(i).USER_USER_NAME.equals(username)){
                pos = i;
            }
        }
        return pos;
    }

}

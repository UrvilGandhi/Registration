package com.example.simpledatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

class DBManager {

    private Context context;

    private SQLiteDatabase database;

    DBManager(Context c) {
        context = c;
    }

    void open() throws SQLException {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

//    public void close() {
//        dbHelper.close();
//    }

    void insert(String name, String mobile, String age, String email) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.MOBILE, mobile);
        contentValue.put(DatabaseHelper.AGE, age);
        contentValue.put(DatabaseHelper.EMAIL, email);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.MOBILE, DatabaseHelper.AGE, DatabaseHelper.EMAIL};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    void update(long _id, String name, String mobile, String age, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.MOBILE, mobile);
        contentValues.put(DatabaseHelper.AGE, age);
        contentValues.put(DatabaseHelper.EMAIL, email);
        database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
    }

    void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}


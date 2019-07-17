package com.example.projecttest;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "database";
    static final String DB_TABLE = "dbTable";
    static final String COL_ID = "id";
    static final String COL_NAME = "name";
    static final String COL_PHONE = "phone";
    static final String COL_GENDER = "gender";
    static final String COL_AREA = "area";
    static final String COL_WORK = "work";
    static final String COL_IMAGE = "image";

    // Table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT," +
            COL_PHONE + " TEXT," +
            COL_GENDER + " TEXT," +
            COL_AREA + " TEXT," +
            COL_WORK + " TEXT," +
            COL_IMAGE + " BLOB);";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating table
        db.execSQL(CREATE_TABLE_IMAGE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        String droptableSQL = "DROP TABLE IF EXISTS " + DB_TABLE;
        db.execSQL(droptableSQL);
        // create new table
        onCreate(db);
    }

    public Cursor getBygenders(String gender, String area) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM dbTable WHERE gender='" + gender + "' AND area ='" + area + "'";
        return sqLiteDatabase.rawQuery(query, null);
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + DB_TABLE;
        return sqLiteDatabase.rawQuery(query, null);
    }

}

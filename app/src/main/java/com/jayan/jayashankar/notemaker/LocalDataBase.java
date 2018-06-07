package com.jayan.jayashankar.notemaker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class LocalDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notes_maker";
    private static final String TABLE_DIRECTORIES = "directories";
    private static  String CREATE_TABLE_NUMBERS = null;
    private static final String COLUMN_NUMBER_ID = "id";
    private static final String COLUMN_NUMBER = "number";
    private static final int DATABASE_VERSION = 1;
    private boolean inserted = false;
    private Context context;

    public LocalDataBase(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_TABLE_NUMBERS = "CREATE TABLE "+ TABLE_DIRECTORIES +" (" +
                " `"+COLUMN_NUMBER_ID+"` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " `"+COLUMN_NUMBER+"` TEXT NOT NULL" +
                ")";

        db.execSQL(CREATE_TABLE_NUMBERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        CREATE_TABLE_NUMBERS = "CREATE TABLE "+ TABLE_DIRECTORIES +" (" +
                " `"+COLUMN_NUMBER_ID+"` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " `"+COLUMN_NUMBER+"` TEXT NOT NULL" +
                ")";

        db.execSQL(CREATE_TABLE_NUMBERS);
    }

    public boolean insertNumber(String number){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NUMBER,number);

        inserted = sqLiteDatabase.insert(TABLE_DIRECTORIES, null, values) != -1;

        sqLiteDatabase.close(); // Closing database connection
        return inserted;
    }

    public List getBlockedNumbers() {

        SQLiteDatabase  sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_DIRECTORIES, new String[] { COLUMN_NUMBER}, null,
                null, null, null, null, null);
        List<String> list = new ArrayList<>();

        while (cursor.moveToNext())
        {
            list.add(cursor.getString(0));
        }

        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    public boolean deleteTitle(String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DIRECTORIES, COLUMN_NUMBER + " = ?",
                new String[]{number}) > 0;
    }
}

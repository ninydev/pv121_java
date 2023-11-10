package com.itstep.restapi.providers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FriendsContractDatabase extends SQLiteOpenHelper  {
    public static final String DATABASE_NAME = "friends.db";
    public static final int DATABASE_VERSION = 1;

    private static FriendsContractDatabase instance = null;

    private FriendsContractDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static FriendsContractDatabase getInstance(Context context){
        if(instance == null){
            instance = new FriendsContractDatabase(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + FriendsContract.TABLE_NAME + "(" +
                FriendsContract.Columns._ID + " INTEGER PRIMARY KEY NOT NULL, " +
                FriendsContract.Columns.NAME + " TEXT NOT NULL, " +
                FriendsContract.Columns.EMAIL + " TEXT, " +
                FriendsContract.Columns.PHONE + " TEXT NOT NULL)";
        db.execSQL(sql);

        // добавление начальных данных
        db.execSQL("INSERT INTO "+ FriendsContract.TABLE_NAME +" (" + FriendsContract.Columns.NAME
                + ", " + FriendsContract.Columns.PHONE  + ") VALUES ('Tom', '+12345678990');");
        db.execSQL("INSERT INTO "+ FriendsContract.TABLE_NAME +" (" + FriendsContract.Columns.NAME
                + ", " + FriendsContract.Columns.EMAIL  + ", " + FriendsContract.Columns.PHONE +
                " ) VALUES ('Bob', 'bob@gmail.com', '+13456789102');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

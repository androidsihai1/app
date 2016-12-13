package com.example.administrator.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pengsihai@yy.com on 2016/12/12.
 */

public class HaiSqliteOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "test.db";
    public static final int DB_VERSION = 1;

    HaiSqliteOpenHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
    }


    public HaiSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbManager.getInstance().mTestCache.createTableSql());
        db.execSQL(DbManager.getInstance().mDbCache.createTableSql());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + DbManager.getInstance().mTestCache.getTableName() + " IF EXIST");
        db.execSQL("DROP TABLE " + DbManager.getInstance().mDbCache.getTableName() + " IF EXIST");

    }

}

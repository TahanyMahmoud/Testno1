package com.tahany.android.test1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Toto on 8/4/2018.
 */
public class UsersDbHelper  extends SQLiteOpenHelper {

    public static final String LOG_TAG = UsersDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "usersinfo.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAMES="UsersEntries";
    public static final String _ID= BaseColumns._ID;
    public static final String COLUMN_Email="Email";
    public static final String COLUMN_password="Password";
    public static final String COLUMN_mobile="Mobile";

    /**
     * Constructs a new instance of {@link UsersDbHelper}.
     *
     * @param context of the app
     */
    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + TABLE_NAMES + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_Email + " TEXT NOT NULL, "
                + COLUMN_password + " TEXT NOT NULL, "
                + COLUMN_mobile + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}

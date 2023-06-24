package khanhnqph30151.fptpoly.mobile_level1_khanhnqph30151.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "data";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    public static final String TABLE_NOTE_CREATE = "CREATE TABLE " +
            "tbl (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT NOT NULL," +
            "hang TEXT NOT NULL," +
            "year INTEGER NOT NULL," +
            "price INTEGER NOT NULL" +
            ")";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NOTE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl");
    }
}

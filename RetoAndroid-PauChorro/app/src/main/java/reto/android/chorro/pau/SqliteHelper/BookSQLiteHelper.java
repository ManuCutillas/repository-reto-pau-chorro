package reto.android.chorro.pau.SqliteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pauchorroyanguas on 21/12/15.
 */
public class BookSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Book (id INTEGER PRIMARY KEY," +
            " title TEXT, author TEXT, cover TEXT, path TEXT)";

    public BookSQLiteHelper(Context context) {
        super(context, "books", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

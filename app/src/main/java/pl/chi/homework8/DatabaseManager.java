package pl.chi.homework8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "notesdb";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "notes";
    private static final String ID = "_id";
    private static final String NAME = "name";

    private SQLiteDatabase myDB;

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT NOT NULL " +
                ")";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void closeDB () {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    long addNote(String name) {
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        myDB = getWritableDatabase();
        return myDB.insert(TABLE_NAME, null, values);
    }

    Cursor getAllNotes() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean deleteNote(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }
}

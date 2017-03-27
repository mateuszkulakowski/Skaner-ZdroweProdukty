package mateusz.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mateusz on 15.12.2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "zdroweprodukty.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PRODUKTY = "produkty";
    public static final String COLUMN_PRODUKTY_ID = "_id";
    public static final String COLUMN_PRODUKTY_NAZWA = "nazwa";
    public static final String COLUMN_PRODUKTY_PRODUCENT_ID = "producent_id";
    public static final String COLUMN_PRODUKTY_SKLADNIKI = "skladniki";

    public static final String TABLE_PRODUCENT = "producenci";
    public static final String COLUMN_PRODUCENT_ID = "_id";
    public static final String COLUMN_PRODUCENT_NAZWA = "nazwa";
    public static final String COLUMN_PRODUCENT_ADRES = "adres";

    public static final String TABLE_SKLADNIKIWPLYW = "skladnikiwplyw";
    public static final String COLUMN_SKLADNIKIWPLYW_ID = "_id";
    public static final String COLUMN_SKLADNIKIWPLYW_RODZAJ = "rodzaj";
    public static final String COLUMN_SKLADNIKIWPLYW_NAZWA = "nazwa";
    public static final String COLUMN_SKLADNIKIWPLYW_OPIS = "opis";

    private static final String SQL_CREATE_TABLE_PRODUCT = "create table "
            + TABLE_PRODUKTY
            + "( " + COLUMN_PRODUKTY_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_PRODUKTY_NAZWA + " TEXT NOT NULL,"
            + COLUMN_PRODUKTY_SKLADNIKI + " TEXT NOT NULL,"
            + COLUMN_PRODUKTY_PRODUCENT_ID + " INTEGER"
            +");";

    private static final String SQL_CREATE_TABLE_PRODUCENT = "create table "
            + TABLE_PRODUCENT
            + "( " + COLUMN_PRODUCENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PRODUCENT_NAZWA + " TEXT NOT NULL,"
            + COLUMN_PRODUCENT_ADRES + " TEXT);";

    private static final String SQL_CREATE_TABLE_SKLADNIKIWPLYW = "create table "
            + TABLE_SKLADNIKIWPLYW
            + "( " + COLUMN_SKLADNIKIWPLYW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SKLADNIKIWPLYW_NAZWA + " TEXT NOT NULL,"
            + COLUMN_SKLADNIKIWPLYW_RODZAJ + " TEXT NOT NULL,"
            + COLUMN_SKLADNIKIWPLYW_OPIS + " TEXT NOT NULL"
            +");";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PRODUCT);
        db.execSQL(SQL_CREATE_TABLE_PRODUCENT);
        db.execSQL(SQL_CREATE_TABLE_SKLADNIKIWPLYW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUKTY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKLADNIKIWPLYW);
        onCreate(db);
    }
}

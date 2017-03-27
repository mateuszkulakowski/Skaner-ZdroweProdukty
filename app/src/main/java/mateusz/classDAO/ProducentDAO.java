package mateusz.classDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mateusz.database.DatabaseHelper;
import mateusz.klasy.Producent;

/**
 * Created by Mateusz on 16.12.2016.
 */

public class ProducentDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private String[] allColumns =
            {
                    DatabaseHelper.COLUMN_PRODUCENT_ID,
                    DatabaseHelper.COLUMN_PRODUCENT_NAZWA,
                    DatabaseHelper.COLUMN_PRODUCENT_ADRES
            };

    public ProducentDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public Long addProducent(Producent producent) {

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUCENT_NAZWA, producent.getNazwa());
        values.put(DatabaseHelper.COLUMN_PRODUCENT_ADRES, producent.getAdres());

        Long id = database.insert(DatabaseHelper.TABLE_PRODUCENT, null, values);
        dbHelper.close();

        return id;
    }


    public void deleteProducent(Producent producent)
    {
        database = dbHelper.getWritableDatabase();
        database.delete(DatabaseHelper.TABLE_PRODUCENT,
                DatabaseHelper.COLUMN_PRODUCENT_ID + " = ?",
                new String[] { String.valueOf(producent.get_id()) });


        database.close();
    }

    public int updateProducent(Producent producent) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUCENT_NAZWA, producent.getNazwa());
        values.put(DatabaseHelper.COLUMN_PRODUCENT_ADRES, producent.getAdres());

        // updating row
        return database.update(DatabaseHelper.TABLE_PRODUCENT,
                values,
                DatabaseHelper.COLUMN_PRODUCENT_ID + " = ?",
                new String[] { String.valueOf(producent.get_id()) });
    }

    public Producent getProducent(int id) {
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUCENT,
                new String[] { DatabaseHelper.COLUMN_PRODUCENT_ID, DatabaseHelper.COLUMN_PRODUCENT_NAZWA, DatabaseHelper.COLUMN_PRODUCENT_ADRES},
                DatabaseHelper.COLUMN_PRODUCENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        Producent producent = null;

        if (cursor != null) {
            cursor.moveToFirst();
            producent = new Producent(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        }
            // return producent
        return producent;
    }


    public List<Producent> getAllProducents() {
        List<Producent> producentList = new ArrayList<Producent>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_PRODUCENT;

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Producent producent = new Producent();
                producent.set_id(Integer.parseInt(cursor.getString(0)));
                producent.setNazwa(cursor.getString(1));
                producent.setAdres(cursor.getString(2));
                // Adding producent to list
                producentList.add(producent);
            } while (cursor.moveToNext());
        }

        // return producent list
        return producentList;
    }


}

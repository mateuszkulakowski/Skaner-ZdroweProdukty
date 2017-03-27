package mateusz.classDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import mateusz.database.DatabaseHelper;
import mateusz.klasy.SkladnikiWplyw;

/**
 * Created by Mateusz on 16.12.2016.
 */

public class SkladnikiWplywDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private String[] allColumns =
            {
                    DatabaseHelper.COLUMN_SKLADNIKIWPLYW_ID,
                    DatabaseHelper.COLUMN_SKLADNIKIWPLYW_NAZWA,
                    DatabaseHelper.COLUMN_SKLADNIKIWPLYW_RODZAJ,
                    DatabaseHelper.COLUMN_SKLADNIKIWPLYW_OPIS
            };

    public SkladnikiWplywDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addSkladnikWplyw(SkladnikiWplyw skladnikiWplyw) {

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SKLADNIKIWPLYW_NAZWA, skladnikiWplyw.getNazwa());
        values.put(DatabaseHelper.COLUMN_SKLADNIKIWPLYW_RODZAJ, skladnikiWplyw.getRodzaj());
        values.put(DatabaseHelper.COLUMN_SKLADNIKIWPLYW_OPIS, skladnikiWplyw.getOpis());

        database.insert(DatabaseHelper.TABLE_SKLADNIKIWPLYW, null, values);
        dbHelper.close();
    }

    public void deleteSkladnikWplyw(SkladnikiWplyw skladnikiWplyw)
    {
        database = dbHelper.getWritableDatabase();
        database.delete(DatabaseHelper.TABLE_SKLADNIKIWPLYW,
                DatabaseHelper.COLUMN_SKLADNIKIWPLYW_ID + " = ?",
                new String[] { String.valueOf(skladnikiWplyw.get_id()) });


        database.close();
    }

    public int updateSkladnikWplyw(SkladnikiWplyw skladnikiWplyw) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_SKLADNIKIWPLYW_NAZWA, skladnikiWplyw.getNazwa());
        values.put(DatabaseHelper.COLUMN_SKLADNIKIWPLYW_RODZAJ, skladnikiWplyw.getRodzaj());
        values.put(DatabaseHelper.COLUMN_SKLADNIKIWPLYW_OPIS, skladnikiWplyw.getOpis());

        // updating row
        return database.update(DatabaseHelper.TABLE_SKLADNIKIWPLYW,
                values,
                DatabaseHelper.COLUMN_SKLADNIKIWPLYW_ID + " = ?",
                new String[] { String.valueOf(skladnikiWplyw.get_id()) });
    }


    public List<SkladnikiWplyw> getSkladnikiWplywALL()
    {
        List<SkladnikiWplyw> skladnikiWplywList = new ArrayList<SkladnikiWplyw>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_SKLADNIKIWPLYW;

        database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SkladnikiWplyw skladnikiWplyw = new SkladnikiWplyw(Integer.parseInt(cursor.getString(0)),cursor.getString(2),cursor.getString(1),cursor.getString(3));
                // Adding skladnikwplyw to list
                skladnikiWplywList.add(skladnikiWplyw);
            } while (cursor.moveToNext());
        }

        // return skladnikwplyw list
        return skladnikiWplywList;
    }


    public SkladnikiWplyw getSkladnikById(int id)
    {
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DatabaseHelper.TABLE_SKLADNIKIWPLYW,
                new String[] { DatabaseHelper.COLUMN_SKLADNIKIWPLYW_ID, DatabaseHelper.COLUMN_SKLADNIKIWPLYW_RODZAJ, DatabaseHelper.COLUMN_SKLADNIKIWPLYW_NAZWA, DatabaseHelper.COLUMN_SKLADNIKIWPLYW_OPIS},
                DatabaseHelper.COLUMN_SKLADNIKIWPLYW_ID + "=?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null);

        SkladnikiWplyw skladnikiWplyw = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            skladnikiWplyw = new SkladnikiWplyw(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        }

        return skladnikiWplyw;
    }
}

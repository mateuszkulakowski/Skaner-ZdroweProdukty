package mateusz.classDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import mateusz.database.DatabaseHelper;
import mateusz.klasy.Produkt;

/**
 * Created by Mateusz on 16.12.2016.
 */

public class ProductDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    private String[] allColumns =
            {
                    DatabaseHelper.COLUMN_PRODUKTY_ID,
                    DatabaseHelper.COLUMN_PRODUKTY_NAZWA,
                    DatabaseHelper.COLUMN_PRODUKTY_SKLADNIKI,
                    DatabaseHelper.COLUMN_PRODUKTY_PRODUCENT_ID
            };

    public ProductDAO(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public void addProduct(Produkt product) {

        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUKTY_ID, product.get_id());
        values.put(DatabaseHelper.COLUMN_PRODUKTY_NAZWA, product.getNazwa());
        values.put(DatabaseHelper.COLUMN_PRODUKTY_SKLADNIKI, product.getSkladniki());
        values.put(DatabaseHelper.COLUMN_PRODUKTY_PRODUCENT_ID, product.getProducent_id());

        database.insert(DatabaseHelper.TABLE_PRODUKTY, null, values);
        dbHelper.close();
    }

    public void deleteProduct(Produkt product)
    {
        database = dbHelper.getWritableDatabase();
        database.delete(DatabaseHelper.TABLE_PRODUKTY,
                DatabaseHelper.COLUMN_PRODUKTY_ID + " = ?",
                new String[] { String.valueOf(product.get_id()) });




        database.close();
    }


    public int updateProduct(Produkt produkt) {
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_PRODUKTY_NAZWA, produkt.getNazwa());
        values.put(DatabaseHelper.COLUMN_PRODUKTY_SKLADNIKI, produkt.getSkladniki());
        values.put(DatabaseHelper.COLUMN_PRODUKTY_PRODUCENT_ID, produkt.getProducent_id());

        // updating row
        return database.update(DatabaseHelper.TABLE_PRODUKTY,
                values,
                DatabaseHelper.COLUMN_PRODUKTY_ID + " = ?",
                new String[] { String.valueOf(produkt.get_id()) });
    }


    public Produkt getProduct(Long id) {
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DatabaseHelper.TABLE_PRODUKTY,
                new String[] { DatabaseHelper.COLUMN_PRODUKTY_ID, DatabaseHelper.COLUMN_PRODUKTY_NAZWA, DatabaseHelper.COLUMN_PRODUKTY_SKLADNIKI, DatabaseHelper.COLUMN_PRODUKTY_PRODUCENT_ID},
                DatabaseHelper.COLUMN_PRODUKTY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        Produkt produkt = null;

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            try {
                produkt = new Produkt(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
            }
            catch(NumberFormatException ex)
            {
                produkt = new Produkt(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), null);

            }
            }

            return produkt;
    }


}
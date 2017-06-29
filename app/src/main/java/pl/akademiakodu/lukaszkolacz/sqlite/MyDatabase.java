package pl.akademiakodu.lukaszkolacz.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Lukasz Kolacz on 29.06.2017.
 */

public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(Context context, int version) {
        super(context, "cars.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table car(id integer primary key autoincrement," +
                "name text," +
                "model text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void registerCar(String name, String model) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("model", model);
        db.insertOrThrow("car", null, values);
    }

    public Cursor getAllCars() {
        String[] columns = {"id", "name", "model"};
        SQLiteDatabase db = getReadableDatabase();
        return db.query("car", columns, null, null, null, null, null);
    }

    public void deleteAllCars() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("car", null, null);
    }

    public Cursor getCar(int id) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"id", "name", "model"};
        return db.query("car", columns, "id=", new String[]{String.valueOf(id)}, null, null, null);
    }
}

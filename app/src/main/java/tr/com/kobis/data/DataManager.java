package tr.com.kobis.data;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import tr.com.kobis.model.Station;

public class DataManager extends SQLiteOpenHelper
{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "kobis_database";
    private static final String TABLE_NAME = "stations";
    private static String ID = "id";
    private static String STATION_NAME = "station_name";
    private static String EMPTY_PARK = "empty_park";
    private static String BICYCLE_COUNT = "bicycle_count";
    private static String LAST_UPDATE = "last_update";

    public DataManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + STATION_NAME + " TEXT,"
                + BICYCLE_COUNT + " TEXT,"
                + EMPTY_PARK + " TEXT,"
                + LAST_UPDATE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    public ArrayList<Station> getStationsList()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Station> stationList = new ArrayList<Station>();

        if (cursor.moveToFirst())
        {
            do
            {
                String deneme = cursor.getString(1);
                    Station station = new Station();
                    station.setName(cursor.getString(1));
                    station.setBicycleCount(cursor.getString(2));
                    station.setEmptyPark(cursor.getString(3));

                    stationList.add(station);

            } while (cursor.moveToNext());
        }
        db.close();
        return stationList;
    }

    public ArrayList<HashMap<String, String>> getStations()
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> stationList = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst())
        {
            do
            {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 1; i < 4; i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                stationList.add(map);
            } while (cursor.moveToNext());
        }
        db.close();

        return stationList;
    }

    public String getLastUpdate()
    {

        String lastUpdate = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " limit 2";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                lastUpdate = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        db.close();

        return lastUpdate;
    }

    public void updateStations(ArrayList<Station> stationlist, String lastUpdate)
    {
        resetTables();

        for(Station station : stationlist)
        {
            saveStation(station.getName(), String.valueOf(station.getBicycleCount()), lastUpdate, String.valueOf
                    (station.getEmptyPark()));
        }

    }

    public void saveStation(String stationName, String bycleCount, String lastUpdate, String emptyPark)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STATION_NAME, stationName);
        values.put(BICYCLE_COUNT, bycleCount);
        values.put(LAST_UPDATE, lastUpdate);
        values.put(EMPTY_PARK, emptyPark);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void resetTables()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
    {
        // TODO Auto-generated method stub

    }

}

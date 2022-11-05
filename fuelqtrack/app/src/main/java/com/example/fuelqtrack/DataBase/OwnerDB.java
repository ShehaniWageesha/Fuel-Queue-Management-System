package com.example.fuelqtrack.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fuelqtrack.Models.OwnerModel;
import com.example.fuelqtrack.Models.UserModel;

public class OwnerDB extends SQLiteOpenHelper {

    private static String dbName = "OwnerDB";
    private static String tableName = "Owner";
    private static String idColumn = "id";
    private static String userNameColumn = "name";
    private static String mobileColumn = "number";
    private static String nicColumn = "nic";
    private static String stationNameColumn = "stationName";
    private static String passwordColumn = "password";

    public OwnerDB(Context context) {
        super(context, dbName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " integer primary key autoincrement, " +
                userNameColumn + " text," +
                mobileColumn + " text," +
                nicColumn + " text," +
                stationNameColumn + " text," +
                passwordColumn + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean create(OwnerModel ownerModel) {
        boolean result = true;
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(userNameColumn, ownerModel.getName());
            contentValues.put(mobileColumn, ownerModel.getNumber());
            contentValues.put(nicColumn, ownerModel.getNic());
            contentValues.put(stationNameColumn, ownerModel.getStationName());
            contentValues.put(passwordColumn, ownerModel.getPassword());
            result = sqLiteDatabase.insert(tableName, null, contentValues) > 0;

        } catch (Exception e) {
            result = false;
        }
        return result;
    }


    public OwnerModel login(String mobile, String password) {
        OwnerModel userA = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where number = ? and password = ?", new String[]{mobile, password});
            if (cursor.moveToFirst()) {
                userA = new OwnerModel();
                userA.setId(cursor.getInt(0));
                userA.setName(cursor.getString(1));
                userA.setNumber(cursor.getString(2));
                userA.setNic(cursor.getString(3));
                userA.setStationName(cursor.getString(4));
                userA.setPassword(cursor.getString(5));

            }
        } catch (Exception e) {
            userA = null;
        }
        return userA;
    }

    public OwnerModel checkUsername(String username) {
        OwnerModel userM = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where name = ?", new String[]{username});
            if (cursor.moveToFirst()) {
                userM = new OwnerModel();
                userM.setId(cursor.getInt(0));
                userM.setName(cursor.getString(1));
                userM.setNumber(cursor.getString(2));
                userM.setNic(cursor.getString(3));
                userM.setStationName(cursor.getString(4));
                userM.setPassword(cursor.getString(5));
            }
        } catch (Exception e) {
            userM = null;
        }
        return userM;
    }
}

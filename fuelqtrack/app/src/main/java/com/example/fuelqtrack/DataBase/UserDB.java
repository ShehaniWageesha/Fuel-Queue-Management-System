package com.example.fuelqtrack.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fuelqtrack.Models.UserModel;

public class UserDB extends SQLiteOpenHelper {
    private static  String dbName= "UserDB";
    private static  String tableName= "User";
    private static  String idColumn= "id";
    private static  String userNameColumn= "name";
    private static  String mobileColumn= "number";
    private static  String passwordColumn= "password";

    public UserDB(Context context){
        super(context, dbName, null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " integer primary key autoincrement, " +
                userNameColumn + " text," +
                mobileColumn + " text," +
                passwordColumn + " text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public boolean create(UserModel userModel){
        boolean result = true;
        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(userNameColumn, userModel.getName());
            contentValues.put(mobileColumn, userModel.getNumber());
            contentValues.put(passwordColumn, userModel.getPassword());
            result = sqLiteDatabase.insert(tableName, null, contentValues) > 0;

        }catch (Exception e){
            result = false;
        }
        return result;
    }


    public UserModel login(String mobile, String password){
        UserModel userA = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where number = ? and password = ?", new String[]{mobile, password});
            if (cursor.moveToFirst()){
                userA = new UserModel();
                userA.setId(cursor.getInt(0));
                userA.setName(cursor.getString(1));
                userA.setNumber(cursor.getString(2));
                userA.setPassword(cursor.getString(3));

            }
        }catch (Exception e){
            userA = null;
        }
        return userA;
    }
    public UserModel checkUsername(String username){
        UserModel userM = null;
        try {
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where name = ?", new String[]{username});
            if (cursor.moveToFirst()){
                userM = new UserModel();
                userM.setId(cursor.getInt(0));
                userM.setName(cursor.getString(1));
                userM.setNumber(cursor.getString(2));
                userM.setPassword(cursor.getString(3));
            }
        }catch (Exception e){
            userM = null;
        }
        return userM;
    }
}

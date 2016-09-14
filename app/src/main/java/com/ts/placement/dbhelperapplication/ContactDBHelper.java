package com.ts.placement.dbhelperapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/1/2016.
 */
public class ContactDBHelper extends SQLiteOpenHelper {

    public ContactDBHelper(Context context) {
        super(context, "ADDRESSBOOKDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "create table if not exists CONTACT(id integer primary key" +
                ", name varchar, phone varchar)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
       // db.execSQL("DROP TABLE IF EXISTS CONTACT");

        // Create tables again
        onCreate(db);
    }

    //add contact in table
    public void addContacts(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", contact.getId());
        contentValues.put("name", contact.getName());
        contentValues.put("phone", contact.getPhone());
        db.insert("CONTACT", null, contentValues);

    }

    public List<Contact> getAllContacts() {
        // Select Query
        String selectQuery = "SELECT  * FROM CONTACT";

        //retrieving records from table
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Create dynamic List to store contact objects
        List<Contact> contactList = new ArrayList<Contact>();

        // looping through all rows and adding to list
        while (cursor.moveToNext()) {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            }
        Log.d("No of record", ""+contactList.size());
        // return contact list
        return contactList;
    }

    public List<Integer> getAllIds() {
        // Select Query
        String selectQuery = "SELECT  id FROM CONTACT";

        //retrieving records from table
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //Create dynamic List to store contact objects
        List<Integer> contactList = new ArrayList<Integer>();

        // looping through all rows and adding to list
        while (cursor.moveToNext()) {
            contactList.add(Integer.parseInt(cursor.getString(0)));
        }
        Log.d("No of IDs", ""+contactList.size());
        // return contact list
        return contactList;
    }

    public void deleteContectById(int id){
        //deleting record from table
        Log.d("DBHELPER DELETE METHOD",""+id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CONTACT","id="+id, null);
    }

    public void updateContectById(Contact contact){

        Log.d("DBHELPER UPDATE METHOD",""+contact);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());

        // updating row
        db.update("CONTACT", values, "id = ?",
                new String[] { String.valueOf(contact.getId()) });

    }
    public Contact getContactById(int id) {
        // Select Query
        String selectQuery = "SELECT  * FROM CONTACT where id ="+id;
Log.d("get contact by ID ", selectQuery);
        //retrieving records from table
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Contact contact = new Contact();
        // looping through all rows and adding to list
        while (cursor.moveToNext()) {

            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setPhone(cursor.getString(2));
        }
        Log.d("Contect", ""+contact);
        // return contact list
        return contact;
    }

}

package com.example.p7basededatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Version de la base de datos
    private static final int DATABASE_VERSION=1;
    //nombre de la base de datos
    private static final String DATABASE_NAME="contactManager";
    //nombre de la tabla contactos
    private static final String TABLE_CONTACTS="contacts";
    //nombre de las columnas de la tabla contactos
    private static final String KEY_ID="id";
    private static final String KEY_NAME="name";
    private static final String KEY_PH_NO="phone_number";
    //constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //creando tablas
    @Override
    public void onCreate (SQLiteDatabase db){
        String CREATE_CONTACTS_TABLE="CREATE TABLE "+ TABLE_CONTACTS+"("
                +KEY_ID+" INTEGER PRIMARY KEY,"
                +KEY_NAME+" TEXT,"
                +KEY_PH_NO+" TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    //ACTUALIZANDO BASE DE DATOS
    @Override
    public void  onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //borrar tablas antiguas si existen
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACTS);
        onCreate(db);
    }
    //CRUD (Create, Read, Update, Delete)
    //Agregar Contactos
    public void addContacts(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME, contact.getName()); //nombre del contacto
        values.put(KEY_PH_NO, contact.getPhoneNumber()); //telefono del contacto
        //insertar filas
        db.insert(TABLE_CONTACTS, null,values);
        db.close(); //cerrar la conexion de la base de datos
    }
    //obtener contactos
    public Contact getContact (int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CONTACTS, new String[]{KEY_ID,KEY_NAME,KEY_PH_NO},
                KEY_ID+"=?",new String[]{String.valueOf(id)},null,
                null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        Contact contact=new Contact(Integer.parseInt(cursor.getString(0)),
        cursor.getString(1),cursor.getString(2));
        //regresar contacto
        return contact;
    }
    //obtener todos los contactos
    public List <Contact> getAllContacts() {
        ArrayList<Contact> allContacts = new ArrayList<Contact>();
        //consolta selecciona todo
        String selectQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //Itera sobre todas las filas y las agrega a la lista
        if (cursor.moveToFirst()){
            do {
                Contact contact=new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                allContacts.add(contact);
                //agrega contactos a la lista
            }while (cursor.moveToNext());
        }
        return allContacts;
    }
    //actualizar contactos
    public void updateContact(int id,String name, String phonenumber) {
    SQLiteDatabase db=this .getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_NAME,name);
    values.put(KEY_PH_NO,phonenumber);
    //actualizar fila
        db.update(TABLE_CONTACTS,values,KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }

    //borrar Contacto
    public void deleteContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,KEY_ID+"=?",
        new String[]{String.valueOf(contact.getId())});
        db.close();
    }
    //Obtiene conteo de contactos
    public int getContactsCount(){
        String countQuery="SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        return cursor.getCount();
    }
}

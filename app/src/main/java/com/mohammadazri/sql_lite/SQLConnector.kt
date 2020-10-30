 package com.mohammadazri.sql_lite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLConnector(context:Context) : SQLiteOpenHelper(context, DB_name, null, 1) {

    //A PLACE WHERE WE CAN ADD VARIABLES
    companion object {
        val DB_name = "House.db "
        val TB_name = "Person "
        val id = "ID"
        val pName = "Name"
        val age = "Age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TB_name(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Age TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TB_name")
    }

    val showData:Cursor get() {
        val DB = this.writableDatabase
        val data = DB.rawQuery("SELECT * FROM " + TB_name, null)
        return data
    }

    fun addData(name:String, age_new:String) {
        val DB = this.writableDatabase
        val values = ContentValues()
        values.put(pName, name)
        values.put(age, age_new)

        DB.insert(TB_name, null, values)
    }

    fun deleteData(id:String) : Int {
        val DB = this.writableDatabase
        val item = DB.delete(TB_name, "id = ?", arrayOf(id))

        return item
    }

}
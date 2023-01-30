package com.studentnate.week13demo
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASENAME = "EDATABASE"
val TABLENAME = "Events"
val COL_TITLE = "ETitle"
val COL_DATE = "EDate"
val COL_DETAIL = "EDetail"
val COL_ID = "id"


class DbHelper(var context: Context?) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TITLE + " VARCHAR(256)," + COL_DETAIL + " VARCHAR(256),"+ COL_DATE + " DATE)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLENAME)
        onCreate(db)
    }

    fun insertEvent(contentValues: ContentValues): Long {
        val database = this.writableDatabase
        return database.insert(TABLENAME, null, contentValues)
    }

    @SuppressLint("Range")
    fun getEvents(): Cursor {

        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        return db.rawQuery(query, null)

    }

    fun deleteEvent(id : Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLENAME, "id=?", arrayOf(id.toString()))
    }

    fun updateEvent(contentValues: ContentValues, id: Int): Int {
        val db = this.writableDatabase
        return db.update(TABLENAME, contentValues, "id=?", arrayOf(id.toString()))
    }
}
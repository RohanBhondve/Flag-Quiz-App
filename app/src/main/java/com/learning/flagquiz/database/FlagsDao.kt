package com.learning.flagquiz.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.learning.flagquiz.model.FlagsModel
import com.techmania.flagquizwithsqlitedemo.DatabaseCopyHelper

class FlagsDao {
    //this gives us 5 questions
    fun randomFiveRecords(helper: DatabaseCopyHelper):ArrayList<FlagsModel>{
        val recordList = ArrayList<FlagsModel>()
        val database: SQLiteDatabase = helper.readableDatabase
        val cursor: Cursor = database.rawQuery("SELECT * FROM flags ORDER BY Random() LIMIT 5",null)

        val idIndex = cursor.getColumnIndex("flag_id")
        val countryNameIndex = cursor.getColumnIndex("country_name")
        val flagNameIndex = cursor.getColumnIndex("flag_name")

        while(cursor.moveToNext()){
            val record = FlagsModel(cursor.getInt(idIndex),cursor.getString(countryNameIndex),cursor.getString(flagNameIndex))
            recordList.add(record)
        }
        cursor.close()
        return recordList
    }

    //this gives us 3 incorrect options
    fun randomThreeRecords(helper: DatabaseCopyHelper,id: Int):ArrayList<FlagsModel>{
        val recordList = ArrayList<FlagsModel>()
        val database: SQLiteDatabase = helper.readableDatabase
        val cursor: Cursor = database.rawQuery("SELECT * FROM flags WHERE flag_id != ? ORDER BY Random() LIMIT 3",arrayOf(id.toString()))

        val idIndex = cursor.getColumnIndex("flag_id")
        val countryNameIndex = cursor.getColumnIndex("country_name")
        val flagNameIndex = cursor.getColumnIndex("flag_name")

        while(cursor.moveToNext()){
            val record = FlagsModel(cursor.getInt(idIndex),cursor.getString(countryNameIndex),cursor.getString(flagNameIndex))
            recordList.add(record)
        }
        cursor.close()
        return recordList
    }

}
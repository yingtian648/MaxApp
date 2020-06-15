package com.zjhj.maxapp.room

import android.content.Context
import androidx.room.Room

class RoomManager(var applicationContext: Context) {
    companion object{
        val DBNAME = "max_db"
    }
    var db: MyDatabase? = null

    init {
        db = Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java, DBNAME
        )
            .allowMainThreadQueries() //允许在主线程中查询
            .build()
    }

    fun getDB():MyDatabase?{
        if(db==null){
            db = Room.databaseBuilder(
                applicationContext,
                MyDatabase::class.java, DBNAME
            )
                .allowMainThreadQueries() //允许在主线程中查询
                .build()
        }
        return db
    }
}
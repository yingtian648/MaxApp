package com.zjhj.maxapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zjhj.maxapp.room.dao.PlayListDao
import com.zjhj.maxapp.room.dao.UserDao
import com.zjhj.maxapp.room.entity.PlayList
import com.zjhj.maxapp.room.entity.UserInfo

@Database(entities = [UserInfo::class,PlayList::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?
    abstract fun playListDao(): PlayListDao?
}
package com.zjhj.maxapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "UserInfo")
class UserInfo() {
    @PrimaryKey(autoGenerate = true)//自增长
    var id: Long = 0
    var name: String? = null
    var tel: String? = null
    var birthday: Long = 0
}
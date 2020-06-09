package com.zjhj.maxapp.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlayList() {
    @PrimaryKey(autoGenerate = true)//自增长
    var id: Long = 0
    var name: String? = null
    var userId: Long = 0
}
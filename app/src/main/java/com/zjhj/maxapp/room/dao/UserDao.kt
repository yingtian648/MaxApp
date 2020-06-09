package com.zjhj.maxapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zjhj.maxapp.room.base.BaseDao
import com.zjhj.maxapp.room.entity.UserInfo

interface UserDao : BaseDao<UserInfo> {
    @Query("SELECT * FROM UserInfo")
    fun getAllUser(): Array<UserInfo>?

    @Query("SELECT * FROM UserInfo WHERE name > :minAge")
    fun loadAllUsersOlderThan(minAge: Int): Array<UserInfo>

    @Query("SELECT * FROM UserInfo WHERE name IN (:regions)")
    fun loadUsersFromRegions(regions: List<String>): List<UserInfo>

    @Query("SELECT * FROM UserInfo WHERE name IN (:regions)")
    fun loadUsersFromRegionsSync(regions: List<String>): LiveData<List<UserInfo>>
}
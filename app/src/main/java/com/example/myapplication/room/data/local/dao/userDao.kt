package com.example.myapplication.room.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.room.data.local.entity.userEntity

@Dao
interface userDao {
    @Query("SELECT * FROM user")
    fun observeAll(): LiveData<List<userEntity>>

    @Query("SELECT * FROM user WHERE id = :userid")
    fun observeById(userid : Int): LiveData<userEntity?>

    @Query("SELECT * FROM user WHERE id = :userid")
    suspend fun findByid(userid: Int) : userEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: userEntity)

    @Insert
    suspend fun insertMary(users: List<userEntity>)

    @Update
    suspend fun update(user: userEntity)

    @Delete
    suspend fun delete(user: userEntity)
}
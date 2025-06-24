package com.example.myapplication.room.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.myapplication.room.data.local.entity.noteEntity
import com.example.myapplication.room.data.local.entity.userAndnote

@Dao
interface noteDao {

    @Query("SELECT * FROM note")
    fun observeAll() : LiveData<List<noteEntity>>

    @Query("SELECT * FROM note WHERE id = :userid")
    fun observeById(userid: Int) : LiveData<noteEntity?>

    @Query("SELECT * FROM note WHERE id = :userid")
    suspend fun findbyid(userid: Int) : noteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note : noteEntity)

    @Insert
    suspend fun insertAll(notes : List<noteEntity>)

    @Update
    suspend fun update(note: noteEntity)

    @Delete
    suspend fun delete(note: noteEntity)

    // 1 user to n note
    @Query("SELECT * FROM user WHERE id = :userid")
    @Transaction
    fun observeuserAndnoteByuserId(userid: Int) : LiveData<userAndnote?>

    @Query("DELETE FROM note WHERE user_id = :userid")
    suspend fun deletenoteByuserId(userid: Int)
}
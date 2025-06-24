package com.example.myapplication.room.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.room.data.local.dao.noteDao
import com.example.myapplication.room.data.local.dao.userDao
import com.example.myapplication.room.data.local.entity.noteEntity
import com.example.myapplication.room.data.local.entity.userEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

@Database(
    version = 1,
    entities = [
        userEntity::class,
        noteEntity::class,
    ],
    exportSchema = false,
)
abstract class MyRoomDatabase: RoomDatabase() {
    abstract fun userD() : userDao
    abstract  fun noteD() : noteDao

    companion object{
      private const val DB_NAME = "android007"

        @Volatile
        private var INSTANCE: MyRoomDatabase?=null

        private fun buildDB(context: Context): MyRoomDatabase{
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = MyRoomDatabase::class.java,
            name = DB_NAME
        )
            .setQueryExecutor(Dispatchers.IO.asExecutor())
            .build()
        }
        fun getInstance(context: Context): MyRoomDatabase{
            return INSTANCE ?:  synchronized(this){
                INSTANCE ?: buildDB(context).also { INSTANCE = it }
            }
        }
    }
}
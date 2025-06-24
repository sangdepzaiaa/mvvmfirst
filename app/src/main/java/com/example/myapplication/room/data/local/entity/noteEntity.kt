package com.example.myapplication.room.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "note",
    foreignKeys = [
        ForeignKey(
            entity = userEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE

        )
    ],
    indices = [
        Index("user_id"),
    ]
    )
data class noteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int?,

    @ColumnInfo(name = "title")
    val title:String,

    @ColumnInfo(name = "user_id")
    val userid:Int,
)

package com.example.myapplication.room.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation


data class userAndnote(
    @Embedded
    val user: userEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "user_id",
        entity = noteEntity::class,
    )
    val notes: List<noteEntity>
)

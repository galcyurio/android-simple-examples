package com.github.galcyurio.roomtodo.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val task: String,
    val detail: String? = null
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
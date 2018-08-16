package com.github.galcyurio.roomtodo.data.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Task(
    var task: String? = null,
    var detail: String? = null
) {
    @PrimaryKey(autoGenerate = true) var id: Long? = null
}
package com.github.galcyurio.ormlitetodo.data

import com.github.galcyurio.ormlitetodo.misc.NoArgsConstructor
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@NoArgsConstructor
@DatabaseTable(tableName = "tasks")
data class Task(
    @DatabaseField(columnName = "title")
    var title: String,

    @DatabaseField(columnName = "description")
    var description: String? = null,

    @DatabaseField(columnName = "is_done")
    var isDone: Boolean = false,

    @DatabaseField(generatedId = true, columnName = "id")
    val id: Long? = null
)
package com.github.galcyurio.roomtodo.data

import androidx.room.*
import com.github.galcyurio.roomtodo.data.domain.Task
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface TaskDao {

    @Insert
    fun insert(vararg tasks: Task): Array<Long>

    @Update
    fun update(vararg tasks: Task): Int

    @Delete
    fun delete(vararg tasks: Task): Int

    @Query("""
        SELECT * FROM task
        WHERE id = :id
    """)
    fun findById(id: Long): Maybe<Task>

    @Query("""
        SELECT * FROM task
        LIMIT :limit OFFSET :offset
    """)
    fun findAll(limit: Int, offset: Int): Single<List<Task>>
}
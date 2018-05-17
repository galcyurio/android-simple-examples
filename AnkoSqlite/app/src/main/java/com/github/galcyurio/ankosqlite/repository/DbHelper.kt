package com.github.galcyurio.ankosqlite.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "AnkoSqlite") {
    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) instance = DbHelper(ctx)
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createDb(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // empty
    }

    fun createDb(db: SQLiteDatabase): DbHelper = apply {
        db.createTable("Customer", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "name" to TEXT,
            "email" to TEXT)

    }

    fun dropDb(db: SQLiteDatabase): DbHelper = apply {
        db.dropTable("Customer", true)
    }
}

package com.github.galcyurio.ankosqlite.repository

import com.github.galcyurio.ankosqlite.domain.Customer
import org.jetbrains.anko.db.*

class CustomerRepository(private val dbHelper: DbHelper) {

    fun save(customer: Customer): Customer {
        return dbHelper.use {
            val id = insertOrThrow("Customer",
                "name" to customer.name, "email" to customer.email)
            customer.copy(id = id.toInt())
        }
    }

    fun findAll(): List<Customer> {
        return dbHelper.use {
            select("Customer")
                .exec { parseList(classParser()) }
        }
    }

    fun findOne(id: Int): Customer? {
        return dbHelper.use {
            select("Customer")
                .whereArgs("id = {id}", "id" to id)
                .exec { parseOpt(classParser()) }
        }
    }

    /**
     * @return 업데이트된 행의 갯수
     */
    fun update(customer: Customer): Int {
        return dbHelper.use {
            update("Customer",
                "name" to customer.name, "email" to customer.email)
                .whereArgs("id = {id}", "id" to customer.id!!)
                .exec()
        }
    }

    /**
     * @return 지워진 행의 갯수
     */
    fun deleteOne(id: Int): Int {
        return dbHelper.use {
            delete("Customer", "id = {id}", "id" to id)
        }
    }
}
package com.github.galcyurio.ankosqlite.robolectric

import com.github.galcyurio.ankosqlite.BuildConfig
import com.github.galcyurio.ankosqlite.domain.Customer
import com.github.galcyurio.ankosqlite.repository.CustomerRepository
import com.github.galcyurio.ankosqlite.repository.DbHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class CustomerRepositoryTest {

    lateinit var customerRepository: CustomerRepository

    @Before
    fun setUp() {
        val dbHelper = DbHelper(RuntimeEnvironment.application)
        customerRepository = CustomerRepository(dbHelper)

        val db = dbHelper.writableDatabase
        dbHelper.dropDb(db).createDb(db)
        db.close()
    }

    @Test
    fun `save 테스트`() {
        val actual = customerRepository.save(Customer(
            name = "dummy name",
            email = "test@test.com"
        ))
        assertThat(actual.id).isGreaterThan(0)
    }

    @Test
    fun `findAll 테스트`() {
        val size = 5
        saveDummy(size)
        val customers = customerRepository.findAll()
        assertThat(customers.size).isEqualTo(5)
        println(customers)
    }

    @Test
    fun `findOne 테스트`() {
        saveDummy(1)
        val customer = customerRepository.findOne(1)
        assertThat(customer!!.id).isEqualTo(1)
        println(customer)
    }

    @Test
    fun `update 테스트`() {
        val customer = saveDummy(1)[0]
        customer.name = "updated"
        customer.email = "updated@test.com"
        assertThat(customerRepository.update(customer)).isEqualTo(1)

        val actual = customerRepository.findOne(1)
        assertThat(actual).isEqualTo(customer)

        println("""
            customer = $customer
            actual = $actual
        """.trimIndent())
    }

    @Test
    fun `delete 테스트`() {
        val customer = saveDummy(1)[0]
        assertThat(customerRepository.deleteOne(customer.id!!)).isGreaterThan(0)

        val actual = customerRepository.findOne(customer.id!!)
        assertThat(actual).isNull()
    }

    fun saveDummy(num: Int): List<Customer> {
        return (1..num).asSequence()
            .map { customerRepository.save(Customer(name = "dummy$it", email = "dummy$it@test.com")) }
            .toList()
    }
}
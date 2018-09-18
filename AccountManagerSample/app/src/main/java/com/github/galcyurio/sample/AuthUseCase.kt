package com.github.galcyurio.sample

import java.util.*

/**
 * @author galcyurio
 */
object AuthUseCase {
    private val users = mutableMapOf<String, String>()
    private val tokens = mutableMapOf<String, String>()

    fun signIn(email: String, password: String): String? {
        return if (users[email] == password) {
            val token = UUID.randomUUID().toString()
            tokens[email] = token
            token
        } else {
            null
        }
    }

    fun signUp(email: String, password: String): String {
        users[email] = password
        val token = UUID.randomUUID().toString()
        tokens[email] = token
        return token
    }
}
package com.github.galcyurio.model.domain

import java.util.*

/**
 * @author galcyurio
 */
data class Doggy(
    val name: String,
    val age: Int,

    val id: String = UUID.randomUUID().toString()
)

internal enum class DoggyNames {
    FLUFFY, SCRATCHY, MINNIE, TIGER, LUCY, PUMPKIN
}
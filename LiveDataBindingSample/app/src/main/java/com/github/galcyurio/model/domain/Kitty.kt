package com.github.galcyurio.model.domain

import java.util.*

data class Kitty(
    val name: String,
    val age: Int,

    val id: String = UUID.randomUUID().toString()
)

internal enum class KittyNames {
    FLUFFY, SCRATCHY, MINNIE, TIGER, LUCY, PUMPKIN
}
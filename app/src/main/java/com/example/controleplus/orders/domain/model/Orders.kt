package com.example.controleplus.orders.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Orders(
    @PrimaryKey val id: Int? = null,
    val amount: Double,
    val category: String,
    val type: String,
    val date: Long,
)

class InvalidOrdersException(message: String): Exception(message)
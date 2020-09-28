package com.douglasluz.listadecompras.models

import android.graphics.Bitmap

data class Product(
    val name: String,
    val quantity: Int,
    val price: Double,
    val image: Bitmap? = null
)
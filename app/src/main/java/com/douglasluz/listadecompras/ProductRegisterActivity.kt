package com.douglasluz.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.douglasluz.listadecompras.models.Product
import com.douglasluz.listadecompras.repository.productsRepository
import kotlinx.android.synthetic.main.activity_product_register.*

class ProductRegisterActivity : AppCompatActivity() {
    private val COD_IMAGE = 101
    private var imageBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_register)

        product_photo.setOnClickListener {
            openGallery()
        }

        product_insert.setOnClickListener{
            val productName = product_text.text.toString()
            val productQuantity = product_quantity.text.toString()
            val productPrice = product_price.text.toString()

            if (productName.isNotEmpty() && productQuantity.isNotEmpty() && productPrice.isNotEmpty()) {
                val product = Product(productName, productQuantity.toInt(), productPrice.toDouble(), imageBitmap)
                productsRepository.add(product)
                product_text.text.clear()
                product_price.text.clear()
                product_quantity.text.clear()
            } else {
                product_text.error = if (productName.isEmpty()) "Please give a name to the product" else null
                product_quantity.error = if (productQuantity.isEmpty()) "Please give a quantity to the product" else null
                product_price.error = if (productPrice.isEmpty()) "Please give a price to the product" else null
             }
        }
    }
    private fun openGallery () {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val inputStream = data.data?.let { contentResolver.openInputStream(it) }
                imageBitmap = BitmapFactory.decodeStream(inputStream)
                product_photo.setImageBitmap(imageBitmap)
            }
        }
    }
}
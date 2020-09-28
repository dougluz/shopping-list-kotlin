package com.douglasluz.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.douglasluz.listadecompras.models.Product
import com.douglasluz.listadecompras.repository.productsRepository
import kotlinx.android.synthetic.main.activity_product_register.*

class ProductRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_register)

        product_insert.setOnClickListener{
            val productName = product_text.text.toString()
            val productQuantity = product_quantity.text.toString()
            val productPrice = product_price.text.toString()

            if (productName.isNotEmpty() && productQuantity.isNotEmpty() && productPrice.isNotEmpty()) {
                val product = Product(productName, productQuantity.toInt(), productPrice.toDouble())
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
}
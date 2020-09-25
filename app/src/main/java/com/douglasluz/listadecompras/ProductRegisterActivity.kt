package com.douglasluz.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product_register.*

class ProductRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_register)

        product_insert.setOnClickListener{
            val product = product_text.text.toString()

            if (product.isNotEmpty()) {
                product_text.text.clear()
            } else {
                product_text.error = "Please type something"
            }
        }

    }
}
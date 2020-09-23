package com.douglasluz.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        items_list.adapter = productsAdapter

        btn_insert.setOnClickListener{
            val product = text_product.text.toString()

            if (product.isNotEmpty()) {
                productsAdapter.add(product)
                text_product.text.clear()
            } else {
                text_product.error = "Please type something"
            }
        }

        items_list.setOnItemLongClickListener { _, view: View?, position: Int, id: Long ->
            val item = productsAdapter.getItem(position)
            productsAdapter.remove(item)
            true
        }
    }
}
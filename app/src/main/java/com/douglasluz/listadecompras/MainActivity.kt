package com.douglasluz.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        items_list.adapter = productsAdapter

        items_list.setOnItemLongClickListener { _, _, position: Int, _ ->
            val item = productsAdapter.getItem(position)
            productsAdapter.remove(item)
            true
        }

        add_button.setOnClickListener {
            val intent = Intent(this, ProductRegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.douglasluz.listadecompras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.douglasluz.listadecompras.repository.productsRepository
import com.douglasluz.listadecompras.util.ProductAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productsAdapter = ProductAdapter(this)

        productsAdapter.addAll(productsRepository)
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

    override fun onResume() {
        super.onResume()
        val format = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        val adapter = items_list.adapter as ProductAdapter
        adapter.clear()
        adapter.addAll(productsRepository)

        val total = productsRepository.sumByDouble { it.price * it.quantity }
        total_text.text = "TOTAL: ${ format.format(total)}"
    }
}
package com.sideproject.productreviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProductDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val textView1 = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val textView3 = findViewById<TextView>(R.id.textView3)

        val productInfo = intent.getParcelableExtra<ProductInfo>("productInfo")

        textView1.setText(productInfo?.productName)
        textView2.setText(productInfo?.productDescription)
        textView3.setText("${productInfo?.productRate}")
    }
}
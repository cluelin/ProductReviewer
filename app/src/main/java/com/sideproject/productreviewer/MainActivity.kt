package com.sideproject.productreviewer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class MainActivity : AppCompatActivity() {

    private var barcodeContent : String? = null
    private var dao = DAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scannerBtn = findViewById<Button>(R.id.scannerBtn)

        scannerBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                IntentIntegrator(this@MainActivity).initiateScan()
            }
        })

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        barcodeContent = getBarcode(result)

        barcodeContent?.let { barcode ->
            val productInfo = dao.searchByCode(barcode)
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("productInfo", productInfo)
            startActivity(intent)

        }

        barcodeContent?:let{
            Log.d(LOG_TAG, "barcodeContent null")
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun getBarcode(result: IntentResult) : String?{
        result?.let {
            result.contents?.let { content ->
                Log.d(LOG_TAG, "barcode content : $content")
                Toast.makeText(this, "Scanned: $content", Toast.LENGTH_LONG).show()
                return content
            }
        }

        return null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object{
        const val LOG_TAG = "MainActivity"
    }
}
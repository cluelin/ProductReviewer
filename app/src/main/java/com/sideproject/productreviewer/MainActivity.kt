package com.sideproject.productreviewer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
        IntentIntegrator(this).initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)


        barcodeContent = getBarcode(result)


        barcodeContent?.let{ barcode->
            dao.searchByCode(barcode)
        }


        barcodeContent?:super.onActivityResult(requestCode, resultCode, data)

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
        const val LOG_TAG = "barcode"
    }
}
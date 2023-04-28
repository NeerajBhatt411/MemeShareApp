package com.example.memeshareapp1

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    val currentImageUrl: String? = "https://i.redd.it/75zqttbty9wa1.gif"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadMeme()
    }
   fun loadMeme(){
        val ProgressDialog = ProgressDialog(this)
        ProgressDialog.setMessage("Please wait while meme loading")
        ProgressDialog.show()

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
               val currentImageUrl = response.getString("url")

                val url = response.getString("url")
                val memeImageView = findViewById<ImageView>(R.id.memeImageView)
                Glide.with(this).load(currentImageUrl).into(memeImageView);
                ProgressDialog.dismiss()

            },

            {
                ProgressDialog.dismiss()
            })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }

    fun nextMeme(view: View) {
        loadMeme()


    }
    fun shareMeme(view: View) {


        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout this Meme from Reddit $currentImageUrl")
        val chooser = Intent.createChooser(intent, "Share this Meme")
        startActivity(chooser)
    }
}


package com.example.testingweatherapp.views

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.testingweatherapp.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)

        button.setOnClickListener(View.OnClickListener {
            Log.d("this", "WEATHER FORCAST ON GITHUBB")
            val intent = Intent(this, Forecast::class.java)
            startActivity(intent)
        })

        button2.setOnClickListener(View.OnClickListener {
            Log.d("this", "SEARCH YOUR GODAMN NEIGHBOURS ON GITHUBBB")
            val intent = Intent(this, Searchbars::class.java)
            startActivity(intent)
        })

    }

}
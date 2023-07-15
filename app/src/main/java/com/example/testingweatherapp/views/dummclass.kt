package com.example.testingweatherapp.views

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testingweatherapp.Interface.Contract
import com.example.testingweatherapp.R

class dummclass : AppCompatActivity(), Contract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forecast_other)
    }

}
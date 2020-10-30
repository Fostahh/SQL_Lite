package com.mohammadazri.sql_lite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Add_Data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__data)

        //Calling SQL Connector Class to be Object
        val DB = SQLConnector(applicationContext)

        val name_input = findViewById<EditText>(R.id.name_editText)
        val age_input = findViewById<EditText>(R.id.age_editText)
        val add_btn = findViewById<Button>(R.id.add_btn)

        add_btn.setOnClickListener {
            val name_text = name_input.text.toString().trim()
            val age_text = age_input.text.toString().trim()

            DB.addData(name_text, age_text)
            Toast.makeText(this@Add_Data, "Data has been added", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@Add_Data, MainActivity::class.java))
        }

    }
}
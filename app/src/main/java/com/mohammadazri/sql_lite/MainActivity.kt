package com.mohammadazri.sql_lite

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var lists:ArrayList<Person>
    lateinit var DB:SQLConnector
    lateinit var data:Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val go = findViewById<Button>(R.id.go)
        val show = findViewById<Button>(R.id.show)

        lists = ArrayList<Person>()
        DB = SQLConnector(applicationContext)
        data = DB.showData

        val adapter = Adapter(lists, applicationContext)
        val recycler = findViewById<RecyclerView>(R.id.list)

        go.setOnClickListener {
            startActivity(Intent(this@MainActivity, Add_Data::class.java))
        }

        show.setOnClickListener {
            if (data.count == 0) {
                Toast.makeText(applicationContext, "There is no items", Toast.LENGTH_SHORT).show()
            }

            recycler.layoutManager = GridLayoutManager(applicationContext, 2)
            recycler.adapter = adapter
            dataShow()
        }
    }

    private fun dataShow() {
        if (data.count == 0) {
            Toast.makeText(applicationContext, "There is no items", Toast.LENGTH_SHORT).show()
        }

        while(data.moveToNext()) {
            lists.add(Person(data.getString(0)
            ,data.getString(1)
            ,data.getString(2)
            ))
        }
    }

}
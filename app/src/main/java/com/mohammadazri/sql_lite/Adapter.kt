package com.mohammadazri.sql_lite

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohammadazri.sql_lite.SQLConnector.Companion.id

class Adapter(Person:ArrayList<Person>, var context:Context) : RecyclerView.Adapter<Adapter.viewHolder>() {

    var Persons:ArrayList<Person>

    init {
        this.Persons = Person
    }

    class viewHolder(item:View) : RecyclerView.ViewHolder(item) {
        var id:TextView
        var name:TextView
        var age:TextView
        var delete_btn:Button

        init {
            id = item.findViewById(R.id.person_id)
            name = item.findViewById(R.id.person_name)
            age = item.findViewById(R.id.person_age)
            delete_btn = item.findViewById(R.id.delete_btn)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.person, parent, false)
        return viewHolder(layout)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var itemData = Persons[position]
        val db = SQLConnector(context)

        holder.id.text = itemData.id
        holder.name.text = itemData.name
        holder.age.text = itemData.age

        holder.delete_btn.setOnClickListener {
            db.deleteData(itemData.id!!)
            Toast.makeText(context, "Data has been deleted", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return Persons.size
    }
}
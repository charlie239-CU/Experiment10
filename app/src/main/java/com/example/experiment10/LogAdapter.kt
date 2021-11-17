package com.example.courage

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.experiment10.R

class LogAdapter(private val context: Activity, private val nameArray: MutableList<String>, private val symbolArray: MutableList<String>, private val idArray: MutableList<String>
                 , private val weightArray: MutableList<String>, private val discoveredArray: MutableList<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list_view, nameArray) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list_view, null, true)

        val name = rowView.findViewById(R.id.nameList) as TextView
        val symbol = rowView.findViewById(R.id.symbolList) as TextView
        val id = rowView.findViewById(R.id.idList) as TextView
        val weight = rowView.findViewById(R.id.weight) as TextView
        val discovered = rowView.findViewById(R.id.discovered) as TextView

        name.text = nameArray[position]
        symbol.text=symbolArray[position]
        id.text = idArray[position]
        weight.text = weightArray[position]
        discovered.text = discoveredArray[position]

        return rowView
    }
}
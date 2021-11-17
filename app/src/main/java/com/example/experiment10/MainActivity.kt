package com.example.experiment10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.courage.LogAdapter
import org.json.JSONArray
import org.json.JSONException


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var nameList:MutableList<String> = mutableListOf()
    private var weightList:MutableList<String> = mutableListOf()
    private var discoveredList:MutableList<String> = mutableListOf()
    private var symbolList:MutableList<String> = mutableListOf()
    private var idList:MutableList<String> = mutableListOf()
    private var requestQueue: RequestQueue? = null
    private lateinit var adapter: LogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView=findViewById(R.id.listview)

        requestQueue = Volley.newRequestQueue(this)
        getData()


    }
    fun getData(){
        val url = "https://mysafeinfo.com/api/data?list=elements&format=json&case=default"
        val request = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            try {
            val jsonArray = JSONArray(response.toString())
                val length=jsonArray.length()
                val list=jsonArray.getJSONObject(0)
                Log.d("jsondata",list.get("Element").toString())
           for(l in 0..length-1){
               val list=jsonArray.getJSONObject(l)
                idList.add(list.get("ID").toString())
                nameList.add(list.get("Element").toString())
                symbolList.add(list.get("Symbol").toString())
                weightList.add(list.get("Weight").toString())
                discoveredList.add(list.get("Discovered").toString())
            }
                adapter = LogAdapter(this, nameList, symbolList, idList,weightList,discoveredList)
                listView.adapter = adapter
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error -> error.printStackTrace() })
        requestQueue?.add(request)
    }
}
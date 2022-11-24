package com.bormotov_vi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bormotov_vi.databinding.ActivityMainBinding
import com.bormotov_vi.model.User
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private  var users = ArrayList <User>()
    private val URL ="https://jsonplaceholder.typicode.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()

        adapter=UserAdapter(users)

        val layoutManager=LinearLayoutManager(this)
        binding.recyclerView.layoutManager=layoutManager
        binding.recyclerView.adapter=adapter
    }


    private fun parseUser(result: String?) {
        val userJson= JSONArray(result)
        parseUserArray(userJson)
    }

    private fun parseUserArray(userJson: JSONArray) {
        for (i in 0 until userJson.length()){
            var user= User(
                userJson.getJSONObject(i).getString("name"),
                userJson.getJSONObject(i).getString("email"),
                userJson.getJSONObject(i).getJSONObject("address").getString("city")
            )
            users.add(user)
        }
    }

     private fun initList(){
        val queue= Volley.newRequestQueue(this)
        val request= StringRequest(
            Request.Method.GET,
            URL,
            {
                    result -> parseUser(result)
            },
            {

            }
        )
        queue.add(request)
    }
}
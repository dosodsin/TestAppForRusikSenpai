package com.bormotov_vi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bormotov_vi.UserAdapter.UserActionListener
import com.bormotov_vi.databinding.ActivityMainBinding
import com.bormotov_vi.model.user.UsersItem
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var adapter: UserAdapter? = null
    private val URL = "https://jsonplaceholder.typicode.com/users"
    private var client: OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val stateClickListener: UserActionListener = object : UserActionListener {
            override fun onUserClickListener(user: UsersItem, position: Int) {
                val intent = Intent(this@MainActivity, UsersPostsAndAlbumsActivity::class.java)
                intent.putExtra("userId", user.id)
                startActivity(intent)
            }

        }

        getUsers {
            runOnUiThread {
                adapter = UserAdapter(it, stateClickListener)
                binding!!.recyclerView.adapter = adapter
            }
        }

    }

    private fun getUsers(callback: (List<UsersItem>) -> Unit) {
        val request = Request.Builder()
            .url(URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val body = response.body?.string()
                    val gson = GsonBuilder().create()
                    val result = gson.fromJson(body, Array<UsersItem>::class.java).toList()
                    callback(result)
                }

            }
        })
    }
}
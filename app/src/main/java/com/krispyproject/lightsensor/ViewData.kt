package com.krispyproject.lightsensor

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krispyproject.lightsensor.model.User
import kotlinx.android.synthetic.main.activity_view_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class ViewData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        val retro = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https:khogyrvwcc.execute-api.ap-south-1.amazonaws.com/sensor/")
            .build()
        val AwsGetApi = retro.create(awsGetApi::class.java)
        val myCall: Call<List<User>> = AwsGetApi.getUsers()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)

        myCall.enqueue(object : Callback<List<User>> {
            override fun onResponse(p0: Call<List<User>>, p1: Response<List<User>>) {
                if (p1.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@ViewData)
                        adapter = PostAdapter(p1.body()!! as MutableList<User>)
                    }
                }
            }

            override fun onFailure(p0: Call<List<User>>, p1: Throwable) {
                Log.e("ERROR", p1.message.toString())
            }

        })
        



    }
}
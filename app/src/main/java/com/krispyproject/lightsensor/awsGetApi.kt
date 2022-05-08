package com.krispyproject.lightsensor

import com.krispyproject.lightsensor.model.User
import retrofit2.Call
import retrofit2.http.GET

interface awsGetApi {
    @GET("sensor-data/")
    fun getUsers(): Call<List<User>>

}
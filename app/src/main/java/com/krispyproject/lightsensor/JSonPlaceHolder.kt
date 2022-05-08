package com.krispyproject.lightsensor

import com.krispyproject.lightsensor.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface JSonPlaceHolder {
    @POST("sensor-data/{id}")
    fun senduserdata(
        @Body user: User
    ): Call<User>

}
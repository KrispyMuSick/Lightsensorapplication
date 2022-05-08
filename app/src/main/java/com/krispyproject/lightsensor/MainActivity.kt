package com.krispyproject.lightsensor

import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.krispyproject.lightsensor.model.User
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


var x: Float = 10.1f


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var brightness: Sensor? = null
    private lateinit var text: TextView
    private lateinit var pb: CircularProgressBar

    fun jolly(u: Float): Unit {
        x = u
    }




    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        text = findViewById(R.id.tv_text)
        pb = findViewById(R.id.circularProgressBar)


        setUpSensorStuff()


    }
    fun damnpost (a: String, b: String): Unit {


        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://khogyrvwcc.execute-api.ap-south-1.amazonaws.com/sensor/")
            .build()

        val JSonPlaceHolder = retrofitbuilder.create(JSonPlaceHolder::class.java)
        val userPost = User(a, b)
        val call = JSonPlaceHolder.senduserdata(userPost)



        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                textView3.text = t.message.toString()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                textView3.text = response.code().toString()
            }
        })
    }

    private fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)


    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]
            jolly(light1)




            text.text = "Luminosity: $light1 lx\n${brightness(light1)} "

            pb.setProgressWithAnimation(light1)
        }
    }

    private fun brightness(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "Pitch black"
            in 1..10 -> "Dark"
            in 11..50 -> "Grey"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "Incredibly bright"
            else -> "This light will blind you"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
    }


    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    fun upldonClk(view: View) {

        var z = "progress"
        var i = 2

        val mBtn = findViewById<Button>(R.id.button) as Button
        mBtn.setOnClickListener {
            z = x.toString()
            Toast.makeText(
                this,
                z + "\t lx is Updated in the Cloud",
                Toast.LENGTH_LONG
            ).show();
            damnpost(i++.toString(),z)

        }
    }

    fun newData(view: View) {
        val intent = Intent(this, ViewData::class.java)
        startActivity(intent)
    }


}




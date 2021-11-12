package com.hasanbektas.cookeggs


import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.LinearLayout
import com.hasanbektas.cookeggs.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {



    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var zaman = intent.getIntExtra("time", 1)

        var colorbackgrount: LinearLayout? = findViewById<LinearLayout>(R.id.linearbackground)

        object : CountDownTimer((zaman * 60 * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {

                var currentMinute = millisUntilFinished / 60000
                var currentSecond = (millisUntilFinished % 60000) / 1000

                when(currentMinute.toInt())
                {
                    3 -> {
                        TimerBackgroundColor(getResources().getColor(R.color.background2),colorbackgrount)
                    }
                    2 -> {
                        TimerBackgroundColor(getResources().getColor(R.color.background3),colorbackgrount)
                    }
                    1 -> {
                        TimerBackgroundColor(getResources().getColor(R.color.background4),colorbackgrount)
                    }
                }

                binding.second.setText(" $currentSecond")
                binding.minute.setText(" $currentMinute")
            }
            override fun onFinish() {
                val mediaplayer = MediaPlayer.create(this@MainActivity2, R.raw.alarmmp3)
                mediaplayer.start()
            }
        }.start()

    }

    fun TimerBackgroundColor (color:Int, colorLayout: LinearLayout?)
    {
       if (colorLayout != null){
           colorLayout.setBackgroundColor(color)
       }
    }
}
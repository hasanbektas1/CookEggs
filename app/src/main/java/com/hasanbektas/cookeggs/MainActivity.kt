package com.hasanbektas.cookeggs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hasanbektas.cookeggs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun az(view: View){

        val intent= Intent(applicationContext,MainActivity2::class.java)

        intent.putExtra("time",5)
        startActivity(intent)

    }
    fun orta(view: View){

        val intent= Intent(applicationContext,MainActivity2::class.java)

        intent.putExtra("time",8)
        startActivity(intent)

    }
    fun cok(view: View){

        val intent= Intent(applicationContext,MainActivity2::class.java)

        intent.putExtra("time",12)

        startActivity(intent)

    }
}
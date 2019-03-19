package com.example.eemitest

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val resultat = findViewById<TextView>(R.id.compteur)
        val buttonPlus = findViewById<Button>(R.id.bouttonplus)

        buttonPlus.setOnClickListener {view ->
            var inputUrl = findViewById<EditText>(R.id.input)

            println(inputUrl.text.toString())


            val t = Thread(Runnable {
                val u = URL(inputUrl.text.toString())
                val c = u.openConnection()
                val input = c.getInputStream()
                val reader = InputStreamReader(input)
                val buffer = BufferedReader(reader)



                val s = buffer.readText()

                val firstTag = s.indexOf("<title>")+7
                val lastTag = s.indexOf("</title>")

                val bytesNumber = s.length


                println("/* ---------------------------------- BYTES -----------------------*/")
                println(bytesNumber)

                val sub = if (lastTag != -1 ) {
                    s.substring(firstTag, lastTag)
                } else {
                    bytesNumber.toString()
                }

                println(firstTag)
                println(lastTag)




                runOnUiThread {
                    resultat.setText(sub)
                }

            })

            t.start()

        }
    }

    fun sendMessage(view: View){
        val intent = Intent(this, Main2Activity::class.java)
        startActivity(intent)
    }


}

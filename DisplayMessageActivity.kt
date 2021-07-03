package com.example.bid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message  = intent.getStringExtra(EXTRA_MESSAGE)


        findViewById<TextView>(R.id.textView2).apply {
            text = message
        }

    }
}

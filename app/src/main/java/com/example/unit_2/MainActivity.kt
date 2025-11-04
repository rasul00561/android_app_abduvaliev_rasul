package com.example.unit_2
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)

        var isHello = true

        button.setOnClickListener {
            if (isHello) {
                textView.text = "Button clicked!"
            } else {
                textView.text = "Hello!"
            }
            isHello = !isHello
        }
    }
}
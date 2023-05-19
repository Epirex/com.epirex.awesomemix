package com.epirex.awesomemix

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlay: Button = findViewById(R.id.buttonPlay)
        val buttonPause: Button = findViewById(R.id.buttonPause)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.drawable.awesomemixstatitc4)

        awesomeStart(buttonPlay, buttonPause)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun awesomeStart(buttonPlay: Button, buttonPause: Button) {
        val url = "https://stream.zeno.fm/0c5xvhqhedsvv"
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepareAsync()
            buttonPlay.setOnClickListener {
                mediaPlayer.start()
                Toast.makeText(this, "Reproduciendo radio", Toast.LENGTH_SHORT).show()
            }

        buttonPause.setOnClickListener {
            mediaPlayer.pause()
            Toast.makeText(this, "En Pausa...", Toast.LENGTH_SHORT).show()
        }
    }
}
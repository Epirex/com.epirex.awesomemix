package com.epirex.awesomemix

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlay = findViewById<Button>(R.id.buttonPlay)
        val buttonPause = findViewById<Button>(R.id.buttonPause)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.drawable.cassete)

        iniciarReproductor(buttonPlay, buttonPause)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun iniciarReproductor(buttonPlay: Button, buttonPause: Button) {
        val url = "https://stream.zeno.fm/0c5xvhqhedsvv"
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepareAsync()

        buttonPlay.setOnClickListener {
            mediaPlayer.start()
            Toast.makeText(this, "¡Rock and roll!", Toast.LENGTH_SHORT).show()
        }

        buttonPause.setOnClickListener {
            mediaPlayer.pause()
            Toast.makeText(this, "En pausa...", Toast.LENGTH_SHORT).show()
        }
    }
}
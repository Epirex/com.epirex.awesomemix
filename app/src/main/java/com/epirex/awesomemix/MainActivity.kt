package com.epirex.awesomemix

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var isPlaying: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlayer = findViewById<FloatingActionButton>(R.id.buttonPlayer)
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(R.drawable.cassete)

        iniciarReproductor(buttonPlayer)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    private fun iniciarReproductor(buttonPlayer: FloatingActionButton) {
        val url = "https://stream.zeno.fm/0c5xvhqhedsvv"
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepareAsync()

        buttonPlayer.setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                Toast.makeText(this, "En pausa...", Toast.LENGTH_SHORT).show()
                buttonPlayer.setImageResource(R.drawable.ic_play)
            } else {
                mediaPlayer.start()
                Toast.makeText(this, "¡Rock and roll!", Toast.LENGTH_SHORT).show()
                buttonPlayer.setImageResource(R.drawable.ic_pause)
            }
            isPlaying = !isPlaying
        }
    }
}
package com.epirex.awesomemix

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var isPlaying: Boolean = false
    private lateinit var animationView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlayer = findViewById<FloatingActionButton>(R.id.buttonPlayer)
        animationView = findViewById(R.id.animationView)

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
                animationView.pauseAnimation()
                Toast.makeText(this, "En pausa...", Toast.LENGTH_SHORT).show()
                buttonPlayer.setImageResource(R.drawable.ic_play)
            } else {
                mediaPlayer.start()
                animationView.playAnimation()
                Toast.makeText(this, "¡Rock and roll!", Toast.LENGTH_SHORT).show()
                buttonPlayer.setImageResource(R.drawable.ic_pause)
            }
            isPlaying = !isPlaying
        }
    }
}

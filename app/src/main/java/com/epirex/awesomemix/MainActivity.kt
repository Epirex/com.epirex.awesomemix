package com.epirex.awesomemix

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlay: Button = findViewById(R.id.buttonPlay)
        val buttonPause: Button = findViewById(R.id.buttonPause)

        awesomeStart(buttonPlay, buttonPause)
    }

    private fun awesomeStart(buttonPlay: Button, buttonPause: Button) {
        val url = "https://stream.zeno.fm/0c5xvhqhedsvv"
        mediaPlayer = MediaPlayer()

        mediaPlayer.setDataSource(url)
        mediaPlayer.setOnPreparedListener {
            buttonPlay.setOnClickListener {
                val videoView = findViewById<VideoView>(R.id.videoView)
                val videoPath = "android.resource://" + packageName + "/" + R.raw.cassette
                val uri = Uri.parse(videoPath)
                videoView.setVideoURI(uri)
                videoView.setOnPreparedListener { mediaPlayer ->
                    mediaPlayer.isLooping = true
                }
                mediaPlayer.start()
                videoView.start()
                Toast.makeText(this, "Reproduciendo radio", Toast.LENGTH_SHORT).show()
            }

            buttonPause.setOnClickListener {
                val videoView = findViewById<VideoView>(R.id.videoView)
                val videoPath = "android.resource://" + packageName + "/" + R.raw.cassette
                val uri = Uri.parse(videoPath)
                videoView.setVideoURI(uri)
                mediaPlayer.pause()
                videoView.stopPlayback()
                Toast.makeText(this, "En Pausa...", Toast.LENGTH_SHORT).show()
            }
        }

        mediaPlayer.prepareAsync()
    }
}
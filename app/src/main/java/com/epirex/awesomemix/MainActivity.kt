package com.epirex.awesomemix

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay = findViewById(R.id.buttonPlay)
        buttonPause = findViewById(R.id.buttonPause)

        awesomeStart()
    }

    private fun awesomeStart() {
        val url =
            "https://stream.zeno.fm/0c5xvhqhedsvv"

        mediaPlayer = MediaPlayer()

        mediaPlayer.setDataSource(url)
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {

            setOnClickListeners(this)
        }
    }

    private fun setOnClickListeners(context: Context) {
        buttonPlay.setOnClickListener {
            val videoView = findViewById<VideoView>(R.id.videoView)
            val videoPath = "android.resource://" + packageName + "/" + R.raw.cassette
            val uri = Uri.parse(videoPath)
            videoView.setVideoURI(uri)
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.setLooping(true)
            }
            mediaPlayer.start()
            videoView.start()
            Toast.makeText(context, "Reproduciendo radio", Toast.LENGTH_SHORT)
                .show()
        }

        buttonPause.setOnClickListener {
            val videoView = findViewById<VideoView>(R.id.videoView)
            val videoPath = "android.resource://" + packageName + "/" + R.raw.cassette
            val uri = Uri.parse(videoPath)
            videoView.setVideoURI(uri)
            mediaPlayer.pause()
            videoView.stopPlayback()
            Toast.makeText(context, "En Pausa...", Toast.LENGTH_SHORT).show()
        }
    }
}
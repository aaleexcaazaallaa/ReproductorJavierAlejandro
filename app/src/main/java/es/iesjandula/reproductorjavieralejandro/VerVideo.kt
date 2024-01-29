package es.iesjandula.reproductorjavieralejandro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.VideoView

class VerVideo : AppCompatActivity() {
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_video)

        videoView = findViewById(R.id.videoCompleto)

        var direccion = intent.getStringExtra("VIDEO_URI")

        if(!direccion.isNullOrEmpty())
        {
            val url = Uri.parse(direccion)
            videoView.setVideoURI(url)
            videoView.start()
            videoView.setOnCompletionListener {
                finish()
            }
        }
        else
        {
            Toast.makeText(this, "No hay videos", Toast.LENGTH_SHORT).show()
        }
    }
}
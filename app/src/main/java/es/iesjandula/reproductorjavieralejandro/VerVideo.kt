package es.iesjandula.reproductorjavieralejandro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.VideoView

class VerVideo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_video)

        var video: VideoView = findViewById(R.id.videoCompleto)
        var direccion = intent.getStringExtra("VIDEO_URI")

        if(!direccion.isNullOrEmpty())
        {
            val url = Uri.parse(direccion)
            video.setVideoURI(url)
            video.start()
            video.setOnCompletionListener {
                finish()
            }
        }
        else
        {
            Toast.makeText(this, "No hay videos", Toast.LENGTH_SHORT).show()
        }
    }
}
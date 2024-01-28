package es.iesjandula.reproductorjavieralejandro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: VideoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Pedimos permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 123)
        }

        initView()

        initRecyclerView()
    }

    fun grabarVideo (view: View) {
        val REQUEST_VIDEO_CAPTURE = 1
        //Vamos a captura el video, mientras que se captura dicho video va a grabar en un resolveActivity, y ese lo que va a hacer
        //guardar el video
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also {
                video -> video.resolveActivity(packageManager)?.also {
            startActivityForResult(video, REQUEST_VIDEO_CAPTURE)
        }
        }

    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VideoAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
    }
}
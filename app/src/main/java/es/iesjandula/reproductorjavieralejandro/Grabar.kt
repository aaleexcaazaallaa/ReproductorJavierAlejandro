package es.iesjandula.reproductorjavieralejandro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Grabar : AppCompatActivity() {

    private var REQUEST_VIDEO_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grabar)

        //Pedimos permisos
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 123)
        }

        grabarVideo()
    }

    fun grabarVideo ()
    {
        //Vamos a captura el video, mientras que se captura dicho video va a grabar en un resolveActivity, y ese lo que va a hacer
        //guardar el video
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also {
                video -> video.resolveActivity(packageManager)?.also {
                    startActivityForResult(video, REQUEST_VIDEO_CAPTURE)
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            val videoUrl: Uri? = data?.data
            val videoList: ArrayList<String> = intent.getStringArrayListExtra("LIST_URI") ?: ArrayList()
            videoUrl?.let {
                if (!videoList.contains(it.toString())) {
                    // Asegura que el video no esté ya en la lista
                    videoList.add(it.toString())
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("VIDEO_URI", it.toString())
                intent.putExtra("LIST_URI", videoList)
                startActivity(intent)
            }
        }
        else
        {
            val videoList: ArrayList<String> = intent.getStringArrayListExtra("LIST_URI") ?: ArrayList()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("LIST_URI", videoList)
            startActivity(intent)
            Toast.makeText(this, "Grabacion cancelada", Toast.LENGTH_SHORT).show()
        }
    }

    //mostrar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_grabar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Damos funcionalidad a las opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Volver -> {
                val intentVolver = Intent(this, MainActivity::class.java)
                startActivity(intentVolver)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
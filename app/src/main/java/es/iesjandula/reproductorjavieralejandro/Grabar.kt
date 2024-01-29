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
import android.view.View
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
            val videoUri: Uri? = data?.data
            videoUri?.let {
                val listUri: ArrayList<String> = intent.getStringArrayListExtra("LIST_URI") ?: ArrayList()

                if (!listUri.contains(it.toString())) {
                    // Asegura que el video no esté ya en la lista
                    listUri.add(it.toString())
                }
                val intentReproducir = Intent(this, MainActivity::class.java)
                intentReproducir.putExtra("VIDEO_URI", it.toString())
                intentReproducir.putExtra("LIST_URI", it.toString())
                startActivity(intentReproducir)
            }
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
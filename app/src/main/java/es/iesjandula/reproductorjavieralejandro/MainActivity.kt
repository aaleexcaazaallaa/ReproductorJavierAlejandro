package es.iesjandula.reproductorjavieralejandro

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity()
{
    private var url: String? = null
    lateinit var videoList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = intent.getStringExtra("VIDEO_URI")
        videoList = intent.getStringArrayListExtra("LIST_URI")?: ArrayList()
    }

    //mostrar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Damos funcionalidad a las opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Grabar -> {
                val intent = Intent(this, Grabar::class.java)
                intent.putExtra("LIST_URI", videoList)
                startActivity(intent)
                return true
            }
            R.id.Reproducir -> {
                if (!url.isNullOrEmpty() || !videoList.isEmpty()) {
                    val intent = Intent(this, Grabaciones::class.java)

                    if (url != null && !videoList.contains(url!!)) {
                        // Asegura que el video no esté ya en la lista
                        videoList.add(url!!)
                    }

                    // Pasa la lista de URIs a través del Intent y inicia la actividad
                    intent.putExtra("LIST_URI", videoList)
                    startActivity(intent)
                } else {
                    // Muestra un mensaje si no se ha grabado ningún video
                    Toast.makeText(this, "No se ha grabado ningún video", Toast.LENGTH_SHORT).show()
                }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
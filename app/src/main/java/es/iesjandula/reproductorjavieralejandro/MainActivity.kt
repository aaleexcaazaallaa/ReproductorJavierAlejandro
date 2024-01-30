package es.iesjandula.reproductorjavieralejandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

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
                val intentGrabar = Intent(this, Grabar::class.java)
                intentGrabar.putExtra("LIST_URI", videoList)
                startActivity(intentGrabar)
                return true
            }
            R.id.Reproducir -> {
                if (!url.isNullOrEmpty() || !videoList.isEmpty()) {
                    val intentReproducir = Intent(this, Grabaciones::class.java)

                    if (url != null && !videoList.contains(url!!)) {
                        // Asegura que el video no esté ya en la lista
                        videoList.add(url!!)
                    }

                    // Pasa la lista de URIs a través del Intent y inicia la actividad
                    intentReproducir.putExtra("LIST_URI", videoList)
                    intentReproducir.putExtra("VIDEO_URI",url)
                    startActivity(intentReproducir)
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
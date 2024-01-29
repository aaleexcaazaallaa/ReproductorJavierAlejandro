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
    private var comprobar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = intent.getStringExtra("VIDEO_URI")

        if(!comprobar)
        {
            videoList = intent.getStringArrayListExtra("LIST_URI")?: ArrayList<String>()
        }
        else
        {
            Toast.makeText(this, "Lista vacia", Toast.LENGTH_SHORT).show()
        }

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
            }
            R.id.Reproducir -> {
                if(!url.isNullOrEmpty())
                {
                    val intentReproducir = Intent(this, Grabaciones::class.java)
                    intentReproducir.putExtra("VIDEO_URI", url)
                    if (url != null && !videoList.contains(url!!))
                    {
                        videoList.add(url!!)
                    }
                    intentReproducir.putExtra("LIST_URI", videoList)
                    startActivity(intentReproducir)
                }
                else
                {
                      Toast.makeText(this, "No hay videos", Toast.LENGTH_SHORT).show()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
package es.iesjandula.reproductorjavieralejandro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Grabaciones : AppCompatActivity()
{
    private lateinit var recyclerView: RecyclerView
    private var adapter: VideoAdapter? = null
    lateinit var videoList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grabaciones)

        initView()

        initRecyclerView()
    }

    //mostrar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_grabaciones, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Damos funcionalidad a las opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Grabar -> {
                val intentGrabar = Intent(this, Grabar::class.java)
                startActivity(intentGrabar)
            }
            R.id.Volver -> {
                val intentVolver = Intent(this, MainActivity::class.java)
                startActivity(intentVolver)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView()
    {
        val url: ArrayList<Uri> = parsearListaUris()

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VideoAdapter(this, url)
        recyclerView.adapter = adapter
    }

    private fun initView()
    {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun parsearListaUris(): ArrayList<Uri> {
        this.videoList = intent.getStringArrayListExtra("LIST_URI") as ArrayList<String>
        val listaUri: ArrayList<Uri> = ArrayList()

        // Itera sobre la lista de strings y convierte cada uno en un objeto Uri
        for (uriString in videoList) {
            try {
                val uri: Uri = Uri.parse(uriString)
                listaUri.add(uri)
            } catch (e: Exception) {
                Toast.makeText(this, "No se ha podido convertir la URI a Objeto", Toast.LENGTH_SHORT).show()
            }
        }
        return listaUri
    }



}
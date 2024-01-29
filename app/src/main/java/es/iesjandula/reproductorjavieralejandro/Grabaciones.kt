package es.iesjandula.reproductorjavieralejandro

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    private fun initRecyclerView()
    {
        val videoList: ArrayList<Uri> = parseUris()
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = VideoAdapter(this, videoList)
        recyclerView.adapter = adapter
    }

    private fun initView()
    {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun parseUris(): ArrayList<Uri>
    {
        this.videoList = intent.getStringArrayListExtra("LIST_URI") as ArrayList<String>
        val urlList: ArrayList<Uri> = ArrayList()

        for (urlString in videoList)
        {
            try
            {
                val url: Uri = Uri.parse(urlString)
                urlList.add(url)
            }
            catch (e: Exception)
            {
                Toast.makeText(this, "No se puede pasar a objeto", Toast.LENGTH_SHORT).show()
            }
        }
        return urlList
    }

    //mostrar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_grabaciones, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Damos funcionalidad a las opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Volver -> {
                val intentVolver = Intent(this, MainActivity::class.java)
                intentVolver.putExtra("LIST_URI", videoList)
                startActivity(intentVolver)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
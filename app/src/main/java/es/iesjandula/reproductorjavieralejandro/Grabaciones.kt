package es.iesjandula.reproductorjavieralejandro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Grabaciones : AppCompatActivity()
{

    private lateinit var btnVolver: Button

    private lateinit var recyclerView: RecyclerView
    private var adapter: VideoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grabaciones)

        initView()

        initRecyclerView()
        btnVolver.setOnClickListener{
            val intentVolver = Intent(this, MainActivity::class.java)
            startActivity(intentVolver)
        }
    }

    private fun initRecyclerView()
    {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VideoAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView()
    {
        recyclerView = findViewById(R.id.recyclerView)
        btnVolver = findViewById(R.id.btnVolver)
    }
}
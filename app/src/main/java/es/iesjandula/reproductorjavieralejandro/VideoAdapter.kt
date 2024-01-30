package es.iesjandula.reproductorjavieralejandro

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter(private val context: Context, private val listaUri: ArrayList<Uri>) :RecyclerView.Adapter<VideoAdapter.VideoViewHolder>()
{
    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val btnVer: Button = view.findViewById(R.id.btnVer)
        val tvUrl: TextView = view.findViewById(R.id.tvUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val uri = listaUri[position]


        holder.btnVer.setOnClickListener {
            val verVideo = Intent(context, VerVideo::class.java)
            verVideo.putExtra("VIDEO_URI", uri.toString())
            context.startActivity(verVideo)
        }

        holder.tvUrl.text = uri.toString()
    }

    override fun getItemCount(): Int {
        return listaUri.size
    }
}
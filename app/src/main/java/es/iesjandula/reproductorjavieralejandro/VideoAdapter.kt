package es.iesjandula.reproductorjavieralejandro

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class VideoAdapter : RecyclerView.Adapter<VideoViewHolder>()
{
    private var videoList: List<Video> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(items: List<Video>)
    {
        this.videoList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VideoViewHolder(layoutInflater.inflate(R.layout.card_item, parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int)
    {
        val std = videoList[position]
        holder.bindView(std)
    }

    override fun getItemCount(): Int = videoList.size
}
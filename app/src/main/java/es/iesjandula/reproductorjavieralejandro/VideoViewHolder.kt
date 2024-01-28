package es.iesjandula.reproductorjavieralejandro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoViewHolder(var view: View) : RecyclerView.ViewHolder(view)
{
    private var url = view.findViewById<TextView>(R.id.tvUrl)

    fun bindView(video : Video)
    {
        url.text = video.url
    }
}
package es.iesjandula.reproductorjavieralejandro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VideoViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
    private var url = view.findViewById<TextView>(R.id.tvUrl)
    private var nombre = view.findViewById<TextView>(R.id.tvNombre)

    fun bindView(video : Video) {
        url.text = video.url
        nombre.text = video.nombre
    }
}
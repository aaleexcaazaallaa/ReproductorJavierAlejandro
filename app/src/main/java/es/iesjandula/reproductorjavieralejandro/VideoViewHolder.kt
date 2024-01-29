package es.iesjandula.reproductorjavieralejandro

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView

class VideoViewHolder(var view: View) : RecyclerView.ViewHolder(view)
{
    var url = view.findViewById<TextView>(R.id.tvUrl)
    var btnVer = view.findViewById<Button>(R.id.btnVer)
}
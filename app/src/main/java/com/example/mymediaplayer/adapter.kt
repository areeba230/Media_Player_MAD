package com.example.mymediaplayer


import android.annotation.SuppressLint
import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class adapter (private val context:Activity, private val arrayList: ArrayList<data>) :
    ArrayAdapter<data>(context,R.layout.customlayout,arrayList) {


    var flag:Boolean =true
    lateinit var user:data

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflator : LayoutInflater = LayoutInflater.from(context)
        val view :View = inflator.inflate(R.layout.customlayout,null)

        val song_title : TextView = view.findViewById(R.id.music_title_text)
        val icon_image:ImageView = view.findViewById(R.id.song_icon)


        icon_image.setImageResource(arrayList[position].icon)
        song_title.setText(arrayList[position].text_name)
         user = arrayList.get(position);

        return view
    }
    }




private fun TextView.setText(textName: TextView) {

}

private fun ImageView.setImageResource(imgPlay: ImageView) {

}




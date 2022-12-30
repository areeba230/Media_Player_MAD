package com.example.mymediaplayer

class data(var text_name: String, var icon: Int,var songa:Int) {

   private var song:Int =0


    fun getsong(): Int {
        return song
    }

    fun setSong(song: Int) {
        this.song = song
    }



}
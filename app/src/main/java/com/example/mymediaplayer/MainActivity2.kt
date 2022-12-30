package com.example.mymediaplayer


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mymediaplayer.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var musicArrayList : ArrayList<data>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
       // binding=MainActivity2.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main2)  //check if everything is normal




        val name = arrayOf(
                    "calm down.mp3",
                    "dekha_ek_khuab.mp3",
                    "dill_diya_galla.mp3",
                    "jeda_nasha.mp3",
                    "kahani_suno.mp3",
                     "kinna_sona.mp3",
                   " made_you_look.mp3",
                    "me_agar_kahu.mp3",
                    "peeche_hut.mp3",
                   " thumkeshwari.mp3"

        )
        val img = intArrayOf(
            R.drawable.calm_down,R.drawable.dekha_ek_khuab,R.drawable.dill_diya_galla,R.drawable.jeda_nasha,R.drawable.kahani_suno,R.drawable.kinna_sona,R.drawable.made_you_look,R.drawable.me_agar_kahu,R.drawable.peeche_hut,R.drawable.thumkeshwari)

        val songa = intArrayOf(R.raw.calm_down,R.raw.dekha_ek_khuab,R.raw.dill_diya_galla,R.raw.jeda_nasha,R.raw.kahai_suno,R.raw.kinna_sona,R.raw.made_you_look,R.raw.me_agar_kahu,R.raw.peeche_hut,R.raw.thumkeshwari)


        musicArrayList = ArrayList()

        for(i in name.indices){
            val song  = data(name[i],img[i],songa[i])
            musicArrayList.add(song)
        }

        binding.listView.isClickable = true
        binding.listView.adapter = adapter(this,musicArrayList)
        binding.listView.setOnItemClickListener { parent, view, position, id ->

           val name = name[position]
            val image = img[position]
            val song = songa[position]

            val i = Intent(this,MainActivity3::class.java)
            i.putExtra("name",name)
            i.putExtra("img",image)
            i.putExtra("song",song)
            startActivity(i)
        }
    }



}
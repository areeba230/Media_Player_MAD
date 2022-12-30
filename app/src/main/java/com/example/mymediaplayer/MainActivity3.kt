package com.example.mymediaplayer

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mymediaplayer.databinding.ActivityMain3Binding


class MainActivity3 : AppCompatActivity() {

    private lateinit var binding : ActivityMain3Binding
    lateinit var image1  :ImageView
    lateinit var text :TextView
    lateinit var btn_play :ImageButton
    lateinit var btn_next :Button
    lateinit var btn_pre :Button
    lateinit var seekbar :SeekBar
   // lateinit var songlist : ArrayList<data>
    lateinit var mp : MediaPlayer
    private var totaltime:Int = 0
    var flag:Boolean = false
    lateinit var elapsedTimeLabel: TextView
    lateinit var remainingTimeLabel: TextView
    lateinit var positionBar:SeekBar
    //////
      var img2 : Int=0
      var song2 :Int=0
     lateinit var name2:String
      var firstsong :Int  =R.raw.calm_down
    var lastsong :Int = R.raw.thumkeshwari
    ////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main3)
        setContentView(binding.root)

        mp = MediaPlayer()
        btn_next = findViewById(R.id.btn_next)
        btn_pre = findViewById(R.id.btn_prev)
        seekbar = findViewById(R.id.songSeekBar)
        positionBar = findViewById(R.id.positionBar)
        elapsedTimeLabel = findViewById(R.id.elapsedTimeLabel)
        remainingTimeLabel= findViewById(R.id.remainingTimeLabel)

        if(mp!=null){

            mp.start()
            mp.release()
        }

       val name = intent.getStringExtra("name")
        val image = intent.getIntExtra("img",R.drawable.calm_down)
        val song = intent.getIntExtra("song",R.drawable.calm_down)
        if (name != null) {
            name2=name
        }
        img2=image
        song2=song



        mp = MediaPlayer.create(this,song)
        mp.setVolume(0.5f,0.5f)
        totaltime = mp.duration
        mp.start()
        binding.txtSongName.text = name
        binding.iconImage.setImageResource(image)


        seekbar.setOnSeekBarChangeListener(
            object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if(fromUser){
                        val volume = progress/100.0F
                        mp.setVolume(volume,volume)
                    } }
                override fun onStartTrackingTouch(p0: SeekBar?) {}
                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        // Position Bar
        positionBar.max = totaltime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()

        mp.setOnCompletionListener(object :MediaPlayer.OnCompletionListener{
            override fun onCompletion(mp: MediaPlayer?) {
                btn_next.performClick()
            }

        })
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            // Update positionBar
            positionBar.progress = currentPosition

            // Update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totaltime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"
        }

    }


    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    fun gotoactivity2(view: View){

        mp.stop()
        val i :Intent = Intent(this,MainActivity2::class.java)
        startActivity(i)
    }

    fun playSong(view: View){
        btn_play = findViewById(R.id.btn_play)
        image1 = findViewById(R.id.icon_image)

       if(mp.isPlaying){
           btn_play.setImageResource(R.drawable.play)
           mp.pause()
       }
        else{
           btn_play.setImageResource(R.drawable.stop)
           mp.start()

           var anim :TranslateAnimation = TranslateAnimation(-25F,25F,-25F,25F)
           anim.setInterpolator(AccelerateInterpolator())
           anim.duration = 600
           anim.isFillEnabled = true
           anim.fillAfter = true
           anim.repeatMode = Animation.REVERSE
           anim.repeatCount = 1
           image1.startAnimation(anim)
       }
    }

    fun nextSong(view:View){
        binding = ActivityMain3Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main3)
        setContentView(binding.root)
        image1 = findViewById(R.id.icon_image)
        if(mp.isPlaying){
            mp.stop()
        }
        setSongPosition(inc=true)
        mp = MediaPlayer.create(this,song2)
        mp.setVolume(0.5f,0.5f)
        totaltime = mp.duration
        mp.start()
        var song_name:String = getText(song2).toString().substring(8)
        var image_name:String = getText(song2).toString().substring(8,getText(song2).toString().indexOf(".mp3"))
        binding.txtSongName.text =image_name
        var id = ("R.drawable."+image_name)
        binding.iconImage.setImageResource(R.drawable.pngtree_black_gold_music_notes_round_border_png_image_2898483)



        imageRotator(image1,360F)
    }

    fun preSong(view:View){
        image1 = findViewById(R.id.icon_image)

        if(mp.isPlaying){
            mp.stop()
        }
        binding = ActivityMain3Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main3)
        setContentView(binding.root)


        setSongPosition(inc=false)
        mp = MediaPlayer.create(this,song2)
        mp.setVolume(0.5f,0.5f)
        totaltime=mp.duration
        mp.start()

        var song_name:String = getText(song2).toString().substring(8)
        var image_name:String = getText(song2).toString().substring(8,getText(song2).toString().indexOf(".mp3"))
        binding.txtSongName.text =image_name
        var id = "R.drawable."+image_name
        binding.iconImage.setImageResource(R.drawable.pngtree_black_gold_music_notes_round_border_png_image_2898483)

        imageRotator(image1,-360F)
    }

    fun imageRotator(view:View,degree:Float){
        image1 = findViewById(R.id.icon_image)

        val objectAnimator : ObjectAnimator= ObjectAnimator.ofFloat(image1,"rotation",0F,degree)
        objectAnimator.setDuration(1000)
        val animatorset:AnimatorSet = AnimatorSet()
        animatorset.playTogether(objectAnimator)
        animatorset.start()

    }

    private fun setSongPosition(inc :Boolean){

        if(inc){

            if(song2==lastsong){
                song2=firstsong
            }else{
                ++song2
            }
        }else{
            if(song2==firstsong){
                song2=lastsong
            }else{
                --song2
            }
        }
    }


}
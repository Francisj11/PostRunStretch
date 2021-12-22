package com.example.postrunstretch
//sound
import android.media.AudioManager
import android.media.SoundPool

//sound
import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.ImageView
import com.example.postrunstretch.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private var soundPool: SoundPool? = null
    private val soundId = 1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        soundPool = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        soundPool!!.load(baseContext, R.raw.shortbing, 1)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView: TextView = findViewById(R.id.text)
        val textView2: TextView = findViewById(R.id.textView2)

        textView.text = "Lets GO!"

        val button: Button = findViewById(R.id.button)
        val hipFlexor1: ImageView = findViewById(R.id.hipFlexor1)

        var listOfStretchImages = arrayOf(
            R.id.hipFlexor1,
            R.id.hipFlexor2,
            R.id.thighStretch1,
            R.id.thighStretch2,
            R.id.hamstringStretch1,
            R.id.hamstringStretch2,
            R.id.iliotibialBandStretch1,
            R.id.iliotibialBandStretch2,
            R.id.calfStretch1,
            R.id.calfStretch2,
            R.id.upperBodyStretch1,
            R.id.upperBodyStretch2,
            R.id.lowerBackStretch1,
            R.id.lowerBackStretch2,
            R.id.gluteStretch1,
            R.id.gluteStretch2)


        button.setOnClickListener()
        {
            // Make button invisible and first image visible
            button.visibility = View.INVISIBLE
            val id1 = listOfStretchImages[0]
            val image1 = findViewById<ImageView>(id1)
            image1.visibility = View.VISIBLE

            soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)

            // Start a timer
            object : CountDownTimer(2000, 1000)
            {
                var iterations = 0
                    override fun onTick(millisUntilFinished: Long)
                    {
                        textView.text = (millisUntilFinished / 1000).toString() + "s."
                    }

                    override fun onFinish()
                    {
                        // Play sound
                        soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)

                        // display count
                        textView2.text = "Stretch no. " + (iterations+1).toString() + " out of " + listOfStretchImages.size + "."

                        // Swap the visibility of current image with next
                        val id1 = listOfStretchImages[iterations]
                        val id2 = listOfStretchImages[iterations+1]
                        val image1 = findViewById<ImageView>(id1)
                        val image2 = findViewById<ImageView>(id2)
                        image1.visibility = View.INVISIBLE
                        image2.visibility = View.VISIBLE
                        iterations++

                        if (iterations < (listOfStretchImages.size - 1))
                        {
                            this.start()
                        }
                        else
                        {
                            // make everything invisible
                            val id1    = listOfStretchImages.last()
                            val image1 = findViewById<ImageView>(id1)
                            image1.visibility    = View.INVISIBLE
                            textView.text  = "You're finished!"
                            textView2.visibility = View.INVISIBLE
                        }
                    }
            }.start()
        }
    }
}
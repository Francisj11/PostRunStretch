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

    private var shortbing: SoundPool? = null
    private var tada: SoundPool? = null
    private val soundId = 1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shortbing = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        shortbing!!.load(baseContext, R.raw.shortbing_enhanced, 1)
        tada = SoundPool(6, AudioManager.STREAM_MUSIC, 0)
        tada!!.load(baseContext, R.raw.tada_enhanced, 1)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topTextView: TextView = findViewById(R.id.topTextView)
        val bottomTextView: TextView = findViewById(R.id.bottomTextView)

        topTextView.text = "Lets GO!"

        val button: Button = findViewById(R.id.button)

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

            shortbing?.play(soundId, 1F, 1F, 0, 0, 1F)
            bottomTextView.visibility = View.VISIBLE
            bottomTextView.text = "Stretch no. 1 of " + listOfStretchImages.size + "."

            // Start a timer
            object : CountDownTimer(2000, 1000)
            {
                var iterations = 0
                override fun onTick(millisUntilFinished: Long)
                {
                    topTextView.text = (millisUntilFinished / 1000).toString() + "s."
                    bottomTextView.text = "Stretch no. " + (iterations+1).toString() + " of " + listOfStretchImages.size + "."
                }

                override fun onFinish()
                {
                    // Swap the visibility of current image with next
                    val id1 = listOfStretchImages[iterations]
                    if ((iterations + 1) < listOfStretchImages.size)
                    {
                        val id2 = listOfStretchImages[iterations+1]
                        val image2 = findViewById<ImageView>(id2)
                        image2.visibility = View.VISIBLE
                    }
                    val image1 = findViewById<ImageView>(id1)
                    image1.visibility = View.INVISIBLE
                    iterations++

                    if (iterations < (listOfStretchImages.size))
                    {
                        // Play sound
                        shortbing?.play(soundId, 1F, 1F, 0, 0, 1F)
                        this.start()
                    }
                    else
                    {
                        // make everything invisible
                        val id1    = listOfStretchImages.last()
                        val image1 = findViewById<ImageView>(id1)
                        image1.visibility    = View.INVISIBLE
                        tada?.play(soundId, 1F, 1F, 0, 0, 1F)
                        topTextView.text  = "You're finished!"
                        bottomTextView.visibility = View.INVISIBLE
                    }
                }
            }.start()
        }
    }
}
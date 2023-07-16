/**
 * Sample App Created & Developed By Encept Ltd
 * https://www.encept.co
 * Developer: Karim Abdallah
 *
 * repo link: https://github.com/kimoandroid/GlideSlider
 */
package co.encept.app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import co.encept.app.databinding.ActivityMainBinding
import co.encept.slider.SliderLayout
import co.encept.slider.animations.DescriptionAnimation
import co.encept.slider.slidertypes.TextSliderView
import com.bumptech.glide.request.RequestOptions


/**
 * This Is The Simple Example Of Slider Library without any implementations.
 * If you want to use the implementations you can go to this Activity: [ActivityWithImplementations].
 *
 * @author Encept Ltd
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mDemoSlider: SliderLayout


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // =========================== Start Slider Codes =============================

        val itemName = ArrayList<String>()
        val itemUrl = ArrayList<String>()
        // 1st item in the slider
        itemUrl.add("https://www.revive-adserver.com/media/GitHub.jpg")
        itemName.add("JPG Format")
        // 2nd item in the slider
        itemUrl.add("https://e7.pngegg.com/pngimages/519/64/png-clipart-black-laptop-computer-illustration-computer-programming-web-development-computer-software-programming-language-theme-coder-electronics-gadget.png")
        itemName.add("PNG Format")
        // 3rd item in the slider
        itemUrl.add("https://i.pinimg.com/originals/e4/26/70/e426702edf874b181aced1e2fa5c6cde.gif")
        itemName.add("GIF Format")


        /**
         * you can add regular LinearLayout at xml file and add SliderLayout to it programmatically like this code below
         * Or you can add SliderLayout directly to xml file like we did at ActivityWithImplementations file.
         */
        //TODO: normal slider into linear layout
        mDemoSlider = SliderLayout(this@MainActivity)
        mDemoSlider.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        // add slider to LinearLayout
        binding.slider.addView(mDemoSlider)

        val requestOptions = RequestOptions()
        requestOptions.centerInside()

        for (pos in itemUrl.indices) {
            // if you want show image only without description text use DefaultSliderView(this) instead
            val sliderView = TextSliderView(this)

            // initialize SliderLayout
            sliderView
                .image(itemUrl[pos])
                .description(itemName[pos])
                .setRequestOption(requestOptions)
                .setProgressBarVisible(true)

                // handle slider click listener:
                .setOnSliderClickListener { slider ->
                    // TODO: Add your click listener implementation here.
                    runOnUiThread {
                        Toast.makeText(this, "name: ${slider.bundle.getString("name")}\nurl: ${slider.bundle.getString("url")}",
                            Toast.LENGTH_SHORT).show()
                    }
                }

            // add image url and image name to bundle
            sliderView.bundle(Bundle())
            sliderView.bundle.putString("name", itemName[pos])
            sliderView.bundle.putString("url", itemUrl[pos])
            mDemoSlider.addSlider(sliderView)
        }

        /**
         * set slider animation see all Transformer animations at [com.glide.slider.library.SliderLayout.Transformer]
         */
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Tablet)
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        mDemoSlider.setCustomAnimation(DescriptionAnimation())
        // timer between every slider scroll (in milliseconds)
        mDemoSlider.setDuration(4000)
        // set true if you want to stop slider cycling when user touch it.
        mDemoSlider.stopCyclingWhenTouch(false)

        // ========================= End Of Slider Codes ===========================


        binding.btnSec.setOnClickListener {
            startActivity(Intent(this, ActivityWithImplementations::class.java))
        }
    }


    override fun onStop() {
        super.onStop()
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle()
    }

}
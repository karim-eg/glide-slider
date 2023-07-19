/**
 * Sample App Created & Developed By Encept Ltd
 * https://www.encept.co
 * Developer: Karim Abdallah
 *
 * repo link: https://github.com/kimoandroid/GlideSlider
 */
package co.encept.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.encept.app.databinding.ActivityWithImplementationsBinding
import co.encept.slider.SliderLayout
import co.encept.slider.animations.DescriptionAnimation
import co.encept.slider.slidertypes.BaseSliderView
import co.encept.slider.slidertypes.TextSliderView
import co.encept.slider.tricks.ViewPagerEx
import com.bumptech.glide.request.RequestOptions

/**
 * This Is The Example Of Slider Library with all implementations.
 * this activity implements: OnSliderClickListener, ViewPagerEx.OnPageChangeListener
 *
 * @author Encept Ltd
 */
class ActivityWithImplementations : AppCompatActivity(),
                        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private lateinit var binding: ActivityWithImplementationsBinding

    private lateinit var mDemoSlider: SliderLayout

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithImplementationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * You can get slider from xml and assign to the variable directly.
         * or you can add regular LinearLayout in your xml file and add slider to it like we did in MainActivity.
         */
        //TODO: direct slider from xml
        mDemoSlider = binding.slider

        val itemName = ArrayList<String>()
        val itemUrl = ArrayList<String>()
        // 1st item in the slider
        itemUrl.add(Consts.imgUrlJpg)
        itemName.add("JPG Image Format")
        // 2nd item in the slider
        itemUrl.add(Consts.imgUrlPng)
        itemName.add("PNG Image Format")
        // 3rd item in the slider
        itemUrl.add(Consts.imgUrlGif)
        itemName.add("GIF Image Format")

        val requestOptions = RequestOptions()
        requestOptions.centerInside()

        for (i in itemUrl.indices) {
            // if you want show image only without description text use DefaultSliderView(this) instead
            val sliderView = TextSliderView(this)

            // initialize SliderLayout
            sliderView
                .image(itemUrl[i])
                .description(itemName[i])
                .setRequestOption(requestOptions)
                .setProgressBarVisible(true)
                .setOnSliderClickListener(this)

            // add image url and image name to bundle
            sliderView.bundle(Bundle())
            sliderView.bundle.putString("name", itemName[i])
            sliderView.bundle.putString("url", itemUrl[i])
            mDemoSlider.addSlider(sliderView)
        }

        /**
         * set slider animation see all Transformer animations at [com.glide.slider.library.SliderLayout.Transformer]
         */
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion)
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        mDemoSlider.setCustomAnimation(DescriptionAnimation())
        // timer between every slider scroll (in milliseconds)
        mDemoSlider.setDuration(4000)
        // set true if you want to stop slider cycling when user touch it.
        mDemoSlider.stopCyclingWhenTouch(false)
        // add OnPageChangeListener to the slider
        mDemoSlider.addOnPageChangeListener(this)
    }


    override fun onStop() {
        super.onStop()
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle()
    }


    override fun onSliderClick(slider: BaseSliderView) {
        // TODO: Add your click listener implementation here.
        runOnUiThread {
            Toast.makeText(this, "name: ${slider.bundle.getString("name")}\nurl: ${slider.bundle.getString("url")}",
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        // TODO: Not implemented yet
        Log.d("Slider onPageScrolled", "Page Scrolled: $position")
    }

    override fun onPageSelected(position: Int) {
        // TODO: Not implemented yet
        Log.d("Slider onPageSelected", "Page Changed: $position")
    }

    override fun onPageScrollStateChanged(state: Int) {
        // TODO: Not implemented yet
        Log.d("onStatChanged", "State Changed: $state")
    }
}
# Android Glide Slider Library
#### Create a slider view inside your app without viewpager or complex adapters.

[![StandWithPalestine](https://raw.githubusercontent.com/karim-eg/StandWithPalestine/main/assets/palestine_badge.svg)](https://github.com/karim-eg/StandWithPalestine)
[![](https://jitpack.io/v/karim-eg/glide-slider.svg)](https://jitpack.io/#karim-eg/glide-slider)
[![](https://jitci.com/gh/karim-eg/glide-slider/svg)](https://jitci.com/gh/karim-eg/glide-slider)
[![Build](https://github.com/karim-eg/glide-slider/actions/workflows/android.yml/badge.svg)](https://github.com/karim-eg/glide-slider/actions/workflows/android.yml)
![GitHub release (with filter)](https://img.shields.io/github/v/release/karim-eg/glide-slider)

![Repo Size](https://img.shields.io/github/repo-size/karim-eg/glide-slider)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6b6ab00686aa4c2686fa95276ba2845a)](https://app.codacy.com/gh/karim-eg/glide-slider/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Lines Of Code](https://tokei.rs/b1/github/karim-eg/glide-slider?category=code)](https://github.com/karim-eg/glide-slider)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)
[![Discord](https://img.shields.io/discord/954020097381502976.svg?label=&logo=discord&logoColor=ffffff&color=7389D8&labelColor=6A7EC2)](https://discord.gg/ptz6VByDbv)

<br>

[![StandWithPalestine](https://raw.githubusercontent.com/karim-eg/StandWithPalestine/main/assets/palestine_banner.svg)](https://github.com/karim-eg/StandWithPalestine/blob/main/Donate.md)

<br>


## Screenshot
<img src="https://github.com/karim-eg/glide-slider/assets/69405523/adba3c99-aa4e-4fd1-9b7e-276cba0f475c" width=420/>

## Example App
[https://github.com/karim-eg/glide-slider/tree/master/app](https://github.com/karim-eg/glide-slider/tree/master/app)

<br>

## Usage


### Step 1
> Add this line to root `build.gradle` at allprojects block code:
```gradle
allprojects {
  repositories {
   //...
   maven { url 'https://jitpack.io' }
  }
 }
 ```

> then add this line into your `build.gradle` app level.
```gradle
dependencies {
    implementation "com.github.karim-eg:glide-slider:1.0"
}
```

<br>

### Step 2
Add this widget into your `xml` activity file
```xml
<co.encept.slider.SliderLayout
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_marginTop="8dp" />
```

<br>

### Step 3
Add these permessions into your `AndroidManifest.xml` file
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" android:maxSdkVersion="32" />
```

**Note:** If you want to load images from the internet, you need both the `INTERNET` and `READ_EXTERNAL_STORAGE` permissions to allow files from the internet to be cached into local storage, but if you want to load images from drawable, then no additional permissions are necessary.

<br>

### Step 4
Now Open Your activity and add `SliderLayout` at class level
```kotlin
private lateinit var mDemoSlider: SliderLayout
```

<br>

### Step 5
at `onCreate`, assign the sliderLayout that you just defined before

just type the variable name and assign the slider from xml file by viewBinding or findViewById
```kotlin
mDemoSlider = binding.slider
```

<br>

### Step 6
at `onCreate`, Now let's add some item's to the slider.

Define two ArrayLists the first one called `itemName`, the secondone called `itemUrl`.
```kotlin
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
```

<br>

### Step 7
at `onCreate`, Add This Lines below the other code that you've added before
```kotlin
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
```

<br>

### Step 8
don't forget to close the sliderLayout cycle when activity stops to prevent a memory leak.
```kotlin
override fun onStop() {
    super.onStop()
    // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
    mDemoSlider.stopAutoCycle()
}
```

<br>

#### Optional
> There are some default indicators. If you want to use a provided indicator:

```xml
<co.encept.slider.indicators.PagerIndicator
        android:id="@+id/custom_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"/>
```

#### You can also customize this library via:
##### [styles.xml](https://github.com/karim-eg/glide-slider/blob/master/slider/src/main/res/values/colors.xml)
##### [colors.xml](https://github.com/karim-eg/glide-slider/blob/master/slider/src/main/res/values/styles.xml)

<br>

#### now you've implemented slider library into your app if you want more advanced options you can take a look here: [ActivityWithImplementations](https://github.com/karim-eg/glide-slider/blob/master/app/src/main/java/co/encept/app/ActivityWithImplementations.kt)

#### That's All don't forget to star the project & fork if you want to develop the library.

#### Powered by [Encept Ltd](https://encept.co).

#### * This Library Was Forked From: [https://github.com/firdausmaulan/GlideSlider](https://github.com/firdausmaulan/GlideSlider)

<br>

## Used by
List of apps on Play Store where this library used. Contact me if you want your app listed.


Icon | Application
------------ | -------------
<img src="https://play-lh.googleusercontent.com/I0i7O7IBTeuRDwjogpu9W5LFZU2X4IMPSs7fCxUOT5sMrTsaJauaEJXYOJCxSPGkm_ps=w240-h480-rw" width="48" height="48" /> | [Coding Oasis - Learn Programming]

<br>

## Contributors
<a href="https://github.com/karim-eg/glide-slider/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=karim-eg/glide-slider" />
</a>
<br>

## Stargazers
[![Stargazers repo roster for @karim-eg/glide-slider](https://reporoster.com/stars/karim-eg/glide-slider)](https://github.com/karim-eg/glide-slider/stargazers)


## Forkers
[![Forkers repo roster for @karim-eg/glide-slider](https://reporoster.com/forks/karim-eg/glide-slider)](https://github.com/karim-eg/glide-slider/network/members)



[Coding Oasis - Learn Programming]:https://links.encept.co/co_github

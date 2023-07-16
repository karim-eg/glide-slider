[![](https://jitpack.io/v/kimoandroid/glide-slider.svg)](https://jitpack.io/#kimoandroid/glide-slider)

 
## Screenshot
<img src="https://github.com/kimoandroid/GlideSlider/assets/69405523/360ee3f8-e120-483d-8ba9-97ee96893b1b" width=420/>

## Example App
[https://github.com/kimoandroid/GlideSlider/tree/master/app](https://github.com/kimoandroid/GlideSlider/tree/master/app)

## Usage

### Step 1
add this line into your `build.gradle` app.
```gradle
dependencies {
    implementation "com.github.kimoandroid:GlideSlider:1.6"
}
```

<br>

### Step 2
Add this widget into your `xml` activity file
```xml
<com.glide.slider.library.SliderLayout
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_marginTop="8dp" />
```

<br>

### Step 3
Add these permessions into your `AndroidManifest.xml` file
```manifest
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" android:maxSdkVersion="32" />
```

**Note:** If you want to load images from the internet, you need both the `INTERNET` and `READ_EXTERNAL_STORAGE` permissions to allow files from the internet to be cached into local storage, but if you want to load images from drawable, then no additional permissions are necessary.

<br>

### Step 4
Add the Slider to your layout:
```xml
<com.glide.slider.library.SliderLayout
        android:id="@+id/slider"
        android:layout_width="match_parent"
        android:layout_height="200dp"/>
```        

<br>

### Step 5
Now Open Your activity and add `SliderLayout` at class level
```kotlin
private lateinit var mDemoSlider: SliderLayout
```

<br>

### Step 6
at `onCreate`, assign the sliderLayout that you just defined before

just type the variable name and assign the slider from xml file by viewBinding or findViewById
```kotlin
mDemoSlider = binding.slider
```

<br>

### Step 7
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

### Step 8
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

### Step 9
don't forget to close the sliderLayout cycle when activity stops to prevent a memory leak.
```kotlin
override fun onStop() {
    super.onStop()
    // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
    mDemoSlider.stopAutoCycle()
}
```

<br>

#### now you've implemented slider library into your app if you want more advanced options you can take a look here: [ActivityWithImplementations](https://github.com/kimoandroid/GlideSlider/blob/master/app/src/main/java/co/encept/app/ActivityWithImplementations.kt)

#### That's All don't forget to star the project & fork if you want to develop the library.

#### Powered by [Encept Ltd](https://encept.co).

<br><br><br>

#### Optional
There are some default indicators. If you want to use a provided indicator:
 
```xml
<com.glide.slider.library.indicators.PagerIndicator
        android:id="@+id/custom_indicator"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center"/>
```

You can customize this library via styles.xml or colors.xml

styles.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">

    <style name="GlideSliderCardStyle" parent="Base.CardView">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">match_parent</item>
    </style>

    <style name="GlideSliderBackgroundStyle">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:background">@color/glide_slider_background_color</item>
    </style>

    <style name="GlideSliderImageStyle">
        <item name="android:layout_height">match_parent</item>
        <item name="android:layout_width">match_parent</item>
    </style>

    <style name="GlideSliderLoadingStyle" parent="Base.Widget.AppCompat.ProgressBar">
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_width">wrap_content</item>
        <item name="android:layout_centerInParent">true</item>
    </style>

    <style name="GlideSliderDescriptionBackgroundStyle">
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:layout_alignParentBottom">true</item>
        <item name="android:background">@color/glide_slider_description_background_color</item>
        <item name="android:gravity">center_vertical</item>
        <item name="android:minHeight">30dp</item>
        <item name="android:orientation">vertical</item>
        <item name="android:paddingLeft">10dp</item>
        <item name="android:paddingRight">10dp</item>
    </style>

    <style name="GlideSliderDescriptionTextStyle" parent="android:Widget.TextView">
        <item name="android:layout_height">wrap_content</item>
        <item name="android:layout_width">match_parent</item>
        <item name="android:textColor">@color/glide_slider_description_color</item>
        <item name="android:fontFamily" tools:targetApi="jelly_bean">sans-serif</item>
    </style>
</resources>
```

colors.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="glide_slider_background_color">#000000</color>
    <color name="glide_slider_indicator_color">#FFFFFF</color>
    <color name="glide_slider_description_color">#FFFFFF</color>
    <color name="glide_slider_description_background_color">#77000000</color>
</resources>
```

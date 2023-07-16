package co.encept.slider.slidertypes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import co.encept.slider.R;

/**
 * a simple slider view, which just show an image. If you want to make your own slider view,
 * <p>
 * just extend BaseSliderView, and implement getView() method.
 */
public class DefaultSliderView extends BaseSliderView {

    public DefaultSliderView(Context context) {
        super(context);
    }

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_default, null);
        AppCompatImageView target = v.findViewById(R.id.glide_slider_image);
        bindEventAndShow(v, target);
        return v;
    }
}

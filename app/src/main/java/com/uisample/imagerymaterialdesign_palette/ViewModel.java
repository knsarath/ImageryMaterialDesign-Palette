package com.uisample.imagerymaterialdesign_palette;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.graphics.Palette;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by sarath on 11/7/17.
 */

public class ViewModel extends BaseObservable {
    private String imageUrl = "https://cdn.pixabay.com/photo/2015/07/06/13/58/arlberg-pass-833326_960_720.jpg";
    private String textInput;
    private int overlayColor;


    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    public void setTextInput(String textInput) {
        this.textInput = textInput;
    }

    @Bindable
    public String getTextInput() {
        return textInput;
    }

    @BindingAdapter(value = {"app:imageUrl", "app:viewmodel"}, requireAll = true)
    public static void setImage(final ImageView imageView, String imageUrl, final ViewModel viewModel) {
        if (imageUrl != null && !TextUtils.isEmpty(imageUrl)) {
            Picasso.with(imageView.getContext()).load(imageUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    // Asynchronous
                    imageView.setImageBitmap(bitmap);
                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                        public void onGenerated(Palette p) {
                            // Use generated instance
                            int mutedColor = p.getVibrantColor(ContextCompat.getColor(imageView.getContext(), R.color.colorAccent));
                            int alphaAppliedColor = ColorUtils.setAlphaComponent(mutedColor, 160);
                            viewModel.setOverlayColor(alphaAppliedColor);
                        }
                    });

                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });
        }
        // Asynchronous


    }

    public void buttonClick(View view, String imageUrl) {
        setImageUrl(imageUrl);
    }

    public void setOverlayColor(int overlayColor) {
        this.overlayColor = overlayColor;
        notifyPropertyChanged(BR.overlayColor);
    }

    @Bindable
    public int getOverlayColor() {
        return overlayColor;
    }
}

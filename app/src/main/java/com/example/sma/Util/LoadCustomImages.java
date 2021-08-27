package com.example.sma.Util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.sma.App;

public class LoadCustomImages {

    @BindingAdapter("android:loadImage")
    public static void loadImage(ImageView imgView, int url) {
        try {
            Glide.with(App.context)
                    .load(url)
                    .into(imgView);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

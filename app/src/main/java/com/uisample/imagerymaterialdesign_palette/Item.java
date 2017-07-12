package com.uisample.imagerymaterialdesign_palette;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v4.util.Pair;
import android.widget.TextView;

/**
 * Created by sarath on 11/7/17.
 */

public class Item extends BaseObservable {
    private Pair<String, Integer> pair;

    public Item(Pair<String, Integer> stringIntegerPair) {
        pair = stringIntegerPair;
    }

    @Bindable
    public Pair<String, Integer> getPair() {
        return pair;
    }

    public void setStringIntegerPair(Pair<String, Integer> stringIntegerPair) {
        pair = stringIntegerPair;
        notifyPropertyChanged(BR.pair);
    }

    @BindingAdapter("app:setVal")
    public static void set(TextView textView, Item item) {
        textView.setText(item.getPair().first);
        textView.setBackgroundColor(item.getPair().second);
    }
}

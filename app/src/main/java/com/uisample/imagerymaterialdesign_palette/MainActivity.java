package com.uisample.imagerymaterialdesign_palette;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.uisample.imagerymaterialdesign_palette.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ViewModel mViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewmodel = new ViewModel();
        activityMainBinding.setViewmodel(mViewmodel);

    }
}

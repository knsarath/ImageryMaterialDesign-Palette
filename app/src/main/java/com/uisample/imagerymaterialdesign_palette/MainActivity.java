package com.uisample.imagerymaterialdesign_palette;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    public void showFragment(View view) {
        if (mViewmodel.getPalette() != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            PaletteDetailFragment fragment = PaletteDetailFragment.createInstance(PaletteDetailFragment.methodAndColors(mViewmodel.getPalette()));
            transaction.replace(R.id.container, fragment);
            getSupportFragmentManager().popBackStackImmediate();
            transaction.commit();
        }

    }

    public void clearText(View view) {
        mViewmodel.setTextInput("");
    }
}

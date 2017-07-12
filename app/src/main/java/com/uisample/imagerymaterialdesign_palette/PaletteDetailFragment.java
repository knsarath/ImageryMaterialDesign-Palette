package com.uisample.imagerymaterialdesign_palette;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uisample.imagerymaterialdesign_palette.databinding.PaletDetailFragmentBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sarath on 11/7/17.
 */

public class PaletteDetailFragment extends Fragment {

    ArrayList<Pair<String, Integer>> mPaletteColors;

    public void setPaletteColors(ArrayList<Pair<String, Integer>> paletteColors) {
        mPaletteColors = paletteColors;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PaletDetailFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.palet_detail_fragment, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new PalleteAdapter(mPaletteColors));
        return root;
    }

    public static PaletteDetailFragment createInstance(ArrayList<Pair<String, Integer>> param) {
        PaletteDetailFragment paletteDetailFragment = new PaletteDetailFragment();
        paletteDetailFragment.setPaletteColors(param);
        return paletteDetailFragment;
    }

    public static ArrayList<Pair<String, Integer>> methodAndColors(Palette palette) {
        ArrayList<Pair<String, Integer>> pairs = new ArrayList<>();
        Class c = palette.getClass();
        for (Method method : c.getDeclaredMethods()) {
            if (method.getReturnType().equals(Integer.TYPE)) {
                String methodName = method.getName();
                int result = Color.TRANSPARENT;
                try {
                    if (Modifier.isPublic(method.getModifiers()) && method.getGenericParameterTypes().length == 0) {
                        result = (int) method.invoke(palette);
                        // use the method
                    }
                    if (Modifier.isPublic(method.getModifiers()) && method.getGenericParameterTypes().length == 1 && method.getGenericParameterTypes()[0] == Integer.TYPE) {
                        result = (int) method.invoke(palette, Color.TRANSPARENT);
                        // use the method
                    }
                    pairs.add(new Pair<>(methodName, result));
                } catch (SecurityException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return pairs;
    }

}

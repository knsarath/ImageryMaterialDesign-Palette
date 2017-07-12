package com.uisample.imagerymaterialdesign_palette;

import android.databinding.DataBindingUtil;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uisample.imagerymaterialdesign_palette.databinding.ItemBinding;

import java.util.ArrayList;

/**
 * Created by sarath on 11/7/17.
 */

public class PalleteAdapter extends RecyclerView.Adapter<PalleteAdapter.Viewholder> {
    ArrayList<Pair<String, Integer>> mPaletteColors;

    public PalleteAdapter(ArrayList<Pair<String, Integer>> paletteColors) {
        mPaletteColors = paletteColors;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item, parent, false);
        return new Viewholder(itemBinding);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.bind(new Item(mPaletteColors.get(position)));
    }

    @Override
    public int getItemCount() {
        return mPaletteColors == null ? 0 : mPaletteColors.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ItemBinding mItemBinding;

        public Viewholder(ItemBinding itemBinding) {
            super(itemBinding.getRoot());
            mItemBinding = itemBinding;
        }

        public void bind(Item pair) {
            mItemBinding.setVm(pair);
            mItemBinding.executePendingBindings();
        }
    }
}

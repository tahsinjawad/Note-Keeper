package com.darkray.noteskeeper.bindingadapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.darkray.noteskeeper.utils.decoration.ItemOffsetDecoration;

public class ItemDecorationBindingAdapter {

    @BindingAdapter("itemDecorationVertical")
    public static void itemDecorationVertical(RecyclerView view, int resId) {
        view.addItemDecoration(new ItemOffsetDecoration(view.getContext(), resId));
    }

}

package com.imagesearch.helper;

/**
 * Interface to notify an item ViewHolder of relevant callbacks from {@link
 * android.support.v7.widget.helper.ItemTouchHelper.Callback}.
 *
 * @author akutty
 */
public interface ItemTouchHelperViewHolder {

    void onItemSelected();

    void onItemClear();
}
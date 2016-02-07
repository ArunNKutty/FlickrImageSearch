package com.imagesearch.helper;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Interface to listen for a move or dismissal event from a {@link ItemTouchHelper.Callback}.
 *
 * @author arun kutty
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
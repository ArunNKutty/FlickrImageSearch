package com.imagesearch.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imagesearch.R;
import com.imagesearch.helper.ItemTouchHelperAdapter;
import com.imagesearch.helper.ItemTouchHelperViewHolder;
import com.lib.listener.OnStartDragListener;
import com.lib.model.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Image List Adapter
 *
 * @author akutty
 */
public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private List<Photo> mPhotos = new ArrayList<>();
    private Context mContext;
    private IFlickerImageListener mFlickerImageListener;
    private OnStartDragListener mDragStartListener;

    public ImageListAdapter(List<Photo> photos, Activity activity, OnStartDragListener listener) {
        this.mPhotos = photos;
        this.mContext = activity;
        this.mDragStartListener = listener;
        this.mFlickerImageListener = (IFlickerImageListener) activity;
        mPhotos.addAll(photos);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_flickr_image_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Photo photo = mPhotos.get(position);

        final String url = getFlickrUrl(photo);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(holder.image);
        holder.title.setText(photo.getTitle());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFlickerImageListener.onImageClicked(url);
            }
        });

        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mDragStartListener.onStartDrag(holder);
                return false;
            }
        });

    }

    private String getFlickrUrl(Photo photo) {
        //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
        return "https://farm" + photo.getFarm() + ".staticflickr.com/" + photo.getServer() + "/" + photo.getId() + "_" + photo.getSecret() + ".jpg";
    }

    @Override
    public int getItemCount() {
        return mPhotos.size()/2;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //Re-ordering logic
        Photo movedItem = mPhotos.remove(fromPosition);
        mPhotos.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mPhotos.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

        public View parent;
        public ImageView image;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            parent = itemView;
            image = (ImageView) parent.findViewById(R.id.flickr_image);
            title = (TextView) parent.findViewById(R.id.image_title);
        }

        @Override
        public void onItemSelected() {
            image.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            image.setBackgroundColor(0);
        }
    }
}

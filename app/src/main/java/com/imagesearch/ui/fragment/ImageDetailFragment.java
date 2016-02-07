package com.imagesearch.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imagesearch.R;

/**
 * A simple {@link Fragment} subclass.
 *
 * Use the {@link ImageDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageDetailFragment extends Fragment {
    private static final String ARG_IMAGE_URL = "image_url";
    private ImageView mDetailImageView;
    private static String BLANK="";
    public ImageDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageUrl Parameter .
     * @return A new instance of fragment ImageDetailFragment.
     */
    public static ImageDetailFragment newInstance(String imageUrl) {
        ImageDetailFragment fragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        mDetailImageView =(ImageView)view.findViewById(R.id.image_detail);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String imageUrl = BLANK;
        if (getArguments() != null) {
            imageUrl = getArguments().getString(ARG_IMAGE_URL);
        }
        loadImage(imageUrl);
    }

    private void loadImage(String url) {
        Glide.with(getContext())
                .load(url)
                .crossFade()
                .into(mDetailImageView);
    }

}

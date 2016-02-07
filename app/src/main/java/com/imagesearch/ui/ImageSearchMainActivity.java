package com.imagesearch.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import com.imagesearch.R;
import com.imagesearch.ui.adapter.IFlickerImageListener;
import com.imagesearch.ui.fragment.ImageDetailFragment;
import com.imagesearch.ui.fragment.ImageSearchFragment;

public class ImageSearchMainActivity extends BaseActivity implements IFlickerImageListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search_main);
        if (savedInstanceState == null) {
            showFragment(ImageSearchFragment.newInstance(), false);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onImageClicked(String imageUrl) {
        showFragment(ImageDetailFragment.newInstance(imageUrl),true);
    }
}

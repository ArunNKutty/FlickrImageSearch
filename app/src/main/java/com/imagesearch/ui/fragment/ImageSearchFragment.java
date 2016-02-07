package com.imagesearch.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.imagesearch.R;
import com.imagesearch.helper.RecyclerViewItemTouchHelperCallback;
import com.imagesearch.loader.callback.ImageListLoaderCallback;
import com.imagesearch.ui.adapter.ImageListAdapter;
import com.lib.listener.INetworkListener;
import com.lib.listener.OnStartDragListener;
import com.lib.model.FlickrResponse;
import com.lib.model.Photo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ImageSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageSearchFragment extends BaseProgressFragment implements INetworkListener<FlickrResponse>, OnStartDragListener {


    private RecyclerView mImageRecyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private EditText mSearchEditText;
    private Button mSearchButton;
    private final static int GRID_COLUMNS = 2;

    public ImageSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ImageSearchFragment.
     */
    public static ImageSearchFragment newInstance() {
        ImageSearchFragment fragment = new ImageSearchFragment();
        return fragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_image_search, container, false);
        mImageRecyclerView = (RecyclerView) view.findViewById(R.id.image_recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), GRID_COLUMNS);
        mImageRecyclerView.setLayoutManager(layoutManager);
        mImageRecyclerView.setHasFixedSize(true);
        mSearchEditText = (EditText) view.findViewById(R.id.et_search);
        mSearchButton = (Button) view.findViewById(R.id.button_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchString = mSearchEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(searchString)) {
                    //Destroy Loader during new search to remove cached data
                    ImageListLoaderCallback.destroyLoader(getLoaderManager());
                    performSearch(searchString);
                } else {
                    Snackbar.make(getView(), "Please enter a search string", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Search functionality
     */
    private void performSearch(String searchString) {
        //Pagination ,format and json callback has been hardcoded for now.
        ImageListLoaderCallback.initLoader(getContext(),
                getLoaderManager(), this, searchString, "20", "json", "1");
        updateProgress(true);
    }

    @Override
    public void onSuccess(FlickrResponse data) {
        updateProgress(false);
        if (getView() != null) {
            loadImages(data.getFlickrImageList().getPhoto());
        }
    }


    private void loadImages(List<Photo> photos) {
        if (null != photos && photos.size() > 0) {
            ImageListAdapter adapter = new ImageListAdapter(photos, getActivity(), this);
            mImageRecyclerView.setAdapter(adapter);
            ItemTouchHelper.Callback callback = new RecyclerViewItemTouchHelperCallback(adapter);
            mItemTouchHelper = new ItemTouchHelper(callback);
            mItemTouchHelper.attachToRecyclerView(mImageRecyclerView);
        }
        updateProgress(false);
    }

    @Override
    public void onError(Exception ex) {
        updateProgress(false);
        updateError(true);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}

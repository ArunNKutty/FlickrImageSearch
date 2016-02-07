package com.imagesearch.ui.fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.imagesearch.R;


/**
 * Created by akutty .
 */
abstract class BaseProgressFragment extends BaseFragment {

    private FrameLayout mContainer;

    protected abstract View initView(LayoutInflater inflater, ViewGroup container);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutId(), container, false);
        mContainer = (FrameLayout) view.findViewById(R.id.container);
        addToContainer(initView(inflater, container));
        return view;
    }

    protected int getLayoutId() {
        return R.layout.fragment_base;
    }

    protected void addToContainer(View view) {
        if (mContainer != null) {
            mContainer.addView(view);
        }
    }

    protected void updateError(boolean shouldShow) {
        updateError(shouldShow, null);
    }

    protected void updateError(boolean shouldShow, String error) {
        if (getView() != null) {
            TextView errorView = (TextView) getView().findViewById(R.id.error_text);
            if (errorView != null) {
                errorView.setVisibility(shouldShow ? View.VISIBLE : View.GONE);
                if (!TextUtils.isEmpty(error)) {
                    errorView.setText(Html.fromHtml(error));
                    errorView.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
        }
    }

    protected void updateProgress(boolean shouldShow) {
        if (getView() != null) {
            ProgressBar progressView = (ProgressBar) getView().findViewById(R.id.progress_bar);
            if (progressView != null) {
                if (shouldShow) {
                    progressView.setVisibility(View.VISIBLE);
                } else {
                    progressView.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        mContainer = null;
        super.onDestroyView();
    }

}

package com.imagesearch.ui.fragment;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;

/**
 * Base Fragment
 *
 * @author akutty
 */
public class BaseFragment extends Fragment {

    private boolean mPendingRotation = false;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (needsRotationHandling()) {
            if (isResumed()) {
                handleRotation();
            } else {
                mPendingRotation = true;
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (needsRotationHandling() && mPendingRotation) {
            mPendingRotation = false;
            handleRotation();
        }
    }

    private void handleRotation() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    protected boolean needsRotationHandling() {
        return false;
    }
}

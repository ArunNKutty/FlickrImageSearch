package com.imagesearch.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.imagesearch.R;
import com.lib.listener.NavigationListener;
import com.lib.util.NavigationManager;


/**
 * Base Activity
 *
 * @author akutty
 */


public abstract class BaseActivity extends AppCompatActivity implements NavigationListener {

    ProgressDialog mProgress;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private NavigationManager mNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavigationManager = new NavigationManager(this);

        initProgressDialog();

    }

    protected void initProgressDialog() {
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Loading...");
        mProgress.setCancelable(false);
    }

    public void showProgressDialog(boolean shouldShow) {
        if (mProgress != null) {
            if (shouldShow) {
                mProgress.show();
            } else {
                mProgress.dismiss();
            }
        }
    }

    public void showError(String errorMessage) {
        Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {

        mProgress = null;
        super.onDestroy();
    }

    @Override
    public void showFragment(Fragment fragment, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack) {
        mNavigationManager.replaceContentFragment(fragment, R.id.fragmentContainer, enterAnim, exitAnim, popEnterAnim, popExitAnim, addToBackStack);
    }

    @Override
    public void showFragment(Fragment fragment, boolean addToBackStack) {
        mNavigationManager.replaceContentFragment(fragment, R.id.fragmentContainer, 0, 0, 0, 0, addToBackStack);
    }

    @Override
    public void removeFragment(Fragment fragment) {
        // TODO implement method
    }

}

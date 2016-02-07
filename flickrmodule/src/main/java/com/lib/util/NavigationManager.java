package com.lib.util;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


/**
 * Manages Fragment Navigation
 *
 * @author akutty
 */
public class NavigationManager {

    private AppCompatActivity mActivity;
    private String mFragmentName;

    public NavigationManager(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void replaceContentFragment(Fragment fragment, int containerId, int enterAnim, int exitAnim,
                                       int popEnterAnim, int popExitAnim, boolean addToBackStack) {

        // Check for null
        if (fragment != null) {

            mFragmentName = fragment.getClass().getSimpleName();
            // Get fragment manager form our activity
            FragmentManager fragmentManager = mActivity.getSupportFragmentManager();

            // replace with new content
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // Animate only if above ICS
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
            }
            fragmentTransaction.replace(containerId, fragment, mFragmentName);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            try {
                fragmentTransaction.commit();
            } catch (IllegalStateException ex) {
                Log.w(NavigationManager.class.getSimpleName(),
                        "Cannot perform a FragmentTransaction after the Activity was stopped. Fragment name : "
                                + mFragmentName
                );
                ex.printStackTrace();
                return;
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if (fragment != null) {
            // Get fragment manager form our activity
            FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
            fragmentManager.popBackStack();
        }
    }

}

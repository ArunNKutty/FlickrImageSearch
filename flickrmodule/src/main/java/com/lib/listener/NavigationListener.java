package com.lib.listener;


import android.support.v4.app.Fragment;

/**
 * Interface to handle fragment navigation
 *
 * @author akutty
 */
public interface NavigationListener {

    public void showFragment(Fragment fragment, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim,
                             boolean addToBackStack);

    public void showFragment(Fragment fragment, boolean addToBackStack);

    public void removeFragment(Fragment fragment);

}

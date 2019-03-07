package com.pay.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.pay.R;

public class BaseFragment extends Fragment {


    public void addFragmenttoStack(Fragment mfagment) {
        try {
            android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
            if (!(currentFragment.getClass().equals(mfagment.getClass())))
                if (!mfagment.isVisible()) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, mfagment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commitAllowingStateLoss();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fragment
     * @param addToBackStack
     * @param tag
     */
    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }


}

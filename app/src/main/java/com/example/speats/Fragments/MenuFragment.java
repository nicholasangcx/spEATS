package com.example.speats.Fragments;

/**
 * Created by Nicholas on 4/6/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speats.R;

public class MenuFragment extends Fragment {

    private String restaurantName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restaurantName = getArguments().getString("resName");
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.container_main);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        return view;

    }

    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();

        return fragment;
    }


    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("resName", restaurantName);
            switch (position) {
                case 0:
                    ExistingMenuFragment existingMenuFragment = ExistingMenuFragment.newInstance(1);
                    existingMenuFragment.setArguments(bundle);
                    return existingMenuFragment;
                default:
                    EditMenuFragment editMenuFragment = EditMenuFragment.newInstance(2);
                    editMenuFragment.setArguments(bundle);
                    return editMenuFragment;
            }

        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Existing";
                default:
                    return "Add New";
            }


        }
    }
}

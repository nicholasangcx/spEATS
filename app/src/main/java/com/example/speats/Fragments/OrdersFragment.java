package com.example.speats.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.speats.R;

public class OrdersFragment extends Fragment {

    private String restaurantName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        restaurantName = getArguments().getString("resName");
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.container_main);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);

        return view;

    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();

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
                    ByOrderFragment byOrderFragment = ByOrderFragment.newInstance(1);
                    byOrderFragment.setArguments(bundle);
                    return byOrderFragment;
                default:
                    ByItemFragment byItemFragment = ByItemFragment.newInstance(2);
                    byItemFragment.setArguments(bundle);
                    return byItemFragment;
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
                    return "By Order";
                default:
                    return "By Item";
            }


        }
    }
}

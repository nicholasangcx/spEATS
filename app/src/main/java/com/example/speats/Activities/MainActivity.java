package com.example.speats.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.speats.Fragments.MenuFragment;
import com.example.speats.Fragments.OrdersFragment;
import com.example.speats.Fragments.UpdateFragment;
import com.example.speats.Models.User;
import com.example.speats.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nicholas on 3/6/2017.
 */

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private String restaurantName;
    public String uid;
    private static User authUser;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle recdData = getIntent().getExtras();
        restaurantName = (String) recdData.get("resName");
       // Log.d("abcdefg", restaurantName);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
/*
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapShot: dataSnapshot.getChildren()) {
                    User user = userSnapShot.getValue(User.class);
                    if (user.getUid().equals(uid)) {
                        authUser = user;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public void onStart() {
        super.onStart();

    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Bundle bundle = new Bundle();
            bundle.putString("resName", restaurantName);
            switch (position) {
                case 0:
                    OrdersFragment ordersFragment = new OrdersFragment();
                    ordersFragment.setArguments(bundle);
                    return ordersFragment;
                case 1:
                    UpdateFragment updateFragment = new UpdateFragment();
                    updateFragment.setArguments(bundle);
                    return updateFragment;
                default:
                    MenuFragment menuFragment = new MenuFragment();
                    menuFragment.setArguments(bundle);
                    return menuFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Orders";
                case 1:
                    return "Update Table";
                case 2:
                    return "Edit Menu";
            }
            return null;
        }


    }
}

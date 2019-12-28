package com.example.madcamp_week1;

import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    MypagerAdapter adapter = new MypagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(pager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);

    }
    public void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(Contact_view.newInstance(), "Contact");
        adapter.addFragment(Gallery_view.newInstance(), "Gallery");
        adapter.addFragment(Music_view.newInstance(), "Music");
        viewPager.setAdapter(adapter);
    }

    private class MypagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public MypagerAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);

        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}

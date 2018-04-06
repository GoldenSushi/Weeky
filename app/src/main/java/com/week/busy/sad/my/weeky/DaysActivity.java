package com.week.busy.sad.my.weeky;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.week.busy.sad.my.weeky.DataStorage.Data;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragments;

import butterknife.BindView;

public class DaysActivity extends FragmentActivity {


    private Resources res;
    private ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initializes resources to get the titles
        //***must initialize always after super.onCreate!!!***
        res = getResources();
        setContentView(R.layout.activity_days);

        viewPager = (ViewPager) findViewById(R.id.days_view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new PageAdapter(fragmentManager));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.days_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    class PageAdapter extends FragmentPagerAdapter {


        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DayFragments.createFragment(position);
        }

        @Override
        public int getCount() {
            return Data.WEEK_DAYS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //gets day names from string resources
            String[] titles = res.getStringArray(R.array.day_names);
            return titles[position];
        }
    }
}

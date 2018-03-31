package com.week.busy.sad.my.weeky;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toolbar;

import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment1;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment2;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment3;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment4;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment5;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment6;
import com.week.busy.sad.my.weeky.fragments.scripts.DayFragment7;

public class DaysActivity extends FragmentActivity {


    private Resources res;
    private ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //initializes resources to get the titles
        //**must initialize always after super.onCreate!!!
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


            Fragment day_Fragment = null;

            if (position == 0) {
                day_Fragment = DayFragment1.createFragment(position);
            }
            if (position == 1) {
                day_Fragment = DayFragment2.createFragment(position);
            }
            if (position == 2) {
                day_Fragment = DayFragment3.createFragment(position);
            }
            if (position == 3) {
                day_Fragment = DayFragment4.createFragment(position);
            }
            if (position == 4) {
                day_Fragment = DayFragment5.createFragment(position);
            }
            if (position == 5) {
                day_Fragment = DayFragment6.createFragment(position);
            }
            if (position == 6) {
                day_Fragment = DayFragment7.createFragment(position);
            }


            return day_Fragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            //gets day names from string resources
            String[] titles = res.getStringArray(R.array.day_names);
            CharSequence pageTitle = null;

            if (position == 0) {
                pageTitle = titles[position];
            }
            if (position == 1) {
                pageTitle = titles[position];
            }
            if (position == 2) {
                pageTitle = titles[position];
            }
            if (position == 3) {
                pageTitle = titles[position];
            }
            if (position == 4) {
                pageTitle = titles[position];
            }
            if (position == 5) {
                pageTitle = titles[position];
            }
            if (position == 6) {
                pageTitle = titles[position];
            }

            return pageTitle;
        }
    }
}

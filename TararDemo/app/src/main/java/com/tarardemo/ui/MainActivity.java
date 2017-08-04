package com.tarardemo.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.tarardemo.R;
import com.tarardemo.base.BaseActivity;
import com.tarardemo.fragment.BarStatusAlphaFragment;
import com.tarardemo.fragment.BarStatusColorFragment;
import com.tarardemo.fragment.BarStatusImageViewFragment;
import com.tarardemo.util.BarUtils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private int[] itemIds = new int[]{R.id.navigation_color, R.id.navigation_alpha, R.id.navigation_image_view};

    private ViewPager mVpHome;
    private BottomNavigationView navigation;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_color:
                    mVpHome.setCurrentItem(0);
                    return true;
                case R.id.navigation_alpha:
                    mVpHome.setCurrentItem(1);
                    return true;
                case R.id.navigation_image_view:
                    mVpHome.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        //        BarUtils.setStatusBarAlpha(MainActivity.this, 112);
        mVpHome = (ViewPager) findViewById(R.id.vp_home);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        mFragmentList.add(BarStatusColorFragment.newInstance());
        mFragmentList.add(BarStatusAlphaFragment.newInstance());
        mFragmentList.add(BarStatusImageViewFragment.newInstance());


        mVpHome.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                navigation.setSelectedItemId(itemIds[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {

    }
}

package com.example.administrator.animations;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    ViewPagerAbdapter  abdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        mViewPager = (ViewPager) findViewById(R.id.vp_test_view);
        abdapter = new ViewPagerAbdapter(getSupportFragmentManager());
        mViewPager.setPageTransformer(true,new MyTransform_simple());
        mViewPager.setAdapter(abdapter);
    }


    class  ViewPagerAbdapter extends FragmentPagerAdapter{

        public ViewPagerAbdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MainFragment();
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    @Override
    public void onBackPressed() {

        if (mViewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
        }
    }
}

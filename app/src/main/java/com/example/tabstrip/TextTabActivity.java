package com.example.tabstrip;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.library.tabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Admin
 * time  : 4/4 0004
 * desc  :
 */

public class TextTabActivity extends AppCompatActivity {
    private PagerSlidingTabStrip tabs;
    private ViewPager viewPager;
    private List<Fragment> list = new ArrayList<Fragment>();
    private TabFragment tabFragment;
    private MyPageAdapter myPageAdapter;
    private final String[] titles = {"消息", "通讯录", "发现", "我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        tabs = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        setTabs();
    }

    private void setTabs() {
        for (int i = 0; i < titles.length; i++) {
            tabFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("content", titles[i]);
            tabFragment.setArguments(bundle);
            list.add(tabFragment);
        }
        myPageAdapter = new MyPageAdapter(getSupportFragmentManager(), titles, list);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setOffscreenPageLimit(3);
        setTabsValue();
    }

    private void setTabsValue() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        // 设置Tab底部选中的指示器Indicator的高度
        tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2.5f, dm));
        // 设置Tab底部选中的指示器 Indicator的颜色
        tabs.setIndicatorColor(Color.GREEN);
        //设置Tab标题文字的颜色
        tabs.setTextColor(Color.BLACK);
        // 设置Tab标题文字的大小
        tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));
        //设置Tab底部分割线的高度
//        tabs.setUnderlineHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, dm));
        //设置Tab底部分割线的颜色
        tabs.setUnderlineColor(Color.TRANSPARENT);
        // 设置点击某个Tab时的背景色,设置为0时取消背景色tabs.setTabBackground(0);
//        tabs.setTabBackground(R.drawable.bg_tab);
        tabs.setTabBackground(0);
        // 设置Tab是自动填充满屏幕的
        tabs.setShouldExpand(true);
        // 设置选中的Tab文字的颜色
        tabs.setSelectedTextColor(Color.GREEN);
        tabs.setViewPager(viewPager);//与ViewPager关联，这样指示器就可以和ViewPager联动
        tabs.setDividerColor(Color.TRANSPARENT);//去除tab间的分割线
        //底部横线与字体宽度一致
        tabs.setIndicatorinFollower(true);
    }

    class MyPageAdapter extends FragmentPagerAdapter {
        String[] titles;
        List<Fragment> lists = new ArrayList<>();

        public MyPageAdapter(FragmentManager fm, String[] titles, List<Fragment> list) {
            super(fm);
            this.titles = titles;
            this.lists = list;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return lists.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}

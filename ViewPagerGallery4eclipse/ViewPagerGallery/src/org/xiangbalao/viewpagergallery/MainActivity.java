package org.xiangbalao.viewpagergallery;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity
{
    TextView pageid;
    
    /**
     * 存放Viewpager中的子项
     */
    private List<View> mPagersList;
    
    private ViewPager mViewPager;
    
    /**
     * viewPager的容器，页面改变后需要刷新
     */
    private RelativeLayout viewPagerContainer;
    
    // private PagerTabStrip tabStrip;
    
    // private String[] titles;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initPagers();
        
        // initTab();
        initViewPager();
    }
    
    private void initViewPager()
    {
        // 容器
        viewPagerContainer = (RelativeLayout)findViewById(R.id.pager_layout);
        
        mViewPager = (ViewPager)findViewById(R.id.ii_viewpager);
        
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
        mViewPager.setAdapter(new MyPagerAdapter());
        
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin));
        
        mViewPager.setCurrentItem(9, true);
    }
    
    private void initTab()
    {
        // tabStrip = (PagerTabStrip) findViewById(R.id.ii_pagestrip);
        // ViewPager
        // tabStrip.setDrawFullUnderline(false);
        //
        // tabStrip.setTabIndicatorColor(Color.RED);
        
    }
    
    /** 
     * 描述: 模似数据</br>
     * 开发人员：longtaoge</br>
     * 创建时间：2015-7-19</br>
     */ 
    private void initPagers()
    {
        mPagersList = new ArrayList<View>();
        pageid = (TextView)findViewById(R.id.pageid);
        
        for (int i = 0; i < 10; i++)
        {
            
            View mView = LayoutInflater.from(this).inflate(R.layout.xiaojinyin_zhangdan_item, null);
            
            TextView zhangdanjinenum = (TextView)mView.findViewById(R.id.zhangdanjinenum);
            
            zhangdanjinenum.setText(String.valueOf(i));
            mPagersList.add(mView);
            
        }
        
    }
    
    private class MyPagerAdapter extends PagerAdapter
    {
        
        @Override
        public int getCount()
        {
            return mPagersList.size();
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }
        
        public Object instantiateItem(ViewGroup container, int position)
        {
            // TextView textView = pagers.get(position);
            // container.addView(textView);
            // return textView;
            
            container.addView(mPagersList.get(position));
            return mPagersList.get(position);
            
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            /*
             * TextView textView = pagers.get(position);
             * container.removeView(textView);
             */
            
            container.removeView(mPagersList.get(position));
        }
        
        /*
         * @Override
         * public CharSequence getPageTitle(int position) {
         * return titles[position];
         * }
         */
    }
    
    public class MyOnPageChangeListener implements OnPageChangeListener
    {
        
        @Override
        public void onPageSelected(int position)
        {
            
        }
        
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
        {
            // to refresh frameLayout 刷新 布局
            if (viewPagerContainer != null)
            {
                viewPagerContainer.invalidate();
            }
            
            pageid.setText(position + "**" + positionOffset + "**" + positionOffsetPixels);
        }
        
        @Override
        public void onPageScrollStateChanged(int arg0)
        {
        }
    }
    
}

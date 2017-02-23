package cn.wujian_demo.com.galleryviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.TubatuAdapter;
import utils.ScalePageTransformer;
import view.ClipViewPager;
/**
 * Created by suhu on 2016/3/23.
 * description: 画廊式中间放大效果 今天很高兴
 */
public class GalleryViewPagerActivity extends AppCompatActivity {
    private final static float TARGET_HEAP_UTILIZATION = 0.75f;
    private TubatuAdapter mPagerAdapter;
    private ClipViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFindView();
        initData();
    }

    private void initFindView() {

        mViewPager = (ClipViewPager) findViewById(R.id.viewpager);
        /**调节ViewPager的滑动速度**/
        mViewPager.setSpeedScroller(300);

        /**给ViewPager设置缩放动画，这里通过PageTransformer来实现**/
        mViewPager.setPageTransformer(true, new ScalePageTransformer());
        List<String> strList = Arrays.asList("现代", "简约", "欧式", "中式", "美式", "地中海", "东南亚", "日式");

        /**
         * 需要将整个页面的事件分发给ViewPager，不然的话只有ViewPager中间的view能滑动，其他的都不能滑动，
         * 这是肯定的，因为ViewPager总体布局就是中间那一块大小，其他的子布局都跑到ViewPager外面来了
         */
        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        mPagerAdapter = new TubatuAdapter(this,strList);
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.style_xiandai);
        list.add(R.mipmap.style_jianyue);
        list.add(R.mipmap.style_oushi);
        list.add(R.mipmap.style_zhongshi);
        list.add(R.mipmap.style_meishi);
        list.add(R.mipmap.style_dzh);
        list.add(R.mipmap.style_dny);
        list.add(R.mipmap.style_rishi);
        /**这里需要将setOffscreenPageLimit的值设置成数据源的总个数，如果不加这句话，会导致左右切换异常；**/
        mViewPager.setOffscreenPageLimit(list.size());
        mPagerAdapter.addAll(list);
    }
}

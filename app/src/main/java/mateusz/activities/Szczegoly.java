package mateusz.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;

import java.util.List;
import java.util.Vector;

import mateusz.adapters.MyFragmentPagerAdapter;
import mateusz.fragments.ProducentFragment;
import mateusz.fragments.SkladnikWplywFragment;
import mateusz.fragments.SkladnikiFragment;

public class Szczegoly extends AppCompatActivity implements
        TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    private TabHost tabHost;
    private ViewPager viewPager;
    private MyFragmentPagerAdapter myViewPagerAdapter;


    // fake content for tabhost
    class FakeContent implements TabHost.TabContentFactory {
        private final Context mContext;

        public FakeContent(Context context) { mContext = context;}

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumHeight(0);
            v.setMinimumWidth(0);
            return v;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegoly);

        // init tabhost
        this.initializeTabHost(savedInstanceState);
        // init ViewPager
        this.initializeViewPager();

    }

    private void initializeViewPager() {
        List<Fragment> fragments = new Vector<Fragment>();

        fragments.add(new SkladnikiFragment());
        fragments.add(new ProducentFragment());
        fragments.add(new SkladnikWplywFragment());

        this.myViewPagerAdapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);
        this.viewPager = (ViewPager) super.findViewById(R.id.viewPager);
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.setOnPageChangeListener(this);

        onRestart();

    }

    private void initializeTabHost(Bundle args) {

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("Składniki");
        tabSpec.setIndicator(getString(R.string.Składniki));
        tabSpec.setContent(new FakeContent(this));
        tabHost.addTab(tabSpec);

        TabHost.TabSpec tabSpec1;
        tabSpec1 = tabHost.newTabSpec("Producent");
        tabSpec1.setIndicator(getString(R.string.Producent));
        tabSpec1.setContent(new FakeContent(this));
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2;
        tabSpec2 = tabHost.newTabSpec("SkładnikWpływ");
        tabSpec2.setIndicator(getString(R.string.okreslone_skladniki));
        tabSpec2.setContent(new FakeContent(this));
        tabHost.addTab(tabSpec2);

        tabHost.setOnTabChangedListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        this.tabHost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabChanged(String tabId) {

        int pos = this.tabHost.getCurrentTab();
        this.viewPager.setCurrentItem(pos);

        HorizontalScrollView hScrollView = (HorizontalScrollView) findViewById(R.id.hScrollView);
        View tabView = tabHost.getCurrentTabView();
        int scrollPos = tabView.getLeft()
                - (hScrollView.getWidth() - tabView.getWidth()) / 2;
        hScrollView.smoothScrollTo(scrollPos, 0);
    }
}

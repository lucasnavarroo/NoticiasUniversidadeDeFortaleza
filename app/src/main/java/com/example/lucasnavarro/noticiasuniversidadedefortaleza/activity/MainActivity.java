package com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.PageAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    protected PageAdapter mPageAdapter;

    @ViewById
    protected ViewPager container;

    @ViewById
    protected TabLayout tabs;

    @ViewById
    protected Toolbar toolbar;

    @AfterViews
    void afterViews() {
        setSupportActionBar(toolbar);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPageAdapter = new PageAdapter(getSupportFragmentManager());

        container.setAdapter(mPageAdapter);

        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(container));

        container.setOffscreenPageLimit(2);
    }
}

package com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.PageAdapter;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.EsportesFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.EventosFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.GeralFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;

public class MainActivity extends BaseActivity {

    private PageAdapter mPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = findViewById(R.id.container);

        mPageAdapter = new PageAdapter(getSupportFragmentManager(), mViewPager);

        mViewPager.setAdapter(mPageAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

}

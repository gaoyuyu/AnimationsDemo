package com.gaoyy.animationsdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gaoyy.animationsdemo.fragment.OneFragment;

public class ShareElementsWithFragActivity extends AppCompatActivity
{
    private LinearLayout activityShareElementsWithFrag;
    private Toolbar shareFragToolbar;
    private FrameLayout shareContent;

    private void assignViews()
    {
        activityShareElementsWithFrag = (LinearLayout) findViewById(R.id.activity_share_elements_with_frag);
        shareFragToolbar = (Toolbar) findViewById(R.id.share_frag_toolbar);
        shareContent = (FrameLayout) findViewById(R.id.share_content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_elements_with_frag);
        assignViews();
        initToolbar();
        setTransition();
        addFragmentOne();



    }

    private void addFragmentOne()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Slide slide = new Slide();
            slide.setDuration(500);
            OneFragment oneFragment = OneFragment.newInstance();
            oneFragment.setReenterTransition(slide);
            oneFragment.setExitTransition(slide);
            oneFragment.setSharedElementEnterTransition(new ChangeBounds());

            getSupportFragmentManager().beginTransaction().replace(R.id.share_content,oneFragment).commit();
        }

    }

    private void setTransition()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setEnterTransition(explode);
        }
    }

    private void initToolbar()
    {
        shareFragToolbar.setTitle("Share Elements with fragment");
        setSupportActionBar(shareFragToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    finishAfterTransition();
                }
                else
                {
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

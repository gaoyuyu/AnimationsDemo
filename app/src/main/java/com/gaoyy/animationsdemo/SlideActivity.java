package com.gaoyy.animationsdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class SlideActivity extends AppCompatActivity
{
    private RelativeLayout activityExplodeCode;
    private Toolbar slideToolbar;

    private int type;

    private void assignViews()
    {
        activityExplodeCode = (RelativeLayout) findViewById(R.id.activity_explode_code);
        slideToolbar = (Toolbar) findViewById(R.id.slide_toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        assignViews();
        initToolbar();
        setTransition();
    }

    private void initToolbar()
    {
        slideToolbar.setTitle("Slide");
        setSupportActionBar(slideToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    private void setTransition()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (type == 1)
            {
                Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_right);
                transition.setDuration(500);
                getWindow().setEnterTransition(transition);
                getWindow().setExitTransition(transition);
            }
            else
            {
                Slide slide = new Slide();
                slide.setSlideEdge(Gravity.RIGHT);
                slide.setDuration(500);
                getWindow().setEnterTransition(slide);
                getWindow().setExitTransition(slide);
            }
        }
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

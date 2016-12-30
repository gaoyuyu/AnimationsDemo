package com.gaoyy.animationsdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class TransitionActivity extends AppCompatActivity
{
    private static final String LOG_TAG = TransitionActivity.class.getSimpleName();
    private LinearLayout activityTransition;
    private Toolbar transitionToolbar;

    private void assignViews()
    {
        activityTransition = (LinearLayout) findViewById(R.id.activity_transition);
        transitionToolbar = (Toolbar) findViewById(R.id.transition_toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        setEnterAnimTransition();
        assignViews();
        initToolbar();
    }

    private void initToolbar()
    {
        setSupportActionBar(transitionToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setEnterAnimTransition()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Fade fade = new Fade();
            getWindow().setExitTransition(fade);
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
                    Log.i(LOG_TAG,"finishAfterTransition");
                    finishAfterTransition();
                }
                else
                {
                    Log.i(LOG_TAG,"finish");
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

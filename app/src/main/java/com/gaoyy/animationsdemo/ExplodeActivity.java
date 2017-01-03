package com.gaoyy.animationsdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ExplodeActivity extends AppCompatActivity
{
    private RelativeLayout activityExplodeCode;
    private Toolbar explodeCodeToolbar;
    private Button button;
    private int type;

    private void assignViews()
    {
        activityExplodeCode = (RelativeLayout) findViewById(R.id.activity_explode_code);
        explodeCodeToolbar = (Toolbar) findViewById(R.id.explode_code_toolbar);
        button = (Button) findViewById(R.id.button);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explode_code);
        assignViews();
        type = getIntent().getIntExtra("type",0);
        initToolbar();
        setTransition();
    }

    private void setTransition()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if(type == 1)
            {
                Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                transition.setDuration(500);
                getWindow().setEnterTransition(transition);
                getWindow().setExitTransition(transition);
            }
            else
            {
                Explode explode = new Explode();
                explode.setDuration(500);
                getWindow().setEnterTransition(explode);
                getWindow().setExitTransition(explode);
            }
        }
    }

    private void initToolbar()
    {
        explodeCodeToolbar.setTitle("Explode Code");
        setSupportActionBar(explodeCodeToolbar);
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

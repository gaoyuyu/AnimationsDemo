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
import android.widget.TextView;

public class ExplodeActivity extends AppCompatActivity
{
    private int type;
    private RelativeLayout activityExplodeCode;
    private Toolbar explodeToolbar;
    private Button button;
    private TextView textView2;

    private void assignViews()
    {
        activityExplodeCode = (RelativeLayout) findViewById(R.id.activity_explode_code);
        explodeToolbar = (Toolbar) findViewById(R.id.explode_toolbar);
        button = (Button) findViewById(R.id.button);
        textView2 = (TextView) findViewById(R.id.textView2);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explode);
        assignViews();
        type = getIntent().getIntExtra("type", 0);
        initToolbar();
        setTransition();
    }

    private void setTransition()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (type == 1)
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
        explodeToolbar.setTitle("Explode");
        setSupportActionBar(explodeToolbar);
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

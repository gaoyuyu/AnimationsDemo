package com.gaoyy.animationsdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TransitionActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String LOG_TAG = TransitionActivity.class.getSimpleName();
    private LinearLayout activityTransition;
    private Toolbar transitionToolbar;
    private TextView textView;
    private Button explodeCodeBtn;
    private Button slideCodeBtn;
    private Button slideXmlBtn;
    private Button exitBtn;
    private Button exitTransitionBtn;
    private Button explodeXmlBtn;

    private void assignViews()
    {
        activityTransition = (LinearLayout) findViewById(R.id.activity_transition);
        transitionToolbar = (Toolbar) findViewById(R.id.transition_toolbar);
        textView = (TextView) findViewById(R.id.textView);
        explodeCodeBtn = (Button) findViewById(R.id.explode_code_btn);
        slideCodeBtn = (Button) findViewById(R.id.slide_code_btn);
        slideXmlBtn = (Button) findViewById(R.id.slide_xml_btn);
        exitBtn = (Button) findViewById(R.id.exit_btn);
        exitTransitionBtn = (Button) findViewById(R.id.exit_transition_btn);
        explodeXmlBtn = (Button) findViewById(R.id.explode_xml_btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);
        setEnterAnimTransition();
        assignViews();
        initToolbar();
        setListener();
    }

    private void setListener()
    {
        explodeCodeBtn.setOnClickListener(this);
        explodeXmlBtn.setOnClickListener(this);
        slideCodeBtn.setOnClickListener(this);
        slideXmlBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
        exitTransitionBtn.setOnClickListener(this);
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
                    Log.i(LOG_TAG, "finishAfterTransition");
                    finishAfterTransition();
                }
                else
                {
                    Log.i(LOG_TAG, "finish");
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        Intent intent = new Intent();
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        switch (id)
        {
            case R.id.explode_code_btn:
                intent.setClass(TransitionActivity.this,ExplodeActivity.class);
                startActivity(intent, transitionActivityOptions.toBundle());
                break;
            case R.id.explode_xml_btn:
                intent.putExtra("type",1);
                intent.setClass(TransitionActivity.this,ExplodeActivity.class);
                startActivity(intent, transitionActivityOptions.toBundle());
                break;
            case R.id.slide_code_btn:
                break;
            case R.id.slide_xml_btn:
                break;
            case R.id.exit_btn:
                break;
            case R.id.exit_transition_btn:
                break;
        }
    }
}

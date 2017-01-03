package com.gaoyy.animationsdemo;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ShareElementsActivity extends AppCompatActivity implements View.OnClickListener
{
    private LinearLayout activityShareElements;
    private Toolbar shareToolbar;
    private ImageView shareCircle;
    private Button shareBtn;

    private void assignViews()
    {
        activityShareElements = (LinearLayout) findViewById(R.id.activity_share_elements);
        shareToolbar = (Toolbar) findViewById(R.id.share_toolbar);
        shareCircle = (ImageView) findViewById(R.id.share_circle);
        shareBtn = (Button)findViewById(R.id.share_btn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_elements);
        assignViews();
        initToolbar();

        shareBtn.setOnClickListener(this);
    }

    private void initToolbar()
    {
        shareToolbar.setTitle("Share Elements(no fragment)");
        setSupportActionBar(shareToolbar);
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

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.share_btn:

                break;
        }

    }
}

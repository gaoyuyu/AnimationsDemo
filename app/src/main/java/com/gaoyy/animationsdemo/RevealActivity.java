package com.gaoyy.animationsdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RevealActivity extends AppCompatActivity implements View.OnClickListener
{
    private LinearLayout activityReveal;
    private Toolbar revealToolbar;
    private ImageView revealTarget;
    private RelativeLayout revealRel;
    private TextView textView3;
    private ImageView revealBlue;
    private ImageView revealGreen;
    private ImageView revealOrange;
    private ImageView revealRed;

    private void assignViews() {
        activityReveal = (LinearLayout) findViewById(R.id.activity_reveal);
        revealToolbar = (Toolbar) findViewById(R.id.reveal_toolbar);
        revealTarget = (ImageView) findViewById(R.id.reveal_target);
        revealRel = (RelativeLayout) findViewById(R.id.reveal_rel);
        textView3 = (TextView) findViewById(R.id.textView3);
        revealBlue = (ImageView) findViewById(R.id.reveal_blue);
        revealGreen = (ImageView) findViewById(R.id.reveal_green);
        revealOrange = (ImageView) findViewById(R.id.reveal_orange);
        revealRed = (ImageView) findViewById(R.id.reveal_red);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        assignViews();
        initToolbar();
        setTransition();
        setListener();


    }

    private void setListener()
    {
        revealBlue.setOnClickListener(this);
        revealOrange.setOnClickListener(this);
        revealGreen.setOnClickListener(this);
        revealRed.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                {
                    int radius = Math.max(revealRel.getWidth(),revealRel.getHeight());
                    revealRel.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                    {
                        Animator anim = ViewAnimationUtils.createCircularReveal(revealRel,(int)motionEvent.getRawX(),(int)motionEvent.getRawY(),0,radius);
                        anim.setDuration(500);
                        anim.setInterpolator(new AccelerateInterpolator());
                        anim.start();
                    }
                }
                return false;
            }
        });
    }

    private void setTransition()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            final ChangeBounds ch = new ChangeBounds();
            getWindow().setSharedElementEnterTransition(ch);
            ch.addListener(new Transition.TransitionListener()
            {
                @Override
                public void onTransitionStart(Transition transition)
                {
                }

                @Override
                public void onTransitionEnd(Transition transition)
                {
                    ch.removeListener(this);
                    revealTarget.setVisibility(View.GONE);
                    animateRevealShow(revealToolbar);
                }

                @Override
                public void onTransitionCancel(Transition transition)
                {

                }

                @Override
                public void onTransitionPause(Transition transition)
                {

                }

                @Override
                public void onTransitionResume(Transition transition)
                {

                }
            });


            final Fade fade = new Fade();
            fade.setDuration(500);
            fade.setStartDelay(500);
            getWindow().setReturnTransition(fade);
            fade.addListener(new Transition.TransitionListener()
            {
                @Override
                public void onTransitionStart(Transition transition)
                {
                    fade.removeListener(this);
                    animateRevealHide(revealToolbar);
                }

                @Override
                public void onTransitionEnd(Transition transition)
                {

                }

                @Override
                public void onTransitionCancel(Transition transition)
                {

                }

                @Override
                public void onTransitionPause(Transition transition)
                {

                }

                @Override
                public void onTransitionResume(Transition transition)
                {

                }
            });



        }
    }

    private void animateRevealHide(final View rootView)
    {
        int cx = (rootView.getLeft()+rootView.getRight())/2;
        int cy = (rootView.getTop()+rootView.getBottom())/2;
        int radius = Math.max(rootView.getWidth(),rootView.getHeight());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Animator anim = ViewAnimationUtils.createCircularReveal(rootView,cx,cy,radius,0);
            rootView.setVisibility(View.VISIBLE);
            anim.setDuration(500);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.start();
            anim.addListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    super.onAnimationEnd(animation);
                    rootView.setVisibility(View.GONE);
                }
            });
        }
    }

    private void animateRevealShow(View rootView)
    {
        int cx = (rootView.getLeft()+rootView.getRight())/2;
        int cy = (rootView.getTop()+rootView.getBottom())/2;
        int radius = Math.max(rootView.getWidth(),rootView.getHeight());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Animator anim = ViewAnimationUtils.createCircularReveal(rootView,cx,cy,0,radius);
            rootView.setVisibility(View.VISIBLE);
            revealTarget.setVisibility(View.GONE);
            anim.setDuration(500);
            anim.setInterpolator(new AccelerateInterpolator());
            anim.start();
        }
    }

    private void initToolbar()
    {
        revealToolbar.setTitle("Reveal");
        setSupportActionBar(revealToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        int radius = (int)Math.hypot((double)revealRel.getWidth(),(double)revealRel.getHeight());
        switch (id)
        {
            case R.id.reveal_blue:

                revealRel.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    Animator anim = ViewAnimationUtils.createCircularReveal(revealRel,0,0,0,radius);
                    anim.setDuration(500);
                    anim.setInterpolator(new AccelerateInterpolator());
                    anim.start();
                }
                break;
            case R.id.reveal_green:
                revealRel.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    Animator anim = ViewAnimationUtils.createCircularReveal(revealRel,revealRel.getWidth()/2,0,0,radius);
                    anim.setDuration(500);
                    anim.setInterpolator(new AccelerateInterpolator());
                    anim.start();
                }

                break;
            case R.id.reveal_orange:
                revealRel.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    Animator anim = ViewAnimationUtils.createCircularReveal(revealRel,revealRel.getWidth()/2,revealRel.getHeight()/2,0,radius);
                    anim.setDuration(500);
                    anim.setInterpolator(new AccelerateInterpolator());
                    anim.start();
                }
                break;

        }
    }
}

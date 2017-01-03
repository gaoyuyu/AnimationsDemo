package com.gaoyy.animationsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.gaoyy.animationsdemo.adapter.Simple;
import com.gaoyy.animationsdemo.adapter.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SimpleListAdapter.OnItemClickListener
{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private Toolbar mainToolbar;
    private RecyclerView mainRv;
    private SimpleListAdapter simpleListAdapter;

    private void assignViews() {
        mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mainRv = (RecyclerView) findViewById(R.id.main_rv);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setExitAnimTransition();
        assignViews();
        initToolbar();
        initRecyclerView();
    }

    private void setExitAnimTransition()
    {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Slide slideTransition = new Slide();
            slideTransition.setSlideEdge(Gravity.LEFT);
            slideTransition.setDuration(500);
            //Re-enter(重新进入)动画在重新回到该Activity时执行
            getWindow().setReenterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
    }

    private void initRecyclerView()
    {
        List<Simple> simpleList = new ArrayList<>();
        simpleList.add(new Simple(R.drawable.blue_24dp,"Transition"));
        simpleList.add(new Simple(R.drawable.green_24dp,"Share Elements"));
        simpleList.add(new Simple(R.drawable.orange_24dp,"Transition3"));
        simpleList.add(new Simple(R.drawable.red_24dp,"Transition4"));

        simpleListAdapter = new SimpleListAdapter(this,simpleList);
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainRv.setAdapter(simpleListAdapter);
        simpleListAdapter.setOnItemClickListener(this);
    }

    private void initToolbar()
    {
        mainToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mainToolbar);
    }

    @Override
    public void onItemClick(View view, int position)
    {
        Intent intent = new Intent();
        switch (position)
        {
            case 0:
                Log.i(LOG_TAG,"finishAfterTransition");
                final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
                intent.setClass(MainActivity.this,TransitionActivity.class);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
                startActivity(intent, transitionActivityOptions.toBundle());
                break;
            case 1:
                View circle = view.findViewById(R.id.item_circle);
                View text = view.findViewById(R.id.item_tv);
                final Pair<View, String>[] pairs1 = TransitionHelper.createSafeTransitionParticipants(this, true,
                        new Pair<>(circle,"circle"),
                        new Pair<>(text,"text"));
                intent.setClass(MainActivity.this,ShareElementsActivity.class);
                ActivityOptionsCompat transitionActivityOptions1 = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs1);
                startActivity(intent, transitionActivityOptions1.toBundle());
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}

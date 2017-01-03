package com.gaoyy.animationsdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gaoyy.animationsdemo.R;

public class OneFragment extends Fragment implements View.OnClickListener
{
    private View rootView;
    private ImageView oneCircle;
    private Button oneOverlapFalseBtn;
    private Button oneOverlapTrueBtn;

    private void assignViews(View rootView)
    {
        oneCircle = (ImageView) rootView.findViewById(R.id.one_circle);
        oneOverlapFalseBtn = (Button) rootView.findViewById(R.id.one_overlap_false_btn);
        oneOverlapTrueBtn = (Button) rootView.findViewById(R.id.one_overlap_true_btn);
    }


    public OneFragment()
    {
        // Required empty public constructor
    }

    public static OneFragment newInstance()
    {
        OneFragment fragment = new OneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_one, container, false);
        assignViews(rootView);
        setListener();
        return rootView;
    }

    private void setListener()
    {
        oneOverlapTrueBtn.setOnClickListener(this);
        oneOverlapFalseBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();
        switch (id)
        {
            case R.id.one_overlap_true_btn:
                addTwoFragment(true);
                break;
            case R.id.one_overlap_false_btn:
                addTwoFragment(false);
                break;
        }
    }


    private void addTwoFragment(boolean overlap)
    {
        TwoFragment twoFragment = TwoFragment.newInstance();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
        {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.LEFT);
            slide.setDuration(500);
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(500);

            twoFragment.setEnterTransition(slide);
            twoFragment.setAllowEnterTransitionOverlap(overlap);
            twoFragment.setAllowReturnTransitionOverlap(overlap);
            twoFragment.setSharedElementEnterTransition(changeBounds);


            getFragmentManager().beginTransaction().replace(R.id.share_content,twoFragment).addToBackStack(null)
                    .addSharedElement(oneCircle,"two")
                    .commit();

        }
    }
}

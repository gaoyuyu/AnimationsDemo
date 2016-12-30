package com.gaoyy.animationsdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gaoyy.animationsdemo.R;

import java.util.List;

public class SimpleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private LayoutInflater inflater;
    private List<Simple> data;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener = listener;
    }


    public SimpleListAdapter(Context context, List<Simple> data)
    {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View rootView = inflater.inflate(R.layout.item_list, parent, false);
        SimpleViewHolder simpleViewHolder = new SimpleViewHolder(rootView);
        return simpleViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        SimpleViewHolder simpleViewHolder = (SimpleViewHolder) holder;
        Simple simple = data.get(position);

        simpleViewHolder.itemCircle.setImageDrawable(context.getResources().getDrawable(simple.getDrawable()));
        simpleViewHolder.itemTv.setText(simple.getText());

        if (onItemClickListener != null)
        {
            simpleViewHolder.itemLayout.setOnClickListener(new BasicOnClickListener(simpleViewHolder));
        }
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }


    public static class SimpleViewHolder extends RecyclerView.ViewHolder
    {
        private RelativeLayout itemLayout;
        private ImageView itemCircle;
        private TextView itemTv;

        public SimpleViewHolder(View itemView)
        {
            super(itemView);
            itemLayout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
            itemCircle = (ImageView) itemView.findViewById(R.id.item_circle);
            itemTv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }


    private class BasicOnClickListener implements View.OnClickListener
    {
        private SimpleViewHolder simpleViewHolder;

        public BasicOnClickListener(SimpleViewHolder simpleViewHolder)
        {
            this.simpleViewHolder = simpleViewHolder;
        }

        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.item_layout:
                    onItemClickListener.onItemClick(simpleViewHolder.itemLayout, simpleViewHolder.getLayoutPosition());
                    break;
            }
        }
    }

}

package com.gaoyy.animationsdemo.adapter;

/**
 * Created by gaoyy on 2016/12/30.
 */
public class Simple
{
    private int drawable;
    private String text;

    public Simple(int drawable, String text)
    {
        this.drawable = drawable;
        this.text = text;
    }

    public int getDrawable()
    {
        return drawable;
    }

    public void setDrawable(int drawable)
    {
        this.drawable = drawable;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

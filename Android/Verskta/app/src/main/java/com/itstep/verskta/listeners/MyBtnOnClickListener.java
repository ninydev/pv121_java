package com.itstep.verskta.listeners;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.itstep.verskta.MainActivity;
import com.itstep.verskta.R;

public class MyBtnOnClickListener implements View.OnClickListener
{
    MainActivity activity;
    public MyBtnOnClickListener(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void onClick(View view) {
        int color = ContextCompat.getColor(view.getContext(), R.color.black);
        // view.setBackgroundColor(color);
        this.activity.toast("Hello From Clicker");
    }
}

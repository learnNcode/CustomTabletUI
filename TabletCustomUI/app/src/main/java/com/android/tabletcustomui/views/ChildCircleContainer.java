package com.android.tabletcustomui.views;
/*
 * Copyright 2015 - learnNcode (learnncode@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.android.tabletcustomui.R;
import com.android.tabletcustomui.interfaces.OnChildClick;

public class ChildCircleContainer extends FrameLayout implements View.OnClickListener{


    private LayoutInflater layoutInflater;
    private CircleTitleView circleTitleView;
    private OnChildClick onChildClick;
    private ChildCircleView childCircleView;
    private InnerCircle innerCircle;
    private int paddingValue = 20;

    public ChildCircleContainer(Context context, int width, OnChildClick onChildClick) {
        super(context);
        width = width ;/*- CircleUtils.getStatusBarHeight(context);*/
        this.onChildClick = onChildClick;
        init(context, null, width);
    }

    public ChildCircleContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs, 0);
    }

    public ChildCircleContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int width) {

       /* LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int)(width / 5 ) , (int)(width / 5 ));
        layoutParams.gravity = Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
        layoutParams.setMargins(10,10,10,10);

        this.setLayoutParams(layoutParams);*/
        this.setOnClickListener(this);

        childCircleView = new ChildCircleView(context, (int) (width / 5  - paddingValue));
        innerCircle = new InnerCircle(context, (int) (width / 5  - paddingValue));

        circleTitleView = new CircleTitleView(context, (int) (width / 5 - paddingValue));
        circleTitleView.setTextColor(getContext().getResources().getColor(R.color.text_circle_color));
        circleTitleView.setTextSize(10);
        circleTitleView.setTypeface(Typeface.DEFAULT,Typeface.BOLD_ITALIC);

        this.addView(childCircleView);
        this.addView(innerCircle);
        this.addView(circleTitleView);

        ObjectAnimator animation = ObjectAnimator.ofFloat(childCircleView, "rotation", 0.0f, 360f);
        animation.setDuration(5000);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();

        ObjectAnimator animationReverse = ObjectAnimator.ofFloat(innerCircle, "rotation", 360f, 0.0f);
        animationReverse.setDuration(5000);
        animationReverse.setRepeatCount(ObjectAnimator.INFINITE);
        animationReverse.setInterpolator(new LinearInterpolator());
        animationReverse.start();

    }

    @Override
    public void onClick(View v) {
        if(v.getTag() != null){
            onChildClick.onChildClick(v.getTag().toString());
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(this, "scaleX", 0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(this, "scaleY", 0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1f);

        scaleDownX.setDuration(1000);
        scaleDownY.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();

        scaleDown.playTogether(scaleDownX,scaleDownY);
        scaleDown.start();
    }

    public void setText(String text){
        if(circleTitleView != null){
            this.setTag(text);
            circleTitleView.setText(text);
            circleTitleView.invalidate();
        }
    }

    public void setTextSize(float size){
        if(circleTitleView != null){
            circleTitleView.setTextSize(size);
            circleTitleView.invalidate();
        }
    }

    public void setTextColor(String color){
        if(circleTitleView != null){
            circleTitleView.setTextColor(Color.parseColor("#" + color));
            circleTitleView.invalidate();
        }
    }

    public void setOuterRingColor(String color){
        if(childCircleView != null){
            childCircleView.setColor("#" + color);
            childCircleView.invalidate();
        }
    }

    public void setInnerRingColor(String color){
        if(innerCircle != null){
            innerCircle.setColor("#"+color);
            innerCircle.invalidate();
        }
    }
}

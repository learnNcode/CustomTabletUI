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
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.android.tabletcustomui.interfaces.OnChildClick;
import com.android.tabletcustomui.utils.CircleAppConstants;

public class CircleVerticalContainer extends LinearLayoutCompat {
    private int childCount = -1;
    private int tempChildCount = 0;
    private OnChildClick onChildClick;
    public CircleVerticalContainer(Context context, int childCount, OnChildClick onChildClick) {
        super(context);
        this.onChildClick = onChildClick;
        init(context, childCount);
    }

    private void init(Context context, int childCount) {
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        this.setLayoutParams(layoutParams);

        this.childCount = childCount;
        this.setOrientation(LinearLayoutCompat.VERTICAL);

        if (this.childCount > 0) {
            LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams((CircleAppConstants.viewHeight)/ 5, (CircleAppConstants.viewHeight) / 5);
            childParams.gravity = Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
            for(int i = 0; i< this.childCount; i++){
                ChildCircleContainer childCircleContainer = new ChildCircleContainer(getContext(), (CircleAppConstants.viewHeight),onChildClick);
                childCircleContainer.setPadding(10,10,10,10);
                childCircleContainer.setLayoutParams(childParams);
                this.addView(childCircleContainer);
            }

        }
    }

    private void getChildAndAnimate(){
        if(tempChildCount != this.childCount){
            ChildCircleContainer childCircleContainer = (ChildCircleContainer) this.getChildAt(tempChildCount);
            childCircleContainer.setVisibility(VISIBLE);
            ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(5000);
            anim.setFillAfter(true);
            childCircleContainer.startAnimation(anim);
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    tempChildCount++;
                    getChildAndAnimate();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }


    }


}

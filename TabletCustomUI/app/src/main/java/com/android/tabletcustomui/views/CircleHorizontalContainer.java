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
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import com.android.tabletcustomui.interfaces.OnChildClick;
import com.android.tabletcustomui.utils.CircleAppConstants;
import com.android.tabletcustomui.utils.CircleUtils;

public class CircleHorizontalContainer extends LinearLayoutCompat {

    private Context mContext;
    private OnChildClick onChildClick;
    public CircleHorizontalContainer(Context context, int childCount, OnChildClick onChildClick) {
        super(context);
        this.onChildClick = onChildClick;
        init(context, childCount);
    }

    private void init(Context context, int childCount) {
        this.mContext = context;
        if (CircleAppConstants.viewHeight == 0) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            CircleAppConstants.viewWidth = metrics.widthPixels;
            CircleAppConstants.viewHeight = metrics.heightPixels;
        }

        LeftCircleContainer leftCircleContainer = new LeftCircleContainer(context, (int) (CircleUtils.roundEven(CircleAppConstants.viewHeight * 0.7)));

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        this.addView(leftCircleContainer);
        recursiveAdd(childCount);
    }

    private void recursiveAdd(int childCount) {
        if(childCount > CircleAppConstants.MAX_CHILD_PER_COLUMN){
            int loopCounter = childCount / CircleAppConstants.MAX_CHILD_PER_COLUMN;
            int modValue = childCount % CircleAppConstants.MAX_CHILD_PER_COLUMN;

            if(modValue > 0){
                addChild(true, loopCounter);
                recursiveAdd(modValue);
            }else{
                addChild(true, loopCounter);
            }

        }else{
            addChild(false, childCount);
        }
    }

    private void addChild(boolean loop, int value){
        if(loop && value > 0){

            CircleVerticalContainer circleVerticalContainer = new CircleVerticalContainer(mContext, CircleAppConstants.MAX_CHILD_PER_COLUMN, onChildClick);
            this.addView(circleVerticalContainer);
            addChild(true, --value);
        }else{
            if(value > 0){
                CircleVerticalContainer circleVerticalContainer = new CircleVerticalContainer(mContext, value, onChildClick);
                this.addView(circleVerticalContainer);
            }
        }
    }

    public ChildCircleContainer getChildAtPosition(int i) {

        int childCount = this.getChildCount();

        if(childCount > 0){
            int tempChildCount = (i % CircleAppConstants.MAX_CHILD_PER_COLUMN)  ==  0 ? i/CircleAppConstants.MAX_CHILD_PER_COLUMN : i/CircleAppConstants.MAX_CHILD_PER_COLUMN +1;

            CircleVerticalContainer circleVerticalContainer = (CircleVerticalContainer) this.getChildAt(tempChildCount);
            return (ChildCircleContainer) circleVerticalContainer.getChildAt(CircleAppConstants.MAX_CHILD_PER_COLUMN - 1 - (tempChildCount * CircleAppConstants.MAX_CHILD_PER_COLUMN - i));
        }

        return null;
    }

    public LeftCircleContainer getAppNameView(){
        LeftCircleContainer leftCircleContainer = (LeftCircleContainer) this.getChildAt(0);
        if (leftCircleContainer != null){
            return leftCircleContainer;
        }
        return null;
    }
}

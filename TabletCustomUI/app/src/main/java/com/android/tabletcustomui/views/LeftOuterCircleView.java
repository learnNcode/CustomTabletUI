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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.android.tabletcustomui.R;
import com.android.tabletcustomui.utils.CircleUtils;

public class LeftOuterCircleView extends View {
    private RectF mOval;
    private RectF mOvalInner;
    private Paint mOuterCirclePaint;
    private Paint mInnerCirclePaint;
    private Paint mPaintClip;
    private float ovalsDiff;
    private Path clipPath;
    private PointF pointF;
    private int width;
    private int radius;
    private boolean isInternal;

    public LeftOuterCircleView(Context context, int width, boolean isInternal) {
        super(context);
        this.isInternal = isInternal;
        init(width);
    }

    private void init(int value) {

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
        layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
        this.setLayoutParams(layoutParams);

        width = value;
        ovalsDiff = 4;
        radius = width/2;
        if (isInternal){
        }else{
        }

        mOval = new RectF();
        mOval.set(0, 0, width, width);
        mOvalInner = new RectF(ovalsDiff, ovalsDiff, width - ovalsDiff , width - ovalsDiff);
        clipPath = new Path();
        mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setColor(getContext().getResources().getColor(android.R.color.white));
        mOuterCirclePaint.setAntiAlias(true);

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        mInnerCirclePaint.setAntiAlias(true);

        mPaintClip = new Paint();
        mPaintClip.setColor(Color.WHITE);
        mPaintClip.setAntiAlias(true);

        pointF = new PointF(mOval.centerX(),mOval.centerY());

        clipPath.addCircle(pointF.x, pointF.y, radius-ovalsDiff, Path.Direction.CW);
        this.setBackgroundColor(Color.TRANSPARENT);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

        CircleUtils.drawArc(canvas, pointF, radius, 15, 45, mOuterCirclePaint);
        CircleUtils.drawArc(canvas, pointF, radius, 105, 45, mOuterCirclePaint);
        CircleUtils.drawArc(canvas, pointF, radius, 195, 45, mOuterCirclePaint);
        CircleUtils.drawArc(canvas, pointF, radius, 285, 45, mOuterCirclePaint);

    }


}

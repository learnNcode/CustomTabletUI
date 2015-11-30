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
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.android.tabletcustomui.R;

public class LeftOuterInnerCircleView extends View {
    private int mProgress = 0;
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

    public LeftOuterInnerCircleView(Context context, int width, boolean isInternal) {
        super(context);
        this.isInternal = isInternal;
        init(null, width);
    }

    public LeftOuterInnerCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LeftOuterInnerCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int value) {
        width = value;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width,width);
        layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
        this.setLayoutParams(layoutParams);

        ovalsDiff = 6;
        radius = width/2;
        if (isInternal){
        }else{
        }

        mOval = new RectF();
        mOval.set(0, 0, width, width);
        mOvalInner = new RectF();
        mOvalInner.set(20, 20, width - 20, width - 20);

        mOuterCirclePaint = new Paint();
        mOuterCirclePaint.setColor(getContext().getResources().getColor(R.color.circle_light_blue));
        mOuterCirclePaint.setAntiAlias(true);

        mInnerCirclePaint = new Paint();
        mInnerCirclePaint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        mInnerCirclePaint.setAntiAlias(true);

        mPaintClip = new Paint();
        mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_light_blue));
        mPaintClip.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        mPaintClip.setAntiAlias(true);

        pointF = new PointF(mOval.centerX(),mOval.centerY());
        clipPath = new Path();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        clipPath.addCircle(pointF.x, pointF.y, (float) (radius * 0.9), Path.Direction.CW);
        canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

        canvas.drawArc(mOval, 330, 10, true, mPaintClip);
        canvas.drawArc(mOval, 342, 20, true, mPaintClip);
        canvas.drawArc(mOval, 4, 10, true, mPaintClip);

        canvas.drawArc(mOval, 240, 10, true, mPaintClip);
        canvas.drawArc(mOval,252, 20,true, mPaintClip);
        canvas.drawArc(mOval,275, 10,true, mPaintClip);

        canvas.drawArc(mOval, 150, 10, true, mPaintClip);
        canvas.drawArc(mOval,162, 20,true, mPaintClip);
        canvas.drawArc(mOval,185, 10,true, mPaintClip);

        canvas.drawArc(mOval, 60, 10, true, mPaintClip);
        canvas.drawArc(mOval,72, 20,true, mPaintClip);
        canvas.drawArc(mOval,95, 10,true, mPaintClip);

    }


}

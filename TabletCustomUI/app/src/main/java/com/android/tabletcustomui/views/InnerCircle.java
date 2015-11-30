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
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import com.android.tabletcustomui.R;
import com.android.tabletcustomui.utils.CircleUtils;


public class InnerCircle extends View {
    private RectF mOval;
    private PointF pointF;
    private Path clipPath;
    private int ovalsDiff;
    private int radius;
    private Paint mPaintClip;

    public InnerCircle(Context context, int width) {
        super(context);
        init(null, width);
    }

    private void init(AttributeSet attrs, int width) {
        ovalsDiff = 15;
        radius = (width- ovalsDiff)/2;
        mOval = new RectF();
        mOval.set(ovalsDiff, ovalsDiff,width -ovalsDiff, width - ovalsDiff);
        clipPath = new Path();

        mPaintClip = new Paint();
        mPaintClip.setStyle(Paint.Style.FILL);
        mPaintClip.setColor(getContext().getResources().getColor(R.color.inner_circle_color));
        mPaintClip.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        mPaintClip.setAntiAlias(true);

        pointF = new PointF(mOval.centerX(),mOval.centerY());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        clipPath.addCircle(pointF.x, pointF.y, radius - 4, Path.Direction.CW);
        canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

        CircleUtils.drawArc(canvas, pointF, radius, 15, 60, mPaintClip);
        CircleUtils.drawArc(canvas, pointF, radius, 105, 60, mPaintClip);
        CircleUtils.drawArc(canvas, pointF, radius, 195, 60, mPaintClip);
        CircleUtils.drawArc(canvas, pointF, radius, 285, 60, mPaintClip);

    }


    public void setColor(String s) {
        mPaintClip.setColor(Color.parseColor(s));
        invalidate();
    }
}

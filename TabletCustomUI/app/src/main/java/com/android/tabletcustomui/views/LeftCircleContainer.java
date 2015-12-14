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
import android.animation.ObjectAnimator;
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
import android.graphics.Typeface;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.android.tabletcustomui.R;
import com.android.tabletcustomui.utils.CircleUtils;

public class LeftCircleContainer extends FrameLayout {


    private CircleTitleView circleTitleView;

    public LeftCircleContainer(Context context, int width) {
        super(context);
        width -= CircleUtils.getStatusBarHeight(context);
        init(context, width);
    }

    private void init(Context context, int width) {

        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams((int)(width) , (int)(width));
        layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
        this.setLayoutParams(layoutParams);

        LeftOuterCircleView leftOuterCircleView1 = new LeftOuterCircleView(context, width , false);
        LeftOuterCircleView leftOuterCircleView2 = new LeftOuterCircleView(context, (width - 40 ), true);

        circleTitleView = new CircleTitleView(context, (int) (width));
        circleTitleView.setTextColor(getContext().getResources().getColor(android.R.color.white));
        circleTitleView.setTextSize(20);
        circleTitleView.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
        circleTitleView.setText("App Name");

        this.addView(leftOuterCircleView1);
        this.addView(leftOuterCircleView2);
        addCircle(1, width - 78, (float) 0.0, 0.85);
        addCircle(2,width - 150, (float) 0.0, 0.97);
        addCircle(3, width - 170, (float) 0.0, 0.97);
        addCircle(4, width - 190, (float) 0.0, 0.98);
        addCircle(5, width - 210, (float) 0.0, 0.98);
        addCircle(6, width - 230, (float) 0.0, 0.85);
        addCircle(7, width - 280, (float) 0.0, 0.85);
        addCircle(0,width - 35, (float) 0.0, 0.9);
        addCircle(0,width - 92, (float)0.1, 0.9);

        this.addView(circleTitleView);

        animateClockWise(leftOuterCircleView1);

        animateAntiClockWise(leftOuterCircleView2);

    }

    private void addCircle(int position, final int value, float diff, double ratio){
        final int radius = value / 2;
        final RectF mOval = new RectF();
        mOval.set(0 + diff, 0 + diff, value - diff, value - diff);

        final Paint mPaintClip = new Paint();
        mPaintClip.setStyle(Paint.Style.FILL);
        mPaintClip.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.OVERLAY));
        mPaintClip.setAntiAlias(true);

        final PointF pointF = new PointF(mOval.centerX(), mOval.centerY());
        final Path clipPath = new Path();
        clipPath.addCircle(pointF.x, pointF.y, (float) (radius * ratio), Path.Direction.CW);


        View circleView;
        if (position == 0){
            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_light_blue));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

//                CircleUtils.drawArc(canvas,pointF,radius,330,10,mPaintClip);
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
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);

            animateClockWise(circleView);

        }else if(position == 1){

            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_dark_blue));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawCircle(pointF.x, pointF.y, radius, mPaintClip);
                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);
            animateClockWise(circleView);

        }else if(position == 2){

            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_light_blue));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawArc(mOval, 55, 190, true, mPaintClip);

                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);
            animateClockWise(circleView);

        }else if(position == 3){
            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_light_blue));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawArc(mOval, 240, 190, true, mPaintClip);
                    canvas.drawArc(mOval, 80, 150, true, mPaintClip);
                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);
            animateClockWise(circleView);

        }else if(position == 4){
            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_light_blue));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawArc(mOval, 335, 150, true, mPaintClip);
                    canvas.drawArc(mOval, 140, 180, true, mPaintClip);
                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);
            animateClockWise(circleView);

        }else if(position == 5){
            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_light_grey));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawArc(mOval, 330, 150, true, mPaintClip);
                    canvas.drawArc(mOval, 140, 150, true, mPaintClip);
                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);
            animateClockWise(circleView);

        }else if(position == 6){
            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_purple_color));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawArc(mOval, 290, 5, true, mPaintClip);
                    canvas.drawArc(mOval, 297, 20, true, mPaintClip);
                    canvas.drawArc(mOval, 319, 20, true, mPaintClip);


                    canvas.drawArc(mOval, 60, 15, true, mPaintClip);
                    canvas.drawArc(mOval, 77, 8, true, mPaintClip);

                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);

            animateClockWise(circleView);


        }else if(position == 7){
            mPaintClip.setColor(getContext().getResources().getColor(R.color.circle_dark_blue));
            circleView = new View(getContext()){

                @Override
                protected void onDraw(Canvas canvas) {
                    super.onDraw(canvas);


                    canvas.clipPath(clipPath, Region.Op.DIFFERENCE);

                    canvas.drawArc(mOval, 160, 10, true, mPaintClip);
                    canvas.drawArc(mOval, 172, 30, true, mPaintClip);
                    canvas.drawArc(mOval, 204, 30, true, mPaintClip);
                    canvas.drawArc(mOval, 236, 10, true, mPaintClip);


                    canvas.drawArc(mOval, 20, 20, true, mPaintClip);
                    canvas.drawArc(mOval, 42, 8, true, mPaintClip);

                }
            };

            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(value,value);
            layoutParams.gravity = Gravity.CENTER_VERTICAL| Gravity.CENTER_HORIZONTAL;
            circleView.setLayoutParams(layoutParams);

            this.addView(circleView);
            animateAntiClockWise(circleView);
        }
    }


    private void animateClockWise(View view){
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 360f);
        animation.setDuration(3000);
        animation.setInterpolator(new FastOutSlowInInterpolator());
        animation.start();
    }

    private void animateAntiClockWise(View view){
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(view, "rotation", 360f, 0.0f);
        animation1.setDuration(3000);
        animation1.setInterpolator(new FastOutSlowInInterpolator());
        animation1.start();
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
}

package com.android.tabletcustomui.ui;

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
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.android.tabletcustomui.R;
import com.android.tabletcustomui.interfaces.OnChildClick;
import com.android.tabletcustomui.views.ChildCircleContainer;
import com.android.tabletcustomui.views.CircleHorizontalContainer;

public class CustomTabletUIActivity extends AppCompatActivity implements OnChildClick{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tablet_ui);

        FrameLayout parentContainer = (FrameLayout) findViewById(R.id.parentContainer);

        CircleHorizontalContainer circleHorizontalContainer = new CircleHorizontalContainer(CustomTabletUIActivity.this, 11, CustomTabletUIActivity.this);
        parentContainer.addView(circleHorizontalContainer);

        ChildCircleContainer childCircleContainer1 = circleHorizontalContainer.getChildAtPosition(1);
        childCircleContainer1.setText("Activity 1");
        ChildCircleContainer childCircleContainer2 = circleHorizontalContainer.getChildAtPosition(2);
        childCircleContainer2.setText("Activity 2");
        ChildCircleContainer childCircleContainer3 = circleHorizontalContainer.getChildAtPosition(3);
        childCircleContainer3.setText("Activity 3");
        ChildCircleContainer childCircleContainer4 = circleHorizontalContainer.getChildAtPosition(4);
        childCircleContainer4.setText("Activity 4");
        ChildCircleContainer childCircleContainer5 = circleHorizontalContainer.getChildAtPosition(5);
        childCircleContainer5.setText("Activity 5");
        ChildCircleContainer childCircleContainer6 = circleHorizontalContainer.getChildAtPosition(6);
        childCircleContainer6.setText("Activity 6");
        ChildCircleContainer childCircleContainer7 = circleHorizontalContainer.getChildAtPosition(7);
        childCircleContainer7.setText("Activity 7");
        ChildCircleContainer childCircleContainer8 = circleHorizontalContainer.getChildAtPosition(8);
        childCircleContainer8.setText("Activity 8");
        ChildCircleContainer childCircleContainer9 = circleHorizontalContainer.getChildAtPosition(9);
        childCircleContainer9.setText("Activity 9");
        ChildCircleContainer childCircleContainer10 = circleHorizontalContainer.getChildAtPosition(10);
        childCircleContainer10.setText("Activity 10");
        ChildCircleContainer childCircleContainer11 = circleHorizontalContainer.getChildAtPosition(11);
        childCircleContainer11.setText("Activity 11");
        childCircleContainer11.setTextColor("ffffff");
        childCircleContainer11.setOuterRingColor("ffffff");
        childCircleContainer11.setInnerRingColor("ffffff");


    }

    @Override
    public void onChildClick(String childTitle) {
        //For specific click do your stuff this way. You can check the view clicked by comparing the view title as shown below
        /*if(childTitle.equalsIgnoreCase("Activity 1")){

        }else if(childTitle.equalsIgnoreCase("Activity 2")){

        }*/


        startActivity(new Intent(CustomTabletUIActivity.this, ChildSampleActivity.class));
    }
}

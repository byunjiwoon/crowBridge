package com.byun91.crow_bridge.code.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.byun91.crow_bridge.code.Common.G;
import com.byun91.crow_bridge.databinding.ActivityMainBinding;
import com.byun91.crow_bridge.databinding.IncludeSlideMenuBinding;
import com.byun91.crow_bridge.databinding.IncludeGnbBinding;

public class BaseActivity extends AppCompatActivity {

    private IncludeSlideMenuBinding includeSlideMenuBinding;
    private IncludeGnbBinding includeGnbBiding;
    private DrawerLayout dl;
    private Activity currentAct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setMenuDrawer(ActivityMainBinding activityMainBinding) {
        dl = activityMainBinding.dlMain;
        includeSlideMenuBinding = activityMainBinding.includeSlideMenu;
        includeSlideMenuBinding.name.setText(G.userName);
        includeSlideMenuBinding.email.setText(G.userMail);


    }

    public void openMenu(){
        if (!dl.isDrawerOpen(Gravity.LEFT)) {
            dl.openDrawer(Gravity.LEFT);
        }
    }

    public void setGnb(ActivityMainBinding actvitiyMainBinding){
        includeGnbBiding = actvitiyMainBinding.includeGnb;
        includeGnbBiding.btnGnbMy.setOnClickListener(clickGnbItem);

    }

    private View.OnClickListener clickGnbItem = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            if(view == includeGnbBiding.btnGnbMy){
                openMenu();
            }
        }
    };
}

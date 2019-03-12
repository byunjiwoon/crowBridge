package com.byun91.crow_bridge.code.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.byun91.crow_bridge.R;
import com.byun91.crow_bridge.code.Widget.NetworkImageView;
import com.byun91.crow_bridge.databinding.ActivitySplashBinding;
import com.squareup.picasso.Picasso;

public class SplashActivity extends BaseActivity {


    private ActivitySplashBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_splash);



       Picasso.with(SplashActivity.this)
                .load("http://image.sivillage.com/upload/C00001/dspl/banner/90/235/00/190200000024235.jpg")
                .into(b.ivSplash);

    }
}

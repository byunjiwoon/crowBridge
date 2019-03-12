package com.byun91.crow_bridge.code.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.byun91.crow_bridge.R;
import com.byun91.crow_bridge.code.Common.Utils;
import com.byun91.crow_bridge.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public static ActivityMainBinding b;

    private static final int GALLERY_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        b.includeSlideMenu.logout.setOnClickListener(this);
        b.includeSlideMenu.profileImage.setOnClickListener(this);


        setGnb(b);
        setMenuDrawer(b);




    }


    @Override
    public void onClick(View view) {

        if(view == b.includeSlideMenu.logout){
            FirebaseAuth.getInstance().signOut();
            Intent in = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(in);
            finish();

        }

        else if(view == b.includeSlideMenu.profileImage){
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

            startActivityForResult(intent, GALLERY_CODE);

        }





    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_CODE){

           String uri = Utils.getPath(data.getData(),this);
            Log.d("byun", "onActivityResult: " + uri);
        }
    }
}

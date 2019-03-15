package com.byun91.crow_bridge.code.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.byun91.crow_bridge.R;
import com.byun91.crow_bridge.code.Common.G;
import com.byun91.crow_bridge.code.Common.Utils;
import com.byun91.crow_bridge.code.Value.RequestCodes;
import com.byun91.crow_bridge.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.io.File;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public static ActivityMainBinding b;
    private FirebaseStorage storage;

    private final String[] strRequiredTotalPermissions = new String[]{
        //    Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        b.includeSlideMenu.logout.setOnClickListener(this);
        b.includeSlideMenu.profileImage.setOnClickListener(this);

        if(onMyPermissionChk()){

        }

        storage = FirebaseStorage.getInstance();
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

            startActivityForResult(intent, RequestCodes.GALLARY_CODE);

        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RequestCodes.GALLARY_CODE){

            String uri = Utils.getPath(data.getData(),this);

            Uri file = Uri.fromFile(new File(uri.toString()));
            final StorageReference imageRef = storage.getReference().child(G.userMail);
            UploadTask uploadTask = imageRef.putFile(file);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(MainActivity.this, "이미지 업로드에 실패했습니다.", Toast.LENGTH_SHORT).show();

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(MainActivity.this, "이미지 업로드에 성공했습니다.", Toast.LENGTH_SHORT).show();

                    Picasso.with(MainActivity.this).load(imageRef.getDownloadUrl().toString()).into(b.includeSlideMenu.profileImage);

                }
            });

        }
    }

    // 권한 체크
    private boolean onMyPermissionChk() {

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return true;
        }

        ArrayList<String> arrMyDeniedPermissions = new ArrayList<>();

        for (String permission : strRequiredTotalPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                arrMyDeniedPermissions.add(permission);
            }
        }

        if (0 < arrMyDeniedPermissions.size()) {
            String[] strMyDeniedPermissions = arrMyDeniedPermissions.toArray(new String[arrMyDeniedPermissions.size()]);
            ActivityCompat.requestPermissions(this, strMyDeniedPermissions, RequestCodes.MY_PERMISSION_RETURN_CODE);
            return false;
        }
        else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case RequestCodes.MY_PERMISSION_RETURN_CODE:

                if(grantResults.length == 1 &&grantResults[0] < 0)

                break;

        }
    }
}

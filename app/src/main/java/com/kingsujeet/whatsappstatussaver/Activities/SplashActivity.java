package com.kingsujeet.whatsappstatussaver.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.kingsujeet.whatsappstatussaver.R;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);
        textView = findViewById(R.id.text);

//        Dexter.withContext(this)
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
//
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
////                        finish();
//
//                        /* ... */}
//                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                        finish();
//                        Toast.makeText(getApplicationContext(), "You must give Storage permission to the App to work !!", Toast.LENGTH_LONG).show();
//                        /* ... */}
//                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
//                }
//                ).check();

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500); //You can manage the blinking time with this parameter
        anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        textView.startAnimation(anim);
        logo.startAnimation(anim);

        new Handler().postDelayed(new Runnable(){
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else {
                    Dexter.withContext(SplashActivity.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                              @Override public void onPermissionGranted(PermissionGrantedResponse response) {

                                                  startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        finish();

                                                  /* ... */}
                                              @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                                                  Toast.makeText(getApplicationContext(), "You must give Storage permission to the App to work ", Toast.LENGTH_LONG).show();


                                                  if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {


                                                              new Handler().postDelayed(new Runnable() {
                                                                  @Override
                                                                  public void run() {

                                                                      ((ActivityManager) SplashActivity.this.getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();

                                                                  }
                                                              },2000);
                                                               // note: it has a return value!
                                                  } else {
                                                      // use old hacky way, which can be removed
                                                      // once minSdkVersion goes above 19 in a few years.
                                                  }

                                                  finish();
                                                  Toast.makeText(getApplicationContext(), "You must give Storage permission to the App to work !!", Toast.LENGTH_LONG).show();
                                                  /* ... */}
                                              @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                                          }
                            ).check();


                }

            }
        }, 3000);
    }
}
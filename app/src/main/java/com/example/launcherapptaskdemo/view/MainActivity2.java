package com.example.launcherapptaskdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.launcherapptaskdemo.R;


public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (savedInstanceState == null) {
            AboutAppFragment fragment = new AboutAppFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, AboutAppFragment.TAG).commit();
        }
    }
}
package com.mehmetomurerdolu.booking.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mehmetomurerdolu.booking.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.profile_btn).setOnClickListener(this);
        findViewById(R.id.new_entry_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.profile_btn){
            Intent intent = new Intent(this,ProfileActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.new_entry_btn){
            Intent intent = new Intent(this,AddEstateActivity.class);
            startActivity(intent);
        }
    }
}
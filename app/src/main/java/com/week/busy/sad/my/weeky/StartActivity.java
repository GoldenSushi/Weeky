package com.week.busy.sad.my.weeky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

    @OnClick(R.id.start_button)
    public void clickButton (View view) {
        Intent intent = new Intent(StartActivity.this, DaysActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }
}
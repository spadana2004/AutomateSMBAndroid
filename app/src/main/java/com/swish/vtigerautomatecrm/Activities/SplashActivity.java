package com.swish.vtigerautomatecrm.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.swish.vtigerautomatecrm.R;

public class SplashActivity extends AppCompatActivity {
    Button letsStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        getSupportActionBar().hide();
        letsStart=(Button)findViewById(R.id.splshbutton);
        letsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}

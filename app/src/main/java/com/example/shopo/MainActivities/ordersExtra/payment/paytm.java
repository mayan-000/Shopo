package com.example.shopo.MainActivities.ordersExtra.payment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.shopo.R;

public class paytm extends AppCompatActivity {

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);
        setActionBar();

        login = findViewById(R.id.PaytmButton);

        login.setOnClickListener(v -> {
            Intent i =new Intent(paytm.this, paytm_1.class);
            i.putExtra("price",getIntent().getIntExtra("price",0));
            i.putExtra("size",getIntent().getIntExtra("size",0));

            int cnt=0;
            int n = (getIntent().getIntExtra("size",0));
            while(cnt<=n) {
                cnt++;
                i.putExtra("order"+cnt,getIntent().getStringExtra("order"+cnt));
            }
            startActivity(i);
            finish();
        });


    }

    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setIcon(R.drawable.shopo);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.action_bar2,null);
//
        actionBar.setCustomView(v);
    }

}
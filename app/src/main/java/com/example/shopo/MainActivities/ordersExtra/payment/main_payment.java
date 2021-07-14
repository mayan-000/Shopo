package com.example.shopo.MainActivities.ordersExtra.payment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.shopo.R;

public class main_payment extends AppCompatActivity {

    ImageButton paytm, card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_payment);
        setActionBar();

        paytm = findViewById(R.id.paytmBtn);
        card = findViewById(R.id.cardBtn);

        paytm.setOnClickListener(v -> {
            Intent i = new Intent(main_payment.this, paytm.class);
            i.putExtra("price",getIntent().getStringExtra("price"));
            i.putExtra("size",getIntent().getStringExtra("size"));

            int cnt=0;
            int n = Integer.parseInt(getIntent().getStringExtra("size"));
            while(cnt<=n) {
                cnt++;
                i.putExtra("order"+cnt,getIntent().getStringExtra("order"+cnt));
            }
            startActivity(i);
            finish();
        });

        card.setOnClickListener(v -> {
            Intent i =new Intent(main_payment.this, card.class);
            i.putExtra("price",getIntent().getStringExtra("price"));
            i.putExtra("size",getIntent().getStringExtra("size"));

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
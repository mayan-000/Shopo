package com.example.shopo.MainActivities.ordersExtra.payment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shopo.MainActivities.ordersExtra.buyNow;
import com.example.shopo.R;

public class card_1 extends AppCompatActivity {

    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1);
        setActionBar();

        submit = findViewById(R.id.submitCardButton);

        submit.setOnClickListener(v -> {
            Intent i =new Intent(card_1.this, buyNow.class);
            i.putExtra("price",getIntent().getIntExtra("price",0));
            i.putExtra("size",getIntent().getIntExtra("size",0));

            int cnt=0;
            int n = (getIntent().getIntExtra("size",0));
            while(cnt<=n) {
                cnt++;
                i.putExtra("order"+cnt,getIntent().getStringExtra("order"+cnt));
            }
            Toast.makeText(this, "PAYMENT SUCCESSFUL!", Toast.LENGTH_SHORT).show();
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
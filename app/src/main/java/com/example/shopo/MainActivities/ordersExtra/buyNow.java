package com.example.shopo.MainActivities.ordersExtra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shopo.R;

import java.util.ArrayList;

public class buyNow extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView itemList;
    TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);
        setActionBar();

        itemList = findViewById(R.id.JustBought);
        price = findViewById(R.id.totalprice);

        Intent intent = getIntent();
        price.setText("Total Price of all Items: $"+intent.getIntExtra("price",0)+"\n" +
                "Your Order has been placed. Will Reach Your Address within 4-5 working days.\n" +
                "Check Your Email for extra information");


        int arraySize = intent.getIntExtra("size",0);
        for (int i = 1; i <= arraySize; i++) {
            String element[] = intent.getStringExtra("order"+i).split(",");

            items.add(element[0]+" "+element[1]);
        }


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemList.setAdapter(adapter);



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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
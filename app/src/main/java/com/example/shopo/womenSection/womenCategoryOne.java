package com.example.shopo.womenSection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopo.MainActivities.order_activity;
import com.example.shopo.loginSignup.PublicVariables;
import com.example.shopo.R;
import com.example.shopo.loginSignup.loginSignup;

import java.util.HashSet;
import java.util.Set;

public class womenCategoryOne extends AppCompatActivity {

    private Button wishlist, buyNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_category_one);
        setActionBar();

        wishlist = findViewById(R.id.wishw1);
        buyNow = findViewById(R.id.BuyNowW);


        wishlist.setOnClickListener(v -> {
            startActivity(new Intent(womenCategoryOne.this, order_activity.class));
        });
        buyNow.setOnClickListener(v -> {
            if(!PublicVariables.logindone){
                startActivity(new Intent(this, loginSignup.class));
                finish();
            }
            else{
                Toast.makeText(this, "Item Added to Cart", Toast.LENGTH_LONG).show();
                SharedPreferences sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);

                Set<String> orders = sharedPreferences.getStringSet("orders", new HashSet<>());
                TextView name = findViewById(R.id.nameWomen), price = findViewById(R.id.priceWomen);
                ImageView photo = findViewById(R.id.photoWomen);
                String iname = name.getText().toString();
                String iprice = price.getText().toString();
                int iphoto = R.drawable.a1;
                Log.d("order", "women");

                orders.add((iname + "," + iprice + "," + iphoto));

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("orders", orders);
                editor.apply();
            }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
package com.example.shopo.boysSection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.shopo.MainActivities.order_activity;
import com.example.shopo.R;
import com.example.shopo.girlsSection.kidsGirlsCategory;

public class kidsBoysCategory extends AppCompatActivity {

    private ImageView _1itemImage;
    private ImageButton wishlistBtn;
    private Button boys, girls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_boys_category);
        setActionBar();

        _1itemImage=findViewById(R.id.boyBtn);
        wishlistBtn = findViewById(R.id.btkb123);
        boys = findViewById(R.id.bt124);
        girls = findViewById(R.id.bt125);



        _1itemImage.setOnClickListener(v -> {
            Intent intent=new Intent(kidsBoysCategory.this, boyCategoryOne.class);
            startActivity(intent);
        });

        wishlistBtn.setOnClickListener(v -> {
            startActivity(new Intent(kidsBoysCategory.this, order_activity.class));
        });

        girls.setOnClickListener(v -> {
            startActivity(new Intent(kidsBoysCategory.this, kidsGirlsCategory.class));
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
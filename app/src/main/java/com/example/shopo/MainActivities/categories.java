package com.example.shopo.MainActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.shopo.R;
import com.example.shopo.boysSection.kidsBoysCategory;
import com.example.shopo.menSection.menCategory;
import com.example.shopo.womenSection.womenCategory;

public class categories extends AppCompatActivity {

    private ImageView homeBtn, categoriesBtn, cartBtn, profileBtn;
    private ImageView men,women,kids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        setActionBar();

        homeBtn = findViewById(R.id.homeButton);
        categoriesBtn = findViewById(R.id.categoriesButton);
        cartBtn = findViewById(R.id.shoppingCart);
        profileBtn = findViewById(R.id.profileButton);
        men = findViewById(R.id.mencategory);
        women = findViewById(R.id.womencategory);
        kids = findViewById(R.id.kidscategory);

        setDark();


        men.setOnClickListener(v -> {
            startActivity(new Intent(categories.this, menCategory.class));
        });
        women.setOnClickListener(v -> {
            startActivity(new Intent(categories.this, womenCategory.class));
        });
        kids.setOnClickListener(v -> {
            startActivity(new Intent(categories.this, kidsBoysCategory.class));
        });


        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(categories.this,MainActivity.class));
        });
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(categories.this,order_activity.class));
        });
        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(categories.this,profile_activity.class));
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

    private void setDark(){
        categoriesBtn.setBackgroundColor(getResources().getColor(R.color.gainsBoro));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

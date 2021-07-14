package com.example.shopo.menSection;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.shopo.MainActivities.order_activity;
import com.example.shopo.R;

public class menCategory extends AppCompatActivity {

    private ImageView _1itemImage;
    private ImageButton wishlistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_category);
        setActionBar();

            _1itemImage=findViewById(R.id.Btn);
            wishlistBtn = findViewById(R.id.btkb123);

            _1itemImage.setOnClickListener(v -> {
                Intent intent=new Intent(menCategory.this, menCategoryOne.class);
                startActivity(intent);
            });

            wishlistBtn.setOnClickListener(v -> {
                startActivity(new Intent(menCategory.this, order_activity.class));
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

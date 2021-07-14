package com.example.shopo.MainActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopo.loginSignup.DBhelper;
import com.example.shopo.loginSignup.PublicVariables;
import com.example.shopo.R;
import com.example.shopo.loginSignup.loginSignup;
import com.example.shopo.loginSignup.profileView;

public class profile_activity extends AppCompatActivity {

    TextView nametxtbox, mailtxtbox, addtxtbox;
    TextView phonetxtbox;
    Button editButton, logOut, contactUs;
    DBhelper dBhelper ;
    private ImageView homeBtn, categoriesBtn, cartBtn, profileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setActionBar();

        getviews();
        if(PublicVariables.logindone) {
            dBhelper = new DBhelper(this);
            showdetails();
        }
        else{
            startActivity(new Intent(this, loginSignup.class));
            finish();
        }

        homeBtn = findViewById(R.id.homeButton);
        categoriesBtn = findViewById(R.id.categoriesButton);
        cartBtn = findViewById(R.id.shoppingCart);
        profileBtn = findViewById(R.id.profileButton);

        setDark();

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(profile_activity.this,MainActivity.class));
        });
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(profile_activity.this,order_activity.class));
        });
        categoriesBtn.setOnClickListener(v -> {
            startActivity(new Intent(profile_activity.this,categories.class));
        });
        editButton.setOnClickListener(v -> {
            startActivity(new Intent(profile_activity.this, profileView.class));
        });
        logOut.setOnClickListener(v -> {
            SharedPreferences sh = getSharedPreferences("saveData",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sh.edit();
            ed.remove("orders");
            ed.remove("purchaseHistory");
            ed.remove("logindone");
            ed.remove("phoneofloggedin");
            ed.apply();
            PublicVariables.phoneofloggedin="";
            PublicVariables.logindone=false;
            PublicVariables.loggedinnum=0;
            startActivity(new Intent(this, loginSignup.class));
            finish();
        });
        contactUs.setOnClickListener(v -> {
            startActivity(new Intent(this, com.example.shopo.ExtraActivities.contactUs.class));
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
        profileBtn.setBackgroundColor(getResources().getColor(R.color.gainsBoro));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void getviews(){
        nametxtbox = findViewById(R.id.profilename1);
        addtxtbox = findViewById(R.id.profileadd1);
        mailtxtbox = findViewById(R.id.profileemail1);
        phonetxtbox = findViewById(R.id.profilephone1);
        editButton = findViewById(R.id.editButton);
        logOut = findViewById(R.id.logOut);
        contactUs = findViewById(R.id.contactUS);
    }

    public void showdetails(){
        //show details of PublicVariable.phoneofloggedin;

        phonetxtbox.setText(PublicVariables.phoneofloggedin);


        Cursor cursor = dBhelper.getdataforprofile(PublicVariables.phoneofloggedin);

        if (cursor.getCount() == 0) {
            return;
        }
        //StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext())
        {
            nametxtbox.setText(cursor.getString(1));
            mailtxtbox.setText(cursor.getString(2));
            addtxtbox.setText(cursor.getString(3));


        }

        phonetxtbox.setText(PublicVariables.phoneofloggedin);

    }

}
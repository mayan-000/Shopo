package com.example.shopo.MainActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shopo.loginSignup.PublicVariables;
import com.example.shopo.R;
import com.example.shopo.boysSection.kidsBoysCategory;
import com.example.shopo.loginSignup.loginSignup;
import com.example.shopo.menSection.menCategory;
import com.example.shopo.womenSection.womenCategory;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    ImageView menuBar, homeIcon, homeBtn, categoriesBtn, cartBtn, profileBtn;
    NavigationView navigationView;
    LinearLayout bottomNav;
    ScrollView scroll;
    HorizontalScrollView Hscroll;
    SharedPreferences sharedPreferences;
    MenuItem shop, theme, orders, contactUs;
    TextView men,women,kids;

    int menuFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();

//        Giving ID
        menuBar = findViewById(R.id.menuBar);
        homeIcon = findViewById(R.id.homeIcon);
        navigationView = findViewById(R.id.nav_view);
        bottomNav = findViewById(R.id.bottomNav);
        scroll = findViewById(R.id.Scroll);
        Hscroll = findViewById(R.id.Hscroll);
        homeBtn = findViewById(R.id.homeButton);
        categoriesBtn = findViewById(R.id.categoriesButton);
        cartBtn = findViewById(R.id.shoppingCart);
        profileBtn = findViewById(R.id.profileButton);
//        shop = findViewById(R.id.shop);
//        theme = findViewById(R.id.theme);
//        orders = findViewById(R.id.orders);
//        contactUs = findViewById(R.id.contact);
        men = findViewById(R.id.Men);
        women = findViewById(R.id.Women);
        kids = findViewById(R.id.Kids);

//        Hiding navBar
        navigationView.animate()
                .translationY(navigationView.getHeight())
                .alpha(0.0f).setDuration(0);
        navigationView.setVisibility(View.INVISIBLE);


//        Click Listeners
        menuBar.setOnClickListener(v -> menuBarClickListener());
        categoriesBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,categories.class));

        });
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,order_activity.class));

        });
        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,profile_activity.class));

        });
//        shop.setOnMenuItemClickListener(item -> {
//            startActivity(new Intent(MainActivity.this,categories.class));
//            return true;
//        });
//        theme.setOnMenuItemClickListener(item -> {
//            startActivity(new Intent(MainActivity.this,categories.class));
//            return true;
//        });
//        orders.setOnMenuItemClickListener(item -> {
//            startActivity(new Intent(MainActivity.this,order_activity.class));
//            return true;
//        });
//        Contact us
        men.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, menCategory.class));
        });
        women.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, womenCategory.class));
        });
        kids.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, kidsBoysCategory.class));
        });



        setDark();


//        Shared Preferences
//        saveData();
//
//        CounterPopUp
        new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                Log.d("msg","1");
            }

            @Override
            public void onFinish() {
                sharedPreferences = getSharedPreferences("saveData",Context.MODE_PRIVATE);
                String loginNumber = sharedPreferences.getString("phoneofloggedin","");
//                Log.d("numberLogin",loginNumber);
                if(loginNumber.length()==0){
                    new AlertDialog.Builder(MainActivity.this,R.style.AlertDialogTheme)
                            .setTitle("Wanna Become a Member of Shopo Community? :)")
                            .setCancelable(false)
                            .setMessage("")
                            .setPositiveButton("LET'S GO", (dialog, which) -> {
                                startActivity(new Intent(MainActivity.this, loginSignup.class));
                            })
                            .setNegativeButton("LATER", (dialog, which) -> {
                                dialog.cancel();
                            })
                            .create().show();

                }
                else{
                    PublicVariables.phoneofloggedin=loginNumber;
                    PublicVariables.logindone=true;
                    PublicVariables.loggedinnum=1;
                }

            }
        }.start();


    }


    private void setActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setIcon(R.drawable.shopo);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.action_bar,null);
//
        actionBar.setCustomView(v);
    }

    private void menuBarClickListener(){
        if(menuFlag==0){
            navigationView.setVisibility(View.VISIBLE);
            navigationView.animate()
                    .translationX(0)
                    .alpha(1.0f)
                    .setDuration(700);
            bottomNav.animate()
                    .translationX(-navigationView.getWidth())
                    .alpha(0.0f).setDuration(700);
            scroll.animate().translationY(scroll.getHeight())
                    .alpha(0.0f).setDuration(700);
            Hscroll.animate()
                    .translationY(-Hscroll.getHeight())
                    .alpha(0.0f).setDuration(700);

            menuFlag^=1;
            menuBar.setImageResource(R.drawable.go_back);
        }
        else{
            navigationView.animate()
                    .translationX(-navigationView.getWidth())
                    .alpha(0.0f).setDuration(700);
            bottomNav.animate()
                    .translationX(0)
                    .alpha(1.0f)
                    .setDuration(700);
            scroll.animate()
                    .translationY(0)
                    .alpha(1.0f).setDuration(700);
            Hscroll.animate()
                    .translationY(0)
                    .alpha(1.0f).setDuration(700);

            menuFlag^=1;
            menuBar.setImageResource(R.drawable.ic_menu);
        }

    }

    private void setDark(){
        homeBtn.setBackgroundColor(getResources().getColor(R.color.gainsBoro));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void saveData(){
        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        SharedPreferences. Editor editor = sharedPreferences. edit();
        editor.remove("orders");
        editor.remove("purchaseHistory");
        editor.remove("logindone");
        editor.remove("phoneofloggedin");
        editor.apply();
    }




}
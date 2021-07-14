package com.example.shopo.MainActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.shopo.MainActivities.ordersExtra.payment.main_payment;
import com.example.shopo.loginSignup.PublicVariables;
import com.example.shopo.R;
import com.example.shopo.MainActivities.ordersExtra.buyNow;
import com.example.shopo.loginSignup.loginSignup;
import com.example.shopo.MainActivities.ordersExtra.purchase_history;
import com.example.shopo.adapter.recyclerAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class order_activity extends AppCompatActivity {

    private ImageView homeBtn, categoriesBtn, cartBtn, profileBtn;
    private RecyclerView recyclerView;
    private recyclerAdapter Adapter;
    private Button orderButton, purchaseHistory;

    private ArrayList<String> itemName = new ArrayList<>();
    private ArrayList<String> itemPrice = new ArrayList<>();
    private ArrayList<Integer> itemImage = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setActionBar();

        homeBtn = findViewById(R.id.homeButton);
        categoriesBtn = findViewById(R.id.categoriesButton);
        cartBtn = findViewById(R.id.shoppingCart);
        profileBtn = findViewById(R.id.profileButton);
        orderButton= findViewById(R.id.buyNow);
        purchaseHistory = findViewById(R.id.historyBtn);

        if(!PublicVariables.logindone){
            startActivity(new Intent(this, loginSignup.class));
            finish();
        }

        setDark();

        recyclerView = this.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//            Retrieve Data

        SharedPreferences sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        Set<String> orders = sharedPreferences.getStringSet("orders",new HashSet<>());
//        Log.d("order","check");

        if(orders.size()>0){
            for (String s: orders) {
//                Log.d("order",s);
                String elements[] = s.split(",");
                itemName.add(elements[0]);
                itemPrice.add(elements[1]);
                itemImage.add(Integer.parseInt(elements[2]));
            }
        }
        else {
            itemName.add("Empty Cart!");
            itemPrice.add("OOPSIE!!!");
            itemImage.add(R.drawable.emptycart);
        }

        Adapter = new recyclerAdapter(itemName,itemPrice,itemImage,this);
        recyclerView.setAdapter(Adapter);

//        Click Listener
        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(order_activity.this,MainActivity.class));

        });
        categoriesBtn.setOnClickListener(v -> {
            startActivity(new Intent(order_activity.this,categories.class));
        });
        profileBtn.setOnClickListener(v -> {
            startActivity(new Intent(order_activity.this,profile_activity.class));

        });
        orderButton.setOnClickListener(v -> {

            Intent i = new Intent(order_activity.this, buyNow.class);

            Set<String> s = sharedPreferences.getStringSet("orders",new HashSet<>());
            if(s.size()>0) {
                int totalPrice=0;
                for (String a: s) {
                    String element[]=a.split("\\$",2);
                    String mainEle[]=element[1].split(" ",2);

                    totalPrice+=Integer.parseInt(mainEle[0]);
                }
                i.putExtra("price", totalPrice);
                i.putExtra("size",s.size());

                int cnt=0;
                for (String a: s) {
                    cnt++;
                    i.putExtra("order"+cnt,a);
                }

                SharedPreferences.Editor editor =  sharedPreferences.edit();
                editor.remove("orders");

                Set<String> stringSet = sharedPreferences.getStringSet("purchaseHistory",new HashSet<>());
                stringSet.addAll(s);
                editor.putStringSet("purchaseHistory",stringSet);
                editor.apply();

                startActivity(i);
                finish();
            }
        });
        purchaseHistory.setOnClickListener(v -> {
            startActivity(new Intent(order_activity.this, purchase_history.class));
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
        cartBtn.setBackgroundColor(getResources().getColor(R.color.gainsBoro));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
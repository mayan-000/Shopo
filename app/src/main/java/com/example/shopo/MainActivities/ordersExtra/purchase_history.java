package com.example.shopo.MainActivities.ordersExtra;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.shopo.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class purchase_history extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView itemList;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        setActionBar();


        itemList = findViewById(R.id.historyList);
        sharedPreferences = getSharedPreferences("saveData",Context.MODE_PRIVATE);
        Set<String> stringSet = sharedPreferences.getStringSet("purchaseHistory", new HashSet<>());

        for (String s: stringSet) {
            String element[]=s.split(",");
            items.add(element[0]+" "+element[1]);
        }


        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, items);
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
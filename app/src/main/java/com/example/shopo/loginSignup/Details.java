package com.example.shopo.loginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopo.MainActivities.profile_activity;
import com.example.shopo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Details extends AppCompatActivity {

    private AlertDialog.Builder builder;


    DBhelper dBhelperinsert;
    EditText fullnametxt, emailtxt;
    CheckBox malecheck, femalecheck;
    EditText addresstxt,  phonetxt, passwordtxt;
    private Boolean malecheckans , femalecheckans;
    Button btn;

    String name, email, phone, add, pass;
    int passmaxlength = 8;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setActionBar();


        dBhelperinsert = new DBhelper(this);
        getviews();
        setfilter() ;

        btn.setOnClickListener(v -> {
            gettextofview();

            if (name.equals("") || email.equals("") || phone.equals("") || add.equals("")  || pass.equals(""))
            {
                builder = new AlertDialog.Builder(Details.this);
                builder.setPositiveButton("OK" , null);
                builder.setMessage("Please fill all details");
                builder.show();
            }
            else{
                if (isEmailValid(email) && phone.length() == 10 && name.length() >= 4 && add.length() >= 10 && pass.length() == 8) {
                    Boolean isuserexit = dBhelperinsert.checkphone(phone);
                    Log.d("msg",""+isuserexit);

                    if (!isuserexit) {

                        Log.d("msg","12341234");

                        Bundle bundle = new Bundle();
                        bundle.putStringArray("data",new String[]{pass, name, email, add, phone});
                        Intent i = new Intent(Details.this, Otp.class);
                        i.putExtras(bundle);
                        startActivity(i);
                        finish();



                    } else {
                        builder = new AlertDialog.Builder(this);
                        builder.setMessage("User already exists.");
                        builder.setPositiveButton("OK", null);
                        builder.show();
                    }

                }
                else
                {
                    builder = new AlertDialog.Builder(this);
                    builder.setMessage("Enter the details properly");
                    builder.setPositiveButton("OK" , null);
                    builder.show();
                }
            }
        });
    }


    public void setfilter(){
        passwordtxt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
        fullnametxt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});
        addresstxt.setFilters(new InputFilter[] {new InputFilter.LengthFilter(40)});
        phonetxt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
    }



    //to restrict the length of edittexts


    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    //creating notification channel



    //get references to edit texts
    public void gettextofview() {

        name = fullnametxt.getText().toString();
        email = emailtxt.getText().toString();
        phone = phonetxt.getText().toString();
        add = addresstxt.getText().toString();

        pass = passwordtxt.getText().toString();
        malecheckans = malecheck.isChecked();
        femalecheckans = femalecheck.isChecked();
    }


    //fetch data inside the edit texts
    private void getviews() {
        fullnametxt = findViewById(R.id.editTextTextPersonName);
        emailtxt = findViewById(R.id.editTextTextEmailAddress);
        malecheck = findViewById(R.id.checkBox);
        femalecheck = findViewById(R.id.checkBox2);
        phonetxt = findViewById(R.id.editTextPhone);
        addresstxt = findViewById(R.id.editTextTextPostalAddress);

        passwordtxt = findViewById(R.id.editTextTextPassword2);
        btn = findViewById(R.id.savebtn);


    }

    //this will fire if user successfully register




    //this btn will insert data entered by user in edit texts in database
    /*public void Onclick(View view) {

        if (name.equals("") || email.equals("") || phone.equals("") || add.equals("")  || pass.equals(""))
        {
            builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK" , null);
            builder.setMessage("Please fill all details");
            builder.show();
        }

        else {

            if (isEmailValid(email) && phone.length() == 10 && name.length() >= 4 && add.length() >= 10 && pass.length() == 8) {
                Boolean isuserexit = dBhelperinsert.checkphone(phone);
                Log.d("msg","12341234");

                if (!isuserexit) {

                    Log.d("msg","12341234");

                    Bundle bundle = new Bundle();
                    bundle.putStringArray("data",new String[]{pass, name, email, add, phone});
                    Intent i = new Intent(Details.this, otpPopUs.class);
                    i.putExtras(bundle);
                    startActivity(i);
                    finish();



                } else {
                    builder = new AlertDialog.Builder(this);
                    builder.setMessage("User already exists.");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }

            }
            else
            {
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Enter the details properly");
                builder.setPositiveButton("OK" , null);
                builder.show();
            }
        }
    }*/




    @Override
    public void onBackPressed() {
        super.onBackPressed();
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





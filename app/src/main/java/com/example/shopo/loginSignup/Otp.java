package com.example.shopo.loginSignup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopo.MainActivities.profile_activity;
import com.example.shopo.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp extends AppCompatActivity {

    String verificationID;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DBhelper dBhelperinsert;
    String pass, name, email, add, phone;

    Button verifyBtn;
    EditText Otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        setActionBar();
        createNotificationchannel();

        verifyBtn = findViewById(R.id.buttonCardSubmit);
        Otp = findViewById(R.id.editTextNumber);

        dBhelperinsert = new DBhelper(this);

        pass = getIntent().getExtras().getStringArray("data")[0];
        name = getIntent().getExtras().getStringArray("data")[1];
        email = getIntent().getExtras().getStringArray("data")[2];
        add = getIntent().getExtras().getStringArray("data")[3];
        phone = getIntent().getExtras().getStringArray("data")[4];


        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);


        verifyBtn.setOnClickListener(v -> {
            String code = Otp.getText().toString();
            if(code.equalsIgnoreCase("")){
                Toast.makeText(Otp.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
            }
            else{
                verifyCode(code);
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

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
        signIn(credential);
    }

    private void signIn(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Boolean isdatainserted = dBhelperinsert.insertuserdata(pass, name, email, add, phone);
                if (isdatainserted) {
                    Toast.makeText(Otp.this, "You are registered now.", Toast.LENGTH_LONG).show();
                    welcomenotification();
                } else
                    Toast.makeText(Otp.this, "Data not inserted", Toast.LENGTH_LONG).show();

                SharedPreferences sh = getSharedPreferences("saveData",Context.MODE_PRIVATE);
                SharedPreferences.Editor ed = sh.edit();
                ed.putBoolean("logindone",true);
                ed.putString("phoneofloggedin",phone);
                ed.apply();

                PublicVariables.phoneofloggedin=phone;
                PublicVariables.logindone=true;
                startActivity(new Intent(Otp.this, profile_activity.class));
                finish();

            }
            else{
                Toast.makeText(Otp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            final String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                Otp.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(Otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationID = s;
        }
    };


    public void welcomenotification(){
        Intent intent = new Intent(this , Details.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0 , intent , 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getString(R.string.channel_id));
        builder.setContentTitle("Welcome !!!");
        builder.setContentText("Thank you for registering with us.");
        builder.setSmallIcon(R.drawable.applogo);
        builder.setContentIntent(pendingIntent);
        builder.setChannelId(getString(R.string.channel_id));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1 ,builder.build());
    }

    private void createNotificationchannel()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    getString(R.string.channel_id) ,
                    getString(R.string.channel_name) ,
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(getString(R.string.channel_description));
            notificationChannel.setShowBadge(true);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}
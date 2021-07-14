package com.example.shopo.ExtraActivities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopo.R;

public class contactUs extends AppCompatActivity {

    SharedPreferences feedback;
    EditText editText;
    String feed;

    TextView phonetextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setActionBar();

        phonetextview = findViewById(R.id.textView4);

        editText = findViewById(R.id.editTextTextMultiLine);
        feedback = getSharedPreferences("userFeedback" , MODE_PRIVATE);
    }

    public void dial(View view){
        Intent chooser;
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("+91 1234567890"));
        chooser = Intent.createChooser(intent , "Launch Caller");
        startActivity(chooser);
    }

    public void emailsent(View view){
        Intent intent , choose;

        String ourmail = "shoppo@gmail.com";
        intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto :"));
        intent.putExtra(Intent.EXTRA_EMAIL , ourmail);
        intent.putExtra(Intent.EXTRA_SUBJECT , "Feedback");
        intent.putExtra(Intent.EXTRA_TEXT ,"Dear sir");
        intent.setType("message/rfc822");
        choose = Intent.createChooser(intent , "Launch Email");
        startActivity(choose);

    }

    public void sendclick(View view) {

        feed = editText.getText().toString();
        SharedPreferences.Editor editor = feedback.edit();

        if (!feed.equals("")) {
            editor.putString("user_feedback", feed);
            editor.commit();
            Toast.makeText(this , "Thanks for sharing your feedback" , Toast.LENGTH_LONG).show();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Empty field");
            builder.setPositiveButton("OK" , null);
            builder.show();
        }
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
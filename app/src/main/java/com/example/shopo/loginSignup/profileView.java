package com.example.shopo.loginSignup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopo.R;

public class profileView extends AppCompatActivity {

    EditText nametxtbox, mailtxtbox, addtxtbox, passtxtbox;
    TextView phonetxtbox;
    Button savebtn;

    DBhelper dBhelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        setActionBar();

        getviews();
        restrictfields();

        if(PublicVariables.logindone) {
            dBhelper = new DBhelper(this);
            showdetails();
            Log.d("finderror","1234");
        }


    }

    public void restrictfields(){
        nametxtbox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        addtxtbox.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
        passtxtbox.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});


    }


    boolean isEmailValid(CharSequence mail) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }



    public void getviews(){
        nametxtbox = findViewById(R.id.profilename);

        addtxtbox = findViewById(R.id.profileadd);
        mailtxtbox = findViewById(R.id.profileemail);
        phonetxtbox = findViewById(R.id.profilephone);
        passtxtbox = findViewById(R.id.profilepass);
        savebtn = findViewById(R.id.editbutton);
    }

    public void showdetails(){
        //show details of PublicVariable.phoneofloggedin;

        phonetxtbox.setText(PublicVariables.phoneofloggedin);


        Cursor cursor = dBhelper.getdataforprofile(PublicVariables.phoneofloggedin);

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Details not Fetched", Toast.LENGTH_LONG).show();
            return;
        }
        //StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext())
        {
            passtxtbox.setText(cursor.getString(0));
            nametxtbox.setText(cursor.getString(1));
            mailtxtbox.setText(cursor.getString(2));
            addtxtbox.setText(cursor.getString(3));


        }

        phonetxtbox.setText(PublicVariables.phoneofloggedin);

    }


    public void editdata(View view) {
        String nam = nametxtbox.getText().toString();
        String mail = mailtxtbox.getText().toString();
        String addr = addtxtbox.getText().toString();
        String ph = phonetxtbox.getText().toString();

        if (nam.equals("") || mail.equals("") || addr.equals("") || ph.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", null);
            builder.setMessage("Please fill all the details");
            builder.show();
        } else {

            if (isEmailValid(mailtxtbox.getText()) && nametxtbox.getText().length() >= 4 && addtxtbox.getText().length() >= 10 && passtxtbox.getText().length() == 8) {
                Boolean isupdated = dBhelper.updatedata(nam, mail, addr, ph);

                if (isupdated){
                    Toast.makeText(this, "Updated successfully", Toast.LENGTH_LONG).show();
                    SharedPreferences sh = getSharedPreferences("saveData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sh.edit();
                    ed.putBoolean("logindone", true);
                    ed.putString("phoneofloggedin", ph);
                    ed.apply();
                    PublicVariables.logindone = true;
                    PublicVariables.phoneofloggedin = ph;
                }
                else
                    Toast.makeText(this, "Not Updated", Toast.LENGTH_LONG).show();
            }

            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("OK", null);
                builder.setMessage("Please fill details properly");
                builder.show();
            }
        }
        finish();
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
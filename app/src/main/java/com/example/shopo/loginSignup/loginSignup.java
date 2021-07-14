package com.example.shopo.loginSignup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shopo.MainActivities.profile_activity;
import com.example.shopo.R;

public class loginSignup extends AppCompatActivity {

    private AlertDialog.Builder builder;
    SharedPreferences userlogindetails ;

    DBhelper dBhelper ;


    EditText editText_phone;
    EditText editText_pass;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        setActionBar();


            userlogindetails = getSharedPreferences("saveData",Context.MODE_PRIVATE);


            editText_phone =(EditText) findViewById(R.id.editTextPhone2);
            editText_pass = (EditText) findViewById(R.id.editTextTextPassword);

            editText_pass.setFilters(new InputFilter[] {new InputFilter.LengthFilter(8)});
            editText_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});


            loginbtn = findViewById(R.id.loginbutton);


            dBhelper = new DBhelper(this);
        }


        public void loginOnclick(View view){
            String us_phone =  editText_phone.getText().toString();
            String user_pass = editText_pass.getText().toString();
            if(us_phone.equals("") || user_pass.equals(""))
            {
                builder = new AlertDialog.Builder(this);
                builder.setMessage("Please fill the details");
                builder.setPositiveButton("OK" , null);
                builder.show();
            }
            else
            {
                Boolean checkuser = dBhelper.checkpassword(us_phone , user_pass);
                if(checkuser)
                { //this variable is to check if someone has logged in
                    PublicVariables.logindone = true;

                    //one person has logged in now
                    PublicVariables.loggedinnum++;

                    //phone no of person logged in
                    PublicVariables.phoneofloggedin = us_phone;

                    SharedPreferences.Editor editor = userlogindetails.edit();
                    editor.putBoolean("logindone" , PublicVariables.logindone);
                    editor.putString("phoneofloggedin" , PublicVariables.phoneofloggedin);
                    editor.apply();


                    Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();



                    editText_phone.getText().clear();
                    editText_pass.getText().clear();

                    startActivity(new Intent(this , profile_activity.class));
                    finish();

                }
                else
                {
                    builder = new AlertDialog.Builder(this);
                    builder.setMessage("Invalid credentials");
                    builder.setPositiveButton("OK" , null);
                    builder.show();
                }
            }

        }

        public void signupclick(View view){
            startActivity(new Intent(this , Details.class));
            finish();
        }


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
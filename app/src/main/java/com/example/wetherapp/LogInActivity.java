package com.example.wetherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogInActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        final EditText etName = (EditText) findViewById(R.id.NameText);
        final EditText etPassword = (EditText) findViewById(R.id.PasswordText);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etName.getText().toString();
                String password = etPassword.getText().toString();

                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                String userDetails = preferences.getString(user + password + "data", "Username or Passoword is Incorrect.");
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("display", userDetails);
//                editor.commit();
                if(!userDetails.contains("Username or Passoword is Incorrect.")) {
                    Intent homePage = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(homePage);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Username or Passoword is Incorrect.",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerScreen = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(registerScreen);
            }
        });
    }
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        // check which textview it is and do what you need to do
//        Intent registerScreen = new Intent(LogInActivity.this, RegisterActivity.class);
//        startActivity(registerScreen);
//        // return true if you don't want it handled by any other touch/click events after this
//        return true;
//    }
}

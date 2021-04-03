package com.example.wetherapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class LogInActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
//        TODO: Hyperlink to text
//        TextView textView = findViewById(R.id.SingInTextView);
//        String text = "I want THIS and THIS to be clickable";
//        SpannableString ss = new SpannableString(text);
//        ClickableSpan clickableSpan1 = new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                Toast.makeText(LogInActivity.this, "One", Toast.LENGTH_SHORT).show();
//            }
//        };
//
//        ss.setSpan(clickableSpan1, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView.setText(ss);
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

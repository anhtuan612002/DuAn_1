package com.example.textfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
Button btnSignIn,btnSignUp;
TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtSlogan = findViewById(R.id.txtslogan);
      //  Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
      //  txtSlogan.setTypeface(face);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
      btnSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent signUp = new Intent(MainActivity.this,SignUp.class);
              startActivity(signUp);
          }
      });
      btnSignIn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent signIn = new Intent(MainActivity.this,HomeActivity.class);
              startActivity(signIn);

          }
      });
    }
}
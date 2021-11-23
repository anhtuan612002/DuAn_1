package com.example.textfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.textfood.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {
MaterialEditText edtPhone,edtName,edtPassword;
Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
       final FirebaseDatabase database = FirebaseDatabase.getInstance();
       final DatabaseReference table_user = database.getReference("User");
       btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final ProgressDialog mDiaLog = new ProgressDialog(SignUp.this);
               mDiaLog.setMessage("Đợi tao tí ...");
               mDiaLog.show();
               table_user.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                     if(snapshot.child(edtPhone.getText().toString()).exists()){
                         mDiaLog.dismiss();
                         Toast.makeText(SignUp.this, "Phone Number already register", Toast.LENGTH_SHORT).show();
                     }else {
                         mDiaLog.dismiss();
                         User user = new User(edtName.getText().toString(),edtPassword.getText().toString());
                         table_user.child(edtPhone.getText().toString()).setValue(user);
                         Toast.makeText(SignUp.this, "Sign up successfully ", Toast.LENGTH_SHORT).show();
                         finish();
                     }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
           }
       });
    }
}
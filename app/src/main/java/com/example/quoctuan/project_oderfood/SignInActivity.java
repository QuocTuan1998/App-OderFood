package com.example.quoctuan.project_oderfood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quoctuan.project_oderfood.commention.Common;
import com.example.quoctuan.project_oderfood.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignInActivity extends AppCompatActivity {

    EditText editPass,editPhone;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        addControls();
        addEvents();
    }

    private void addControls() {
        editPhone = (MaterialEditText) findViewById(R.id.editPhone);
        editPass = (MaterialEditText) findViewById(R.id.editPass);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);


    }

    private void addEvents() {
        //        Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_User = database.getReference("Users");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog(SignInActivity.this);
                mdialog.setMessage("please waiting....");
                mdialog.show();
                table_User.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                        check if user not exist in database
                if (dataSnapshot.child(editPhone.getText().toString()).exists()){
//                          get Users information
                    mdialog.dismiss();
                    Users user = dataSnapshot.child(editPhone.getText().toString()).getValue(Users.class);
                    user.setPhone(editPhone.getText().toString());
                    if (user.getPassword().equals(editPass.getText().toString())){
                        Intent homeintent = new Intent(SignInActivity.this,HomeActivity.class);
                        Common.currentUser = user;
                        startActivity(homeintent);
                        finish();
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Sign in failed ,Wrong password !!", Toast.LENGTH_SHORT).show();
                    }

                }
                else {
                    mdialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Users not exist,please try again or sign up !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            });
            }
        });

    }
}

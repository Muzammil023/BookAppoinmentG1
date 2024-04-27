package com.example.bookappointmentg1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Login extends AppCompatActivity {

    Button btn_login_submit;
    Button btn_login_register;
    EditText txt_login_username;
    EditText txt_login_password;

    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login_submit = findViewById(R.id.btn_login_submit);
        btn_login_register = findViewById(R.id.btn_login_register);
        txt_login_username = findViewById(R.id.txt_login_username);
        txt_login_password = findViewById(R.id.txt_login_password);

        dbref = FirebaseDatabase.getInstance().getReference();


        btn_login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int valid = 1;
                if (TextUtils.isEmpty(String.valueOf(txt_login_username.getText()))) {
                    Toast.makeText(Login.this, "Enter Username", Toast.LENGTH_LONG).show();
                    valid = 0;
                } else if (TextUtils.isEmpty(String.valueOf(txt_login_password.getText()))) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_LONG).show();
                    valid = 0;
                }
                if (valid == 1) {

                    if (CheckUser_isvalid(String.valueOf(txt_login_username.getText()), String.valueOf(txt_login_password.getText())) != "") {
                        //validate with firebase

                        dbref.child("Logins").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(txt_login_username.getText().toString().trim())) {

                                    //check password
                                    final String getPassword = snapshot.child(txt_login_username.getText().toString().trim()).child("password").getValue(String.class);

                                    //Toast.makeText(Login.this, get, Toast.LENGTH_LONG).show();

                                    if (getPassword.equals(txt_login_password.getText().toString())) {
                                        //final String getIsActive=snapshot.child(txt_login_username.getText().toString().trim()).child("isactive").getValue(String.class);
                                        Toast.makeText(Login.this, "Successful login ", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Login.this, Dashboard.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(Login.this, "Invalid login", Toast.LENGTH_LONG).show();

                                    }


                                } else {
                                    Toast.makeText(Login.this, "Invalid login", Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    } else {
                        Toast.makeText(Login.this, "Wrong Credentials", Toast.LENGTH_LONG).show();

                    }
                }

            }
        });

        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


    }

    public String CheckUser_isvalid(String UserName, String Password) {
        final String[] val = {""};


        DatabaseReference conn = FirebaseDatabase.getInstance().getReference().child("logins");
        conn.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    if (snapshot1.getKey().equals("username")) {
                        String db_name = snapshot1.getValue().toString();


                        val[0] = "yes";
                        Toast.makeText(Login.this, db_name, Toast.LENGTH_LONG).show();


                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return "yes";
    }

}
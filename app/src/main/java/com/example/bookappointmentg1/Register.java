package com.example.bookappointmentg1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private DatabaseReference dbref;
    long id_no=0;

    //objects for input fields
    EditText txt_register_userid;
    EditText txt_register_username;
    EditText txt_register_email;
    EditText txt_register_password;
    Button btn_register_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_register_userid=findViewById(R.id.txt_register_userid);
        txt_register_username=findViewById(R.id.txt_register_username);
        txt_register_password=findViewById(R.id.txt_register_password);
        txt_register_email=findViewById(R.id.txt_register_email);
        btn_register_submit=findViewById(R.id.btn_register_submit);


        //Database Ref
        dbref= FirebaseDatabase.getInstance().getReference("Logins");
        //auto number generator
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    id_no=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//insert record

        btn_register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model_user m=new Model_user();
                //m.setId(id_no);
                //m.setUserid(txt_register_userid.getText().toString().trim());
                m.setUsername(txt_register_username.getText().toString().trim());
                m.setPassword(txt_register_password.getText().toString().trim());
                m.setEmailaddress(txt_register_email.getText().toString().trim());
                m.setIsactive("True");




                //insert into table
                dbref.child(txt_register_userid.getText().toString().trim()).setValue(m);

                Intent intent=new Intent(Register.this,Dashboard.class);
                startActivity(intent);
            }
        });


    }
}

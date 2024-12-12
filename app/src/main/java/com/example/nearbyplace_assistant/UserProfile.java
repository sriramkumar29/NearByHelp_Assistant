package com.example.nearbyplace_assistant;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    EditText et_username;
    EditText et_email;
    EditText et_pho_no;
    EditText et_pho_no1;
    EditText et_pho_no2;
    EditText et_pho_no3;
    Button btn_save;
    UserDetail user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        et_username = findViewById(R.id.name);
        et_email = findViewById(R.id.Email);
        et_pho_no = findViewById(R.id.phone_no);
        et_pho_no1 = findViewById(R.id.phone_no1);
        et_pho_no2 = findViewById(R.id.phone_no2);
        et_pho_no3 = findViewById(R.id.phone_no3);
        btn_save = findViewById(R.id.btn_save);

        DBHelper dbHelper = new DBHelper(UserProfile.this);
        user = dbHelper.getValue();
//        Toast.makeText(UserProfile.this,user.getName(),Toast.LENGTH_LONG).show();
        et_username.setText(user.getName());
        et_email.setText(user.getEmail());
        et_pho_no.setText(user.getPhone_no());
        et_pho_no1.setText(user.getPhone_no1());
        et_pho_no2.setText(user.getPhone_no2());
        et_pho_no3.setText(user.getPhone_no3());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setName(et_username.getText().toString());
                user.setEmail(et_email.getText().toString());
                user.setPhone_no(et_pho_no.getText().toString());
                user.setPhone_no1(et_pho_no1.getText().toString());
                user.setPhone_no2(et_pho_no2.getText().toString());
                user.setPhone_no3(et_pho_no3.getText().toString());
                boolean result = dbHelper.updateUser(user);

                if(result)
                    Toast.makeText(UserProfile.this,"Updated",Toast.LENGTH_LONG).show();
            }
        });
    }
}

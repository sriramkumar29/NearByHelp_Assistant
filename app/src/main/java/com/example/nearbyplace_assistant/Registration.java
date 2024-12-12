package com.example.nearbyplace_assistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    EditText et_name;
    EditText et_email;
    EditText et_pho_no;
    EditText et_pho_no1;
    EditText et_pho_no2;
    EditText et_pho_no3;
    Button btn_reg;
    String phoneNumberRegex = "^\\d{10}$";
    String nameRegex = "^[a-zA-Z\\s]+";
    String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    private boolean validateWithRegex(String text,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(text).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        SharedPreferences sp = getSharedPreferences("Registration",MODE_PRIVATE);
        String firstTime = sp.getString("FristTimeInstall","");

        if(firstTime.equals("Yes")){
            Intent i1 = new Intent(Registration.this,MainActivity.class);
            startActivity(i1);
        }
//        else{
//            SharedPreferences.Editor edit = sp.edit();
//            edit.putString("FristTimeInstall","Yes");
//            edit.apply();
//        }



        et_name = findViewById(R.id.username);
        et_email = findViewById(R.id.Email);
        et_pho_no = findViewById(R.id.phone_no);
        et_pho_no1 = findViewById(R.id.phone_no1);
        et_pho_no2 = findViewById(R.id.phone_no2);
        et_pho_no3 = findViewById(R.id.phone_no3);
        btn_reg = findViewById(R.id.btn_register);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserDetail userDetail;

                try {
                    userDetail = new UserDetail(-1,et_name.getText().toString(),et_email.getText().toString(),
                            et_pho_no.getText().toString(),et_pho_no1.getText().toString(),et_pho_no2.getText().toString(),
                            et_pho_no3.getText().toString());


                    boolean validEmail =  validateWithRegex(userDetail.getEmail(),emailRegex);
                    boolean validname = validateWithRegex(userDetail.getName(),nameRegex);
                    boolean validphone = validateWithRegex(userDetail.getPhone_no(),phoneNumberRegex);
                    boolean validphone1 = validateWithRegex(userDetail.getPhone_no1(),phoneNumberRegex);;
                    boolean validphone2 = validateWithRegex(userDetail.getPhone_no2(),phoneNumberRegex);
                    boolean validphone3 = validateWithRegex(userDetail.getPhone_no3(),phoneNumberRegex);

                    System.out.println(validEmail);
                    System.out.println(validname);
                    System.out.println(validphone);
                    System.out.println(validphone1);
                    System.out.println(validphone2);
                    System.out.println(validphone3);

                    if(validname && validphone && validEmail && validphone1 && validphone2 && validphone3)
                    {
                        Toast.makeText(Registration.this, userDetail.toString(), Toast.LENGTH_SHORT).show();
                        DBHelper dbHelper =  new DBHelper(Registration.this);
                        boolean success = dbHelper.addUser(userDetail);
                        if(success == true){
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("FristTimeInstall","Yes");
                            edit.apply();
                            Toast.makeText(Registration.this,"Registration success",Toast.LENGTH_LONG).show();
                            Intent i1 = new Intent(Registration.this,MainActivity.class);
                           // i1.putExtra("restartFlag",true);
                            startActivity(i1);
                        }
                    }
                    else
                    {
                        throw new DetailsException("Please Enter the correct details");
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(Registration.this, e.toString(), Toast.LENGTH_SHORT).show();
                    userDetail = new UserDetail(-1,"error","error","0","0","0","0");
                }


            }
        });
    }

}
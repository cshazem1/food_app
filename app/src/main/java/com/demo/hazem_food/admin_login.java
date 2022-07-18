package com.demo.hazem_food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    TextView name_login;
    TextView pass_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
name_login=(TextView)findViewById(R.id.name_loginn);
pass_login=(TextView) findViewById(R.id.pass_login);
    }

    public void enter(View view) {

        String name =name_login.getText().toString();
        String pass=pass_login.getText().toString();
        if(name.equals("hazem@food.com")&&pass.equals("admin123"))
        {
            Intent intent=new Intent(this,name_address_in_firebase.class);
            startActivity(intent);
            Toast.makeText(this,"Welcome admain",Toast.LENGTH_SHORT).show();
            name_login.setText(null);
            pass_login.setText(null);
        }
        else
            Toast.makeText(this,"pass or email (not correct)",Toast.LENGTH_SHORT).show();


    }
}
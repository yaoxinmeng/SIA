package com.example.xinmeng.sia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    EditText username = (EditText) findViewById(R.id.usernameField);
    EditText password = (EditText) findViewById(R.id.passwordField);

    Button login_button = (Button) findViewById(R.id.loginButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AzureServiceAdapter.Initialize(this);

        LoginButton();

        try {
            MobileServiceClient mClient = new MobileServiceClient("https://defectslist.azurewebsites.net/", this);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }


    }
    public void LoginButton(){

        login_button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (username.getText().toString().equals("supervisor") && password.getText().toString().equals("password")) {
                            Intent i = new Intent(MainActivity.this, TechnicianMain.class);
                            startActivity(i);
                        }
                            else {
                                Toast.makeText(MainActivity.this,
                                        "Invalid Username / Password",Toast.LENGTH_SHORT).show();
                            }

                    }
                }
        );

    }
}

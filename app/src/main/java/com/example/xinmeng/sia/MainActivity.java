package com.example.xinmeng.sia;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    EditText username_field;
    EditText password_field;
    TextView username;
    TextView password;

    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AzureServiceAdapter.Initialize(this);
        username_field = (EditText) findViewById(R.id.usernameField);
        password_field = (EditText) findViewById(R.id.passwordField);
        username = (TextView)  findViewById(R.id.username_text);
        password = (TextView)  findViewById(R.id.password_text);
        login_button = (Button) findViewById(R.id.loginButton);

        /*Intent display_intent = new Intent(this, userpwDisplay.class);
        startService(display_intent);*/
        enterUserPw();
        LoginButton();

        try {
            MobileServiceClient mClient = new MobileServiceClient("https://defectslist.azurewebsites.net/", this);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }

    }

    public void enterUserPw(){
        username_field.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        username.setText("");
                    }
                }
        );

        password_field.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        password.setText("");
                    }
                }
        );
       /* layout.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        if (username.toString().isEmpty())
                            username.setText("@string/type_username");
                        if (password.toString().isEmpty())
                            password.setText("@string/type_password");
                    }
                }
        );
        */

    }

    public void LoginButton(){


        login_button.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        if (username.getText().toString().equals("technician") && password.getText().toString().equals("password")) {
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
    /*public class userpwDisplay extends IntentService{

        public userpwDisplay() {
            super("userpwDisplay");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            if (username_field.getText().toString().isEmpty())
                username.setText("@string/type_username");
            else
                username.setText("");

        }
    }*/

}

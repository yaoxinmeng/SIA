package com.example.xinmeng.sia;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;



import com.example.xinmeng.sia.Models.defectsDataRetriever;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText username_field;
    EditText password_field;
    TextView username;
    TextView password;
    //RelativeLayout layout;

    Button login_button;
    DefectsFetcher defect;
    List<DefectsFetcher> result;
    List<TechnicianData> tResult;

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
        //layout = (RelativeLayout)findViewById(R.id.homelayout);
        if(!databaseGetter.hasBeenInitialised()){
            databaseGetter.initialise(this);
        }
        Database.updateFromDatabase();
        result = databaseGetter.getInstance().getDefectsDataGetter().fetchDefectsData();
        tResult = databaseGetter.getInstance().getTechnicianDataGetter().fetchTechniciansData();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Database.updateFromDatabase();
            }
        });

        thread.start();


        /*Intent display_intent = new Intent(this, userpwDisplay.class);
        startService(display_intent);*/
        enterUserPw();
        LoginButton();

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

        /*
        layout.setOnClickListener(
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

                        if (username_field.getText().toString().equals("technician") && password_field.getText().toString().equals("password")) {
                            Intent i = new Intent(MainActivity.this, TechnicianMain.class);
                            String loginID = username_field.getText().toString();
                            i.putExtra("TECH", loginID);
                            startActivity(i);
                        }
                            else {
//                            Toast.makeText(MainActivity.this,
//                                    "Invalid Username / Password", Toast.LENGTH_SHORT).show();

                            Toast.makeText(MainActivity.this,
                                    Database.technicians.get(1).ID, Toast.LENGTH_SHORT).show();





                        }

                    }
                }
        );




    }

    private DefectsFetcher addStuff() {
        DefectsFetcher newDefect = new DefectsFetcher();
        newDefect.setId("123");
        newDefect.setAction("test");
        newDefect.setAgeing(25);
        newDefect.setAssigned(false);
        newDefect.setAta(25);
        newDefect.setCategory("test");
        newDefect.setClassCode("test");
        newDefect.setDateRaised("test date");
        newDefect.setDefects("I am a defect");
        newDefect.setDeferralReason("Dunno");
        newDefect.setFleet("A380");
        newDefect.setInProgress(false);
        newDefect.setPartDetails("test");
        newDefect.setRegn("test");
        newDefect.setResolved(false);
        newDefect.setStn("test");
        newDefect.setTechnicianID("howardbby");
        return newDefect;
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

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
//        databaseGetter.getInstance().getDefectsDataGetter().insertData(addStuff());


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
                            i.putExtra("loginID", loginID);
                            startActivity(i);
                        }
                        else if (username_field.getText().toString().equals("supervisor") && password_field.getText().toString().equals("password")) {
                            Intent i = new Intent(MainActivity.this, SupervisorMain.class);
                            String loginID = username_field.getText().toString();
                            i.putExtra("loginID", loginID);
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
        newDefect.setId("CD123570");
        newDefect.setAction("20 OCT: LOCK LATCH DAMAGED TO REPLACE");
        newDefect.setAgeing(12);
        newDefect.setAssigned(false);
        newDefect.setAta(25);
        newDefect.setCategory("Lavatory");
        newDefect.setClassCode("First");
        newDefect.setDateRaised("15-Oct-17");
        newDefect.setDefects("LAVATORY 33 UNABLE TO LATCH");
        newDefect.setDeferralReason("No Part Number");
        newDefect.setFleet("A380");
        newDefect.setInProgress(false);
        newDefect.setPartDetails("");
        newDefect.setRegn("SWM");
        newDefect.setResolved(false);
        newDefect.setStn("SIN");
        newDefect.setTechnicianID("");
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

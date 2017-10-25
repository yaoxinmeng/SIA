package com.example.xinmeng.sia;

import android.os.AsyncTask;
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
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.google.common.util.concurrent.ListenableFuture;
import com.squareup.okhttp.OkHttpClient;


import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText username_field;
    EditText password_field;
    TextView username;
    TextView password;

    Button login_button;

    MobileServiceClient mClient;
    MobileServiceTable<DefectsFetcher> mDefectsTable;
    List<DefectsFetcher> result;

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
            mClient = new MobileServiceClient("https://defectslist.azurewebsites.net/", this);
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });
            mDefectsTable = mClient.getTable("DefectsData", DefectsFetcher.class);
            result = mDefectsTable
                    .where()
                    .field("resolved").eq(false)
                    .execute()
                    .get();
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
//                            Toast.makeText(MainActivity.this,
//                                    result.get(1).getDefectNumber(),Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );




    }

    private DefectsFetcher addStuff() {
        DefectsFetcher newDefect = new DefectsFetcher();
        newDefect.setAction("test");
        newDefect.setAgeing(25);
        newDefect.setAssigned(false);
        newDefect.setAta(25);
        newDefect.setCategory("test");
        newDefect.setClassCode("test");
        newDefect.setDateRaised("test date");
        newDefect.setDefectNumber("test123");
        newDefect.setDefectsName("I am a defect");
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

package com.example.sdzone.carfi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
   ProgressBar progressbar;



  private TextView textView;
    private Button button;
    private RequestQueue LrequestQueue; //volley request queue
    private StringRequest LstringRequest; //volley sttringrequest
    private String url="http://www.google.com";//specify the connection "URI"
    private static final String TAG = HomeActivity.class.getSimpleName();
    private Button addacar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    progressbar=(ProgressBar)findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewSignup1).setOnClickListener(this);






        button =(Button)findViewById(R.id.buttonSignUp);
        //textView=(TextView)findViewById(R.id.txt);








       /* button = (Button)findViewById(R.id.bn);
        textView=(TextView)findViewById(R.id.txt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RequestQueue requestQueue = Volley.newRequestQueue(TestActivity.this);
                StringRequest stringRequest =new StringRequest(Request.Method.POST, server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                textView.setText(response);
                                requestQueue.stop();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                            textView.setText("something went wrong..");
                            error.printStackTrace();
                            requestQueue.stop();
                    }
                }

                );
                requestQueue.add(stringRequest);
            }
        });*/

    }
    public void openTestActivity(){
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);
    }
    public void openaddacarActivity(){
        Intent intent = new Intent(this,AddCarActivity.class);
        startActivity(intent);
    }


    private void SendRequestAndPrint() {

        LrequestQueue = Volley.newRequestQueue(this);//creating a simple request
        LstringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {//GET method
            @Override
            public void onResponse(String response) {

                Log.i(TAG, response.toString());
                textView.setText("Response is: "+ response.substring(0,500));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,error.toString());
                textView.setText("That didn't work!");
            }



        });

        LrequestQueue.add(LstringRequest);
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum lenght of password should be 6");
            editTextPassword.requestFocus();
            return;
        }


        progressbar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(),"User Registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TestActivity.this,SecondActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){

                    Toast.makeText(getApplicationContext(),"you are allready registered",Toast.LENGTH_SHORT).show();

                }else
                       // Toast.makeText(getApplicationContext(),"some error occured",Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                   //


            }
        });

    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                registerUser();
                break;

            case R.id.textViewLogin:
                //finish();
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }
    }
}

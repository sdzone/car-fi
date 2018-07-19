package com.example.sdzone.carfi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
   /* private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private TextView signup;*/
    //public TextView signuptext;
    private Button signup;

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    ProgressBar progressbar;


    private static final String TAG = HomeActivity.class.getSimpleName();
    private Button buttonReg;
    private boolean res;
    private Button sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressbar=(ProgressBar)findViewById(R.id.progressbar);
       /* signup=(TextView)findViewById(R.id.textViewSignup1);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTestActivity ();
            }
        });*/


     /*   signuptext=(TextView)findViewById(R.id.SIGNUPTEXT);
        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTestActivity ();
            }
        });*/

        signup=(Button)findViewById(R.id.signupbn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openTestActivity ();
               // openAddaCarActivity();
                openSignUpActivity();
            }
        });




        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.buttonLogin).setOnClickListener(this);
       // findViewById(R.id.textViewSignup1).setOnClickListener(this);

       /*
        public void openSecondActivity () {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }

        public void openSellActivity () {
            Intent intent = new Intent(this, SellActivity.class);
            startActivity(intent);
        }*/


    }
    public void openTestActivity () {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void userLogin(){

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
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    finish();
                Intent intent = new Intent(HomeActivity.this,SecondActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
           // case R.id.textViewSignup:

               // startActivity(new Intent(this, TestActivity.class));
               // break;

            case R.id.buttonLogin:
               userLogin();
               break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(this,SecondActivity.class));
        }
    }
}
//
//
//

//
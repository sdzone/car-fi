package com.example.sdzone.carfi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity  {

    private Button signup;

    EditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressbar;
    private Button button;
    private TextView login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
       // findViewById(R.id.buttonSignUp).setOnClickListener(this);
        //findViewById(R.id.textViewSignup1).setOnClickListener(this);
        button = (Button) findViewById(R.id.buttonSignUp);
        login=(TextView)findViewById(R.id.textViewLogin) ;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              loginActivity();
          }
      });
    }

    public void loginActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    public void openSignUpActivity () {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }





    private void registerUser() {
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

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(),"User Registered successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                    Toast.makeText(getApplicationContext(), "you are allready registered", Toast.LENGTH_SHORT).show();

                } else
                    // Toast.makeText(getApplicationContext(),"some error occured",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                //


            }
        });

    }



}

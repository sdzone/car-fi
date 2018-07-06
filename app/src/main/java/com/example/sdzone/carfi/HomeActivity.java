package com.example.sdzone.carfi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class HomeActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private RequestQueue LrequestQueue; //volley request queue
    private StringRequest LstringRequest; //volley sttringrequest
    private String url="http://www.google.com";//specify the connection "URI"
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);


        Info.setText("No of attempts remaining: 5");



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate(Name.getText().toString(), Password.getText().toString());

               SendRequestAndPrint(); //server connection method
            }
        });
    }

    private void SendRequestAndPrint() {

        LrequestQueue = Volley.newRequestQueue(this);//creating a simple request
        LstringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {//GET method
            @Override
            public void onResponse(String response) {

                Log.i(TAG, response.toString());
                Info.setText("Response is: "+ response.substring(0,500));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,error.toString());
                Info.setText("That didn't work!");
            }



    });

        LrequestQueue.add(LstringRequest);
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(HomeActivity.this, SecondActivity.class);
            startActivity(intent);
        }else{
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }

//    // Instantiate the RequestQueue.
//    RequestQueue queue = Volley.newRequestQueue(this);
//    String url ="http://www.google.com";
//
//    // Request a string response from the provided URL.
//    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//            new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    // Display the first 500 characters of the response string.
//                   // mTextView.setText("Response is: "+ response.substring(0,500));
//                }
//            }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            //mTextView.setText("That didn't work!");
//        }
 //   });

// Add the request to the RequestQueue.
//queue.add(stringRequest);
}


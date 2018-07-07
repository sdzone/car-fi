package com.example.sdzone.carfi;

import android.content.Intent;
import android.service.voice.VoiceInteractionSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TestActivity extends AppCompatActivity {

  /* private Button button;
    private  TextView textView;
    private String server_url="http://192.168.1.2/greetings.php";*/
  private TextView textView;
    private Button button;
    private RequestQueue LrequestQueue; //volley request queue
    private StringRequest LstringRequest; //volley sttringrequest
    private String url="http://www.google.com";//specify the connection "URI"
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button =(Button)findViewById(R.id.bn);
        textView=(TextView)findViewById(R.id.txt);
       button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendRequestAndPrint();
            }
        });







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
}

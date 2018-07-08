package com.example.sdzone.carfi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private RequestQueue LrequestQueue; //volley request queue
    private StringRequest LstringRequest; //volley sttringrequest
    private RequestQueue LoginRq ;
    private JsonObjectRequest loginJq;
    private String url="http://192.168.8.103:7077/carfi/values/userlogin";//specify the connection "URI"
    private static final String TAG = HomeActivity.class.getSimpleName();
    private Button buttonReg;
    private boolean res;
    private Button sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonReg=(Button)findViewById(R.id.button2);
        sell=(Button)findViewById(R.id.sellbn);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTestActivity();
            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSellActivity();
            }
        });


        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);


        Info.setText("No of attempts remaining: 5");



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate(Name.getText().toString(), Password.getText().toString());

//               res=usr();
//               if (res==true){
//                   openTestActivity();
//               }
//               else {
//                   Info.setText("Invalid credentials.");
//               }
//               //server connection method
//            }

                try {
                  boolean tur=  usr(Name.getText().toString(), Password.getText().toString());
                  if (tur ==true){
                      openTestActivity();
                  } else {
                      Info.setText("Invalid Credentials");
                  }
                } catch (JSONException e) {
                    System.out.println("++++++++++++++++++++++++++++++");
                    e.printStackTrace();
                }
            }
            });
    }


    public void openTestActivity(){
        Intent intent = new Intent(this,TestActivity.class);
        startActivity(intent);
    }
    public void openSellActivity(){
        Intent intent = new Intent(this,SellActivity.class);
        startActivity(intent);
    }


    private void SendRequestAndPrint() {

        LrequestQueue = Volley.newRequestQueue(this);//creating a simple request
        LstringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {//GET method
            @Override
            public void onResponse(String response) {

                Log.i(TAG, response.toString());
                Info.setText("Response is: "+ response.substring(0,10));


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

//   ew/f
//
//    public boolean login_usr(String usrid,String passwd)
//    {
//        final int[] val =new int[1];
//
//           Map<String, String> postParam= new HashMap<String, String>();
//            postParam.put("un", "xyz@gmail.com");
//            postParam.put("p", "somepasswordhere");
//
//
//         JsonObjectRequest jsonObjReq = new JsonObjectRequest (Request.Method.POST,
//            url, new JSONObject(postParam),
//            new Response.Listener<JSONObject>() {
//
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.d(TAG, response.toString());
//
//                    val[0] = Integer.parseInt(response.toString());
//                    hideProgressDialog();
//                }
//            }, new Response.ErrorListener() {
//
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            VolleyLog.d(TAG, "Error: " + error.getMessage());
//            hideProgressDialog();
//        }
//    }) {
//
//
//             // Adding request to request queue
//    queue.add(jsonObjReq);
//        if(val[0] ==1){
//            return true;
//        }
//        else {
//            return false;
//        }
//
//
//    }


//    public boolean send_usr(final String usr, final String passwd){
//        final int[] val = new int[1];
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest sr = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("HttpClient", "success! response: " + response.toString());
//                        val[0] = Integer.parseInt(response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("HttpClient", "error: " + error.toString());
//                    }
//                })
//        {
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("user",usr);
//                params.put("pass",passwd);
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//
//
//        };
//        queue.add(sr);
//        if(val[0] ==1){
//          return true;
//        }
//        else {
//            return false;
//        }
//    }
//

     public  boolean usr(String un,String pw) throws JSONException {
         final int[] val = new int[1];

         final String uname = null;
         //final String[] pw = {null};
         final String[] resu = new String[1];
         //Context c;
         final Gson[] gs = {new Gson()};
         RequestQueue queue = Volley.newRequestQueue(this);

         HashMap<String, String> postParam = new HashMap<String, String>();
         //JSONObject jsonObject = new JSONObject("{username: uthpala , pass:123}");

         postParam.put("username", un);
         postParam.put("pass", pw);
         Log.e(TAG,"asdfg");
         JsonObjectRequest jo = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), new Response.Listener<JSONObject>() {

             @Override
             public void onResponse(JSONObject response) {

                 //Log.e(TAG,response.toString());

                 gs[1].toJson(response);

                 
                 Log.e(TAG,"-------------");
                 resu[1] = gs[1].getClass().toString();
                 Log.e(TAG, gs[1].toString());

                 if (resu[1]=="true"){

                     val[1]=1;

                 }

//                 if(response.length() > 0){
//                     for(int i = 0 ; i < response.lengthh() ; i++ ){
//                         try{
//                             JSONObject jsonres = response.getJSONObject(i);
//                             Log.e(TAG,jsonres.toString());
//                         } catch (Exception e ){
//
//                         }
//
//                     }
//                 }


             }
         },
                 new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         Log.e("HttpClient", "error: " + error.toString());
                     }
                 }) {

//             @Override
//             public Map<String, String> getHeaders() throws AuthFailureError {
//                 Map<String, String> params = new HashMap<String, String>();
//                 params.put("Content-Type", "application/json; charset=utf-8");
//                 return params;
//             }


         };
           queue.add(jo);

           System.out.print(val);

           if(val[1]==1){
               return true;
           }
           else {
               return false;
           }

     }

     }




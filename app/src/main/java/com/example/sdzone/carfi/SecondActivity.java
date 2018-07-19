package com.example.sdzone.carfi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


private CardView sellCard,findCard;
private Button addcar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //defining cards
        addcar=(Button)findViewById(R.id.addacarbn);
        sellCard=(CardView)findViewById(R.id.sell_car);
        findCard=(CardView)findViewById(R.id.find_car);
        //add click listner to cards
        sellCard.setOnClickListener(this);
        findCard.setOnClickListener(this);
//        addcar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addacar();
//            }
//        });
      addcar.setOnClickListener(this);


    }

//    public void addacar(){
//        Intent intent = new Intent(this, AddCarActivity.class);
//        startActivity(intent);
//    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.sell_car:i=new Intent(this,SellActivity.class);startActivity(i);break;
            case R.id.find_car:i=new Intent(this,FindActivity.class);startActivity(i);break;
            case R.id.addacarbn:i=new Intent(this,CarAdd2Activity.class);startActivity(i);break;
            default:break;

        }


    }
}

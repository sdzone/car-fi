package com.example.sdzone.carfi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
private CardView sellCard,findCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //defining cards

        sellCard=(CardView)findViewById(R.id.sell_car);
        findCard=(CardView)findViewById(R.id.find_car);
        //add click listner to cards
        sellCard.setOnClickListener(this);
        findCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.sell_car:i=new Intent(this,SellActivity.class);startActivity(i);break;
            case R.id.find_car:i=new Intent(this,FindActivity.class);startActivity(i);break;
            default:break;

        }

    }
}

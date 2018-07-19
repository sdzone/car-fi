package com.example.sdzone.carfi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int CHOOSE_IMAGE = 101;
    AutoCompleteTextView suggestion_box;
    Spinner items;
    //camera
    Button button;

    ImageView imageViewaa;
    EditText editText;
    Uri uriProfileImage;
    TextView texa;



   // static final int CAM_REQUEST=1;


    ArrayList<String> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageViewaa = (ImageView) findViewById(R.id.ImageSelect);
        editText = (EditText) findViewById(R.id.editTextDisplayName);
        texa=(TextView)findViewById(R.id.textView8);

            setContentView(R.layout.activity_add_car);
           //




        suggestion_box = (AutoCompleteTextView) findViewById(R.id.suggestion_box);


        items = (Spinner) findViewById(R.id.items);
        foods.add("select car brand");


        foods.add("Alfa Romeo");


        foods.add("Aston Martin");


        foods.add("Audi");


        foods.add("Austin");


        foods.add("BMW");


        foods.add("Buick");


        foods.add("Cadilac");


        foods.add("Changan");
        foods.add("Chery");


        foods.add("Chevrolet");


        foods.add("Chrysler");


        foods.add("Citroen");


        foods.add("Daewoo");


        foods.add("Daihatsu");


        foods.add("Datsun");


        foods.add("Dodge");


        foods.add("Ferrari");


        foods.add("Fiat");


        foods.add("Ford");


        foods.add("Geely");


        foods.add("GMC");


        foods.add("Hino");


        foods.add("Honda");


        foods.add("Hummer");
        foods.add("Hyundai");


        foods.add("Isuzu");


        foods.add("Chrysler");


        foods.add("Jaguar");


        foods.add("Jeep");


        foods.add("Kia");


        foods.add("Lamborghini");


        foods.add("Land Rover");

        foods.add("Lexus");


        foods.add("Lincoln");


        foods.add("Mahindra");


        foods.add("Maruti");


        foods.add("Mazda");


        foods.add("Mercedes-Benz");


        foods.add("MG");

        foods.add("Micro");


        foods.add("Mini");


        foods.add("Mitsubishi");


        foods.add("Morris");


        foods.add("Moto Guzzi");


        foods.add("Nissan");


        foods.add("oldsmobile");

        foods.add("Opel");

        foods.add("Perodua");


        foods.add("Peugeot");


        foods.add("Plymouth");


        foods.add("Pontiac");


        foods.add("Porsche");


        foods.add("Proton");


        foods.add("Renault");

        foods.add("Rover");

        foods.add("Royal Enfield");


        foods.add("SAAB");


        foods.add("Scion");


        foods.add("SEAT");


        foods.add("Skoda");


        foods.add("Smart");


        foods.add("Ssang Yong");

        foods.add("Subaru");

        foods.add("Suzuki");

        foods.add("Tata");


        foods.add("Toyota");


        foods.add("Vauxhall");


        foods.add("Volkswagen");


        foods.add("Volvo");


        foods.add("Zotye");


        foods.add("Other Brand");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCarActivity.this, android.R.layout.simple_spinner_dropdown_item, foods);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(AddCarActivity.this, android.R.layout.simple_spinner_dropdown_item, foods);
        suggestion_box.setAdapter(adapter);
        items.setAdapter(adapter);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CHOOSE_IMAGE && resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            uriProfileImage=data.getData();
            try {
                Bitmap bitmap =MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
               imageViewaa.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }




    public void openSellActivity() {
        Intent intent = new Intent(this, SellActivity.class);
        startActivity(intent);
  }




    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView8:
                showImageChooser();
                break;
        }
    }
}
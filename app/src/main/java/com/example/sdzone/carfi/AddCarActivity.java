package com.example.sdzone.carfi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;


import java.io.File;
import java.util.ArrayList;

public class AddCarActivity extends AppCompatActivity {
    AutoCompleteTextView suggestion_box;
    Spinner items;
    //camera
    Button button;
    ImageView imageView;
    static final int CAM_REQUEST=1;


    ArrayList<String> foods = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_add_car);

//camera
        button=(Button)findViewById(R.id.button);
        imageView=(ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file= getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent,CAM_REQUEST);

            }
        });


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

private File getFile(){

        File folder = new File("sdcard/camera_app");
    //File folder = new File(Environment.getExternalStorageDirectory(),"camera_app");
        if (!folder.exists()){
            folder.mkdir();
        }
        File image_file= new File(folder,"cam_image.jpg");

        return image_file;
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
String path="sdcard/camera_app/cam_image.jpg";
imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}
package com.mehmetomurerdolu.booking.ui;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.mehmetomurerdolu.booking.R;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class AddEstateActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 121;
    ViewFlipper imageFlipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estate);
        imageFlipper = findViewById( R.id.image_flipper );
        findViewById(R.id.add_photo).setOnClickListener(this);
        findViewById(R.id.back_photo).setOnClickListener(this);
        findViewById(R.id.next_photo).setOnClickListener(this);
        imageFlipper.setFlipInterval( 5000 ); //5s intervals
        imageFlipper.startFlipping();
    }

    public void pickImage(){
        Intent i = new Intent(
                Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void addSlider(Bitmap image){
        ImageView imageView = new ImageView( getApplicationContext() );
        imageView.setImageBitmap(image);
        imageFlipper.addView( imageView );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            final Uri imageUri = data.getData();
            Bitmap selectedImage = null;
            try {
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (selectedImage != null) {
                addSlider(selectedImage);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_photo){
            pickImage();
        }else if(v.getId() == R.id.back_photo){
            imageFlipper.showPrevious();
        }else if(v.getId() == R.id.next_photo){
            imageFlipper.showNext();
        }
    }
}
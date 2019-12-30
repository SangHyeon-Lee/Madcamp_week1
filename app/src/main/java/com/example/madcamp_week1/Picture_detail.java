package com.example.madcamp_week1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Picture_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        ImageView detail_image = (ImageView) findViewById(R.id.detail_image);

        Intent intent = getIntent();

        int img_num = intent.getExtras().getInt("img_num");

        switch(img_num) {
            case 1:
                detail_image.setImageResource(R.drawable.img1);
                break;
            case 2:
                detail_image.setImageResource(R.drawable.img2);
                break;
            case 3:
                detail_image.setImageResource(R.drawable.img3);
                break;
        }



        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

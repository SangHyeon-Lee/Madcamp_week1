package com.example.madcamp_week1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Picture_detail extends AppCompatActivity {

    ImageView detail_image;
    Uri uri_id;
    Uri uri_name;
    Uri uri_date;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture_detail);

        detail_image = (ImageView) findViewById(R.id.detail_image);
        TextView img_name = (TextView) findViewById(R.id.img_name);
        TextView img_date = (TextView) findViewById(R.id.img_date);

        Intent intent = getIntent();

        final List<Gallery_view.ImageDTO> gallery_list = intent.getExtras().getParcelableArrayList("imglist");
        final int position = intent.getExtras().getInt("position");

        uri_id = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (gallery_list.get(position)).getId());
        uri_name = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (gallery_list.get(position)).getDisplayname());
        uri_date = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (gallery_list.get(position)).getDate());

        detail_image.setImageURI(uri_id);




        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}

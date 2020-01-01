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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Picture_detail extends AppCompatActivity {

    ImageView detail_image;
    Uri uri_id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picture_detail);

        detail_image = (ImageView) findViewById(R.id.detail_image);
        TextView img_name = (TextView) findViewById(R.id.img_name);
        TextView img_date = (TextView) findViewById(R.id.img_date);
        TextView img_size = (TextView) findViewById(R.id.img_size);

        Intent intent = getIntent();

        final List<Gallery_view.ImageDTO> gallery_list = intent.getExtras().getParcelableArrayList("imglist");
        final int position = intent.getExtras().getInt("position");

        uri_id = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (gallery_list.get(position)).getId());
        String name = gallery_list.get(position).getDisplayname();
        String date = gallery_list.get(position).getDate();
        String size = gallery_list.get(position).getSize();


        Long setDate;
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");

        setDate = Long.parseLong(date);
        setDate = setDate * 1000;


        String putdate = dayTime.format(new Date(setDate));
        detail_image.setImageURI(uri_id);
        img_name.setText(name);
        img_date.setText(putdate);
        img_size.setText(byteCalculation(size));



        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public String byteCalculation(String bytes) {
        String retFormat = "0";
        Double size = Double.parseDouble(bytes);

        String[] s = { "bytes", "KB", "MB", "GB", "TB", "PB" };


        if (bytes != "0") {
            int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
            DecimalFormat df = new DecimalFormat("#,###.##");
            double ret = ((size / Math.pow(1024, Math.floor(idx))));
            retFormat = df.format(ret) + " " + s[idx];
        } else {
            retFormat += " " + s[0];
        }

        return retFormat;
    }

}

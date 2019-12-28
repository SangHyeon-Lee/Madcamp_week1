package com.example.madcamp_week1;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imgview;
    TextView name, phone_number;
    ViewHolder(View itemView){
        super(itemView);
        imgview = itemView.findViewById(R.id.sample_contact_img);
        name = itemView.findViewById(R.id.contact_name);
        phone_number = itemView.findViewById(R.id.contact_phone_number);
    }
}

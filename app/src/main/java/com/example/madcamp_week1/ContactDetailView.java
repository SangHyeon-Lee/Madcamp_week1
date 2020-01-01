package com.example.madcamp_week1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactDetailView extends AppCompatActivity {

    TextView name, phone_number, address, company, job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail_view);
        name = findViewById(R.id.contact_detail_name);
        phone_number = findViewById(R.id.contact_detail_phone_number);
        address = findViewById(R.id.contact_detail_address);
        company = findViewById(R.id.contact_detail_company);
        job = findViewById(R.id.contact_detail_job);

        Contact_view.Contact contact_data = (Contact_view.Contact) getIntent().getParcelableExtra("data");

        name.setText(name.getText() + contact_data.name);
        phone_number.setText(phone_number.getText() + contact_data.phone_number);
        address.setText(address.getText() + contact_data.address);
        company.setText(company.getText() + contact_data.company);
        job.setText(job.getText() + contact_data.job);
    }
}

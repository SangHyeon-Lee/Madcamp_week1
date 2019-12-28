package com.example.madcamp_week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Contact_view extends Fragment {

    ArrayList<Contact> contactList = new ArrayList<>();

    public class Contact{
        private String name;
        private String phone_number;

        public String getName() {
            return name;
        }

        public String getPhone_number() {
            return phone_number;
        }


        public void setName(String name) {
            this.name = name;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

    }


    public static Contact_view newInstance() {
        Contact_view fragmentFirst = new Contact_view();
        return fragmentFirst;
    }

    public String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getResources().getAssets().open("data.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    public void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray movieArray = jsonObject.getJSONArray("Contact");

            for(int i=0; i<movieArray.length(); i++)
            {
                JSONObject contactObject = movieArray.getJSONObject(i);

                Contact contact = new Contact();

                contact.setName(contactObject.getString("name"));
                contact.setPhone_number(contactObject.getString("phone_number"));

                contactList.add(contact);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.activity_contact_view, container, false);
        return view;
    }
}

package com.example.madcamp_week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Gallery_view extends Fragment {

    public static Gallery_view newInstance() {
        Gallery_view fragmentFirst = new Gallery_view();
        return fragmentFirst;
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

        View view = inflater.inflate(R.layout.activity_gallery_view, container, false);

        ImageButton imgbutton1 = (ImageButton) view.findViewById(R.id.imgbutton1);
        imgbutton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Picture_detail.class);

                intent.putExtra("img_num", 1);

                startActivity(intent);
            }
        });
        ImageButton imgbutton2 = (ImageButton) view.findViewById(R.id.imgbutton2);
        imgbutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Picture_detail.class);

                intent.putExtra("img_num", 2);

                startActivity(intent);
            }
        });
        ImageButton imgbutton3 = (ImageButton) view.findViewById(R.id.imgbutton3);
        imgbutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), Picture_detail.class);

                intent.putExtra("img_num", 3);

                startActivity(intent);
            }
        });

        return view;
    }

}

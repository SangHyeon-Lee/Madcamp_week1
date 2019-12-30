package com.example.madcamp_week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
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

        final int[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5,
                R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11,
                R.drawable.img12, R.drawable.img13,R.drawable.img14,R.drawable.img15,R.drawable.img16,R.drawable.img17,
                R.drawable.img18,R.drawable.img19,R.drawable.img20,};
        int[] imgs_set = {R.id.img_set1, R.id.img_set2, R.id.img_set3, R.id.img_set4, R.id.img_set5, R.id.img_set6,
                R.id.img_set7, R.id.img_set8, R.id.img_set9, R.id.img_set10, R.id.img_set11, R.id.img_set12,
                R.id.img_set13, R.id.img_set14, R.id.img_set15, R.id.img_set16, R.id.img_set17, R.id.img_set18,
                R.id.img_set19, R.id.img_set20};

        for (int i=0; i<imgs.length; i++){

            final ImageButton img_button = (ImageButton) view.findViewById(imgs_set[i]);
            img_button.setImageResource(imgs[i]);

            img_button.setTag(imgs[i]);
            img_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Picture_detail.class);
                    intent.putExtra("img", (int)img_button.getTag());
                    startActivity(intent);

                }
            });
        }

        return view;
    }

}

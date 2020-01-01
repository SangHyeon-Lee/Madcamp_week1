package com.example.madcamp_week1;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class Image_GridView_Adapter extends BaseAdapter {

    List<Gallery_view.ImageDTO> gallery_list;
    View view;
    LayoutInflater inflater;

    public Image_GridView_Adapter(View view, ArrayList<Gallery_view.ImageDTO> gallery_list) {
        this.gallery_list = gallery_list;
        this.view = view;
        this.inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return gallery_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_image_grid_view_item, parent, false);

        }

        ImageView imageView = convertView.findViewById(R.id.image);
        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, (gallery_list.get(position)).getId());
        imageView.setImageURI(uri);

        return convertView;
    }
}

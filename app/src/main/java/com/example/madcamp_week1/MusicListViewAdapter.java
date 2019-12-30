package com.example.madcamp_week1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class MusicListViewAdapter extends BaseAdapter {
    List<Music_view.MusicDTO> music_list;
    View view;
    LayoutInflater inflater;

    public MusicListViewAdapter(View view, ArrayList<Music_view.MusicDTO> music_list) {
        this.music_list = music_list;
        this.view = view;
        this.inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return music_list.size();
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
            convertView = inflater.inflate(R.layout.music_list_view_item, parent, false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            convertView.setLayoutParams(layoutParams);
        }

        ImageView imageView = convertView.findViewById(R.id.music_album);
        Bitmap albumImage = getAlbumImage(view.getContext(), Integer.parseInt((music_list.get(position)).getAlbumId()), 170);
        imageView.setImageBitmap(albumImage);

        TextView title = convertView.findViewById(R.id.music_title);
        title.setText(music_list.get(position).getTitle());

        TextView artist = convertView.findViewById(R.id.music_artist);
        artist.setText(music_list.get(position).getArtist());

        return convertView;
    }

    private static final BitmapFactory.Options options = new BitmapFactory.Options();

    private Bitmap getAlbumImage(Context context, int album_id, int MAX_IMAGE_SIZE) {
        ContentResolver res = context.getContentResolver();
        Uri uri = Uri.parse("content://media/external/audio/albumart/" + album_id);
        if (uri != null) {
            ParcelFileDescriptor fd = null;
            try {
                double base;
                fd = res.openFileDescriptor(uri, "r");
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFileDescriptor((
                        fd.getFileDescriptor()), null, options);
                int scale = 0;
                base = (int) Math.log(MAX_IMAGE_SIZE / (double) Math.max(options.outHeight, options.outWidth)) / Math.log(0.5);
                if (options.outHeight > MAX_IMAGE_SIZE || options.outWidth > MAX_IMAGE_SIZE)
                    scale = (int) Math.pow(2, base);
                options.inJustDecodeBounds = false;
                options.inSampleSize = scale;

                Bitmap bitmap_fd = BitmapFactory.decodeFileDescriptor(
                        fd.getFileDescriptor(), null, options);
                if (bitmap_fd != null && options.outWidth != MAX_IMAGE_SIZE || options.outHeight != MAX_IMAGE_SIZE) {
                    Bitmap tmp = Bitmap.createScaledBitmap(bitmap_fd, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE, true);
                    bitmap_fd.recycle();
                    bitmap_fd = tmp;
                }
                return bitmap_fd;
            } catch (FileNotFoundException e) {
                Log.d("[MUSIC_FILE_NOT_FOUND]", e.toString());
            } finally {
                try {
                    if (fd != null)
                        fd.close();
                } catch (IOException e) {
                    Log.d("[MUSIC IO ERROR]", e.toString());
                }
            }
        }
        return null;
    }
}

package com.example.madcamp_week1;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Music_view extends Fragment implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    Button btnStart, btnStop;
    private ArrayList<MusicDTO> music_list;

    public static Music_view newInstance() {
        Music_view fragmentFirst = new Music_view();
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
        View view = inflater.inflate(R.layout.activity_music_view, container, false);

        getMusicList(view);
        for (MusicDTO tmp :
                music_list) {
            Log.d("[MusicList]", tmp.toString());
        }
//        btnStart = view.findViewById(R.id.btn_music_start);
//        btnStop = view.findViewById(R.id.btn_music_pause);
//        btnStart.setOnClickListener(this);
//        btnStop.setOnClickListener(this);
        return view;
    }

    private void getMusicList(View view) {
        music_list = new ArrayList<>();
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
        };

        Cursor cursor = view.getContext().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null);

        while(cursor.moveToNext()){
            MusicDTO music_dto = new MusicDTO();
            music_dto.setId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            music_dto.setAlbumId(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)));
            music_dto.setTitle(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            music_dto.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            music_list.add(music_dto);
        }
        cursor.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_music_start:
//                mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.high);
//                mediaPlayer.start();
//                break;
//            case R.id.btn_music_pause:
//                mediaPlayer.pause();
//                mediaPlayer.reset();
//                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private class MusicDTO {
        private String id;
        private String albumId;
        private String title;
        private String artist;

        public MusicDTO() {
        }

        public MusicDTO(String id, String albumId, String title, String artist) {
            this.id = id;
            this.albumId = albumId;
            this.title = title;
            this.artist = artist;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlbumId() {
            return albumId;
        }

        public void setAlbumId(String albumId) {
            this.albumId = albumId;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "MusicDto{" +
                    "id='" + id + '\'' +
                    ", albumId='" + albumId + '\'' +
                    ", title='" + title + '\'' +
                    ", artist='" + artist + '\'' +
                    '}';
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }
    }
}

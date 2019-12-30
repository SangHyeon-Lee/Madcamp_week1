package com.example.madcamp_week1;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class Music_view extends Fragment {

    private ArrayList<MusicDTO> music_list;
    private ListView musicListView;

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
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_music_view, container, false);
        getMusicList(view);
        musicListView = view.findViewById(R.id.music_listview);
        musicListView.setAdapter(new MusicListViewAdapter(view, music_list));

        musicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), MusicDetailView.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("playlist", (ArrayList<? extends Parcelable>)music_list);
                bundle.putSerializable("position", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
    public static class MusicDTO implements Parcelable {
        private String id;
        private String albumId;
        private String title;
        private String artist;

        public MusicDTO(){}

        public MusicDTO(Parcel in){
            id = in.readString();
            albumId = in.readString();
            title = in.readString();
            artist = in.readString();
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

        public static final Creator<MusicDTO> CREATOR = new Creator<MusicDTO>(){
            @Override
            public MusicDTO createFromParcel(Parcel source) {
                return new MusicDTO(source);
            }

            @Override
            public MusicDTO[] newArray(int size) {
                return new MusicDTO[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(albumId);
            dest.writeString(title);
            dest.writeString(artist);
        }
    }

}

package com.example.madcamp_week1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Music_view extends Fragment implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    Button btnStart, btnStop;


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
        btnStart = view.findViewById(R.id.btn_music_start);
        btnStop = view.findViewById(R.id.btn_music_stop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_music_start:
                mediaPlayer = MediaPlayer.create(this.getContext(), R.raw.high);
                mediaPlayer.start();
                break;
            case R.id.btn_music_stop:
                mediaPlayer.stop();
                mediaPlayer.reset();
                break;
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

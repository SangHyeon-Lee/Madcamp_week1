package com.example.madcamp_week1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MusicDetailView extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Music_view.MusicDTO> music_list;
    private MediaPlayer mediaPlayer;
    private TextView title;
    private ImageView album, previous, play, pause, next, shuffle;
    private SeekBar seek_bar;
    boolean isPlaying = true;
    private ContentResolver res;
    private ProgressUpdate progressUpdate;
    private int position;
    private boolean isShuffle = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_detail_view);
        Intent intent = getIntent();
        mediaPlayer = new MediaPlayer();
        title = (TextView)findViewById(R.id.title);
        album = (ImageView)findViewById(R.id.album);
        seek_bar = (SeekBar)findViewById(R.id.seek_bar);

        position = intent.getIntExtra("position",0);
        music_list = (ArrayList<Music_view.MusicDTO>) intent.getSerializableExtra("playlist");
        res = getContentResolver();

        previous = (ImageView)findViewById(R.id.pre);
        play = (ImageView)findViewById(R.id.play);
        pause = (ImageView)findViewById(R.id.pause);
        next = (ImageView)findViewById(R.id.next);
        shuffle = findViewById(R.id.shuffle);

        previous.setOnClickListener(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        next.setOnClickListener(this);
        shuffle.setOnClickListener(this);

        playMusic(music_list.get(position));
        progressUpdate = new ProgressUpdate();
        progressUpdate.start();

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                if(seekBar.getProgress()>0 && play.getVisibility()== View.GONE){
                    mediaPlayer.start();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play_next_music(1);
            }
        });
    }

    private void play_next_music(int weight) {
        if(isShuffle){
            position = new Random().nextInt(music_list.size());
            playMusic(music_list.get(position));
        }
        else {
            position += weight;
            if(position >= 0 && position < music_list.size())
                playMusic(music_list.get(position));
            else
                throw new RuntimeException("Error At play next music");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                pause.setVisibility(View.VISIBLE);
                play.setVisibility(View.GONE);
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                mediaPlayer.start();
                break;
            case R.id.pause:
                pause.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                break;
            case R.id.pre:
                Log.d("[PRE_BUTTON]", "Pressed");
                play_next_music(-1);
//                if(position - 1 >= 0 ){
//                    position--;
//                    playMusic(music_list.get(position));
//                    seek_bar.setProgress(0);
//                }
                break;
            case R.id.next:
                Log.d("[NEXT_BUTTON]", "Pressed");
                play_next_music(1);
//                if(position+1<music_list.size()){
//                    position++;
//                    playMusic(music_list.get(position));
//                    seek_bar.setProgress(0);
//                }
                break;
            case R.id.shuffle:
                if (isShuffle) {
                    shuffle.setColorFilter(null);
                } else {
                    shuffle.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
                isShuffle = !isShuffle;
        }
    }

    private class ProgressUpdate extends Thread {
        @Override
        public void run(){
            while(isPlaying){
                try{
                    Thread.sleep(500);
                    if(mediaPlayer != null)
                        seek_bar.setProgress(mediaPlayer.getCurrentPosition());
                } catch (Exception e){
                    Log.e("ProgressUpdate", e.getMessage());
                }
            }
        }
    }

    private static String getCoverArtPath(long albumId, Context context) {

        Cursor albumCursor = context.getContentResolver().query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Albums.ALBUM_ART},
                MediaStore.Audio.Albums._ID + " = ?",
                new String[]{Long.toString(albumId)},
                null
        );
        boolean queryResult = albumCursor.moveToFirst();
        String result = null;
        if (queryResult) {
            result = albumCursor.getString(0);
        }
        albumCursor.close();
        return result;
    }

    private void playMusic(Music_view.MusicDTO musicDTO) {
        try {
            seek_bar.setProgress(0);
            if(musicDTO.getAlbumId().length() > 7)
                title.setText(musicDTO.getTitle());
            else
                title.setText(musicDTO.getArtist()+" - "+musicDTO.getTitle());
            Uri musicURI = Uri.withAppendedPath(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, ""+musicDTO.getId());
            mediaPlayer.reset();
            mediaPlayer.setDataSource(this, musicURI);
            mediaPlayer.prepare();
            mediaPlayer.start();
            seek_bar.setMax(mediaPlayer.getDuration());
            if(mediaPlayer.isPlaying()){
                play.setVisibility(View.GONE);
                pause.setVisibility(View.VISIBLE);
            }else{
                play.setVisibility(View.VISIBLE);
                pause.setVisibility(View.GONE);
            }


            Bitmap bitmap = BitmapFactory.decodeFile(getCoverArtPath(Long.parseLong(musicDTO.getAlbumId()),getApplication()));
            album.setImageBitmap(bitmap);

        }
        catch (Exception e) {
            Log.e("SimplePlayer", e.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isPlaying = false;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

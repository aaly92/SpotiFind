package com.example.aaly.spotifind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class ArtistInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_info_layout);
        Intent activityThatCalled = getIntent();
        Artist artist = (Artist) activityThatCalled.getSerializableExtra("artist");

        TextView artistNameView = (TextView) findViewById(R.id.artistName);
        if(artistNameView != null && artist !=null ) {
            artistNameView.setText(artist.getName());
        }
    }

}

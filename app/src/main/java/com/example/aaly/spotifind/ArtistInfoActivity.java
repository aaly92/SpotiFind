package com.example.aaly.spotifind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.aaly.spotifind.data.model.Item;


public class ArtistInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_info_layout);
        Intent activityThatCalled = getIntent();
        Item artist = (Item) activityThatCalled.getSerializableExtra("artist");

        TextView artistNameView = (TextView) findViewById(R.id.artistName);
        if(artistNameView != null && artist !=null ) {
            artistNameView.setText(artist.getName());
        }
    }

}

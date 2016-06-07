package com.example.aaly.spotifind;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aaly.spotifind.data.model.Item;
import com.squareup.picasso.Picasso;

import java.util.LinkedHashMap;


public class ArtistInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_info_layout);
        Intent activityThatCalled = getIntent();
        Item artist = (Item) activityThatCalled.getSerializableExtra("artist");
        TextView artistNameView = (TextView) findViewById(R.id.artistName);
        if (artist != null && artist.getImages().size() > 0) {
            ImageView artistImageView = (ImageView) findViewById(R.id.artistImage);
            Picasso.with(ArtistInfoActivity.this)
                    .load(((LinkedHashMap) (artist.getImages().get(0))).get("url").toString())
                    .into(artistImageView);
        }
        if (artistNameView != null && artist != null) {
            artistNameView.setText(artist.getName());
        }
    }

}

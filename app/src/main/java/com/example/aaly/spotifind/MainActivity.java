package com.example.aaly.spotifind;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ListView artistListView;
    EditText artistNameView;
    ProgressBar progressBarView;
    Button searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistListView = (ListView) findViewById(R.id.artistList);
        artistNameView = (EditText) findViewById(R.id.artistName);
        progressBarView = (ProgressBar) findViewById(R.id.progressBar);
        searchButton = (Button) findViewById(R.id.search);
        searchButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                RetrieveArtistsTask getArtist = new RetrieveArtistsTask(artistListView, artistNameView, progressBarView, MainActivity.this);
                getArtist.execute();
            }
        });
    }


}

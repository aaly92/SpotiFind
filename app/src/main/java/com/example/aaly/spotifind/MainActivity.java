package com.example.aaly.spotifind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aaly.spotifind.data.model.Item;
import com.example.aaly.spotifind.data.remote.SpotifyArtistAPI;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpotifyArtistAPI.Factory.getInstance().getArtists(artistNameView.getText().toString()).enqueue(new Callback<com.example.aaly.spotifind.data.model.Artist>() {
                    @Override
                    public void onResponse(Call<com.example.aaly.spotifind.data.model.Artist> call, Response<com.example.aaly.spotifind.data.model.Artist> response) {

                        final List<Item> artistList = response.body().getArtists().getItems();
                        ArtistAdapter artistAdapter = new ArtistAdapter(MainActivity.this, artistList);
                        if (artistListView != null) {
                            artistListView.setAdapter(artistAdapter);
                            artistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    String artistPicked = "You selected " + String.valueOf(((Item) adapterView.getItemAtPosition(position)).getName());
                                    Toast.makeText(MainActivity.this, artistPicked, Toast.LENGTH_SHORT).show();
                                    Intent displayArtistInfoIntent = new Intent(MainActivity.this, ArtistInfoActivity.class);
                                    final int result = 1;
                                    Item item = artistList.get(position);
                                    displayArtistInfoIntent.putExtra("artist", item);
                                    startActivityForResult(displayArtistInfoIntent, result);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.aaly.spotifind.data.model.Artist> call, Throwable t) {
                        Log.e("Failed", t.getMessage());
                    }
                });
            }
        });
    }


}

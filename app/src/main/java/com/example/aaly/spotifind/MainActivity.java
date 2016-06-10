package com.example.aaly.spotifind;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aaly.spotifind.data.model.Artist;
import com.example.aaly.spotifind.data.model.Item;
import com.example.aaly.spotifind.data.remote.SpotifyArtistAPI;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText artistNameView;
    ProgressBar progressBarView;
    Button searchButton;
    private RecyclerView recyclerView;
    private ArtistAdapter artistAdapter;
    private List<Item> artistList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        artistAdapter = new ArtistAdapter(artistList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(artistAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                String artistPicked = "You selected " + String.valueOf( artistList.get(position).getName());
                Toast.makeText(MainActivity.this, artistPicked, Toast.LENGTH_SHORT).show();
                Intent displayArtistInfoIntent = new Intent(MainActivity.this, ArtistInfoActivity.class);
                Item item = artistList.get(position);
                displayArtistInfoIntent.putExtra("artist", item);
                startActivity(displayArtistInfoIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        artistNameView = (EditText) findViewById(R.id.artistName);
        progressBarView = (ProgressBar) findViewById(R.id.progressBar);
        searchButton = (Button) findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                SpotifyArtistAPI.Factory.getInstance().getArtists(artistNameView.getText().toString()).enqueue(new Callback<Artist>() {
                    @Override
                    public void onResponse(Call<Artist> call, Response<Artist> response) {
                        progressBarView.setVisibility(View.GONE);
                        artistAdapter.setArtistList(response.body().getArtists().getItems());
                        artistList = response.body().getArtists().getItems();
                        recyclerView.setVisibility(View.VISIBLE);
                        artistAdapter.notifyDataSetChanged();
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
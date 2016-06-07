package com.example.aaly.spotifind;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RetrieveArtistsTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private String artistName;
    private ProgressBar progressBar;
    private ListView responseView;


    public RetrieveArtistsTask(ListView responseView, EditText artistNameView, ProgressBar progressBarView, Context context) {
        this.responseView = responseView;
        this.artistName = artistNameView.getText().toString();
        this.progressBar = progressBarView;
        this.context = context;
    }


    final static String API_URL = "https://api.spotify.com/v1/search?type=artist";



    protected void onPreExecute() {
        progressBar.setVisibility(View.VISIBLE);
    }

    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL(API_URL + "&q=" + artistName );
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }
        progressBar.setVisibility(View.GONE);
        final ArrayList<Artist> artistsSuggestions = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(response).getJSONObject("artists");
            JSONArray artists = responseObject.getJSONArray("items");

            for(int i=0; i<artists.length(); i++){
                JSONObject jsonObj  = artists.getJSONObject(i);
                Artist artist = new Artist( jsonObj.getString("id"), jsonObj.getString("name") );
                artistsSuggestions.add(artist);
            }

        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage(), e);
        }
        Log.i("INFO", response);
        ArtistAdapter artistAdapter = new ArtistAdapter(context, artistsSuggestions);
        if(responseView != null){
            responseView.setAdapter(artistAdapter);
            responseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public  void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                    String artistPicked = "You selected " + String.valueOf(((Artist)adapterView.getItemAtPosition(position)).getName());
                    Toast.makeText(context, artistPicked, Toast.LENGTH_SHORT).show();
                    Intent displayArtistInfoIntent = new Intent(context, ArtistInfoActivity.class);
                    final int result = 1;
                    displayArtistInfoIntent.putExtra("artist", artistsSuggestions.get(position));
                    ((Activity) context).startActivityForResult(displayArtistInfoIntent, result);
                }
            });
        }
    }
}
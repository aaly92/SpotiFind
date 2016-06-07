package com.example.aaly.spotifind.data.remote;


import com.example.aaly.spotifind.data.model.Artists;
import com.example.aaly.spotifind.data.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpotifyArtistAPI  {

    String API_URL = "https://api.spotify.com/v1/";

    @GET("search?type=artist")
    Call<com.example.aaly.spotifind.data.model.Artist> getArtists(@Query("q") String stringToSearch);

    class Factory {
        private static SpotifyArtistAPI service;
        public static SpotifyArtistAPI getInstance() {
            if(service == null){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(SpotifyArtistAPI.class);
            }
            return service;
        }
    }

}



package com.example.aaly.spotifind.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("artists")
    @Expose
    private Artists artists;

    /**
     * 
     * @return
     *     The artists
     */
    public Artists getArtists() {
        return artists;
    }

    /**
     * 
     * @param artists
     *     The artists
     */
    public void setArtists(Artists artists) {
        this.artists = artists;
    }

}


package com.example.aaly.spotifind.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExternalUrls implements Serializable{

    @SerializedName("spotify")
    @Expose
    private String spotify;

    /**
     * 
     * @return
     *     The spotify
     */
    public String getSpotify() {
        return spotify;
    }

    /**
     * 
     * @param spotify
     *     The spotify
     */
    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

}

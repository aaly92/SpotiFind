package com.example.aaly.spotifind;


import java.io.Serializable;

public class Artist implements Serializable {

    private String id;
    private String name;
//    private String imgUrl;

    public Artist(String id, String name) {
        this.id = id;
        this.name = name;
//        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public String getImgUrl() {
//        return imgUrl;
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
}

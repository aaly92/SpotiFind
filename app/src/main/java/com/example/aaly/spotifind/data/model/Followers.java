
package com.example.aaly.spotifind.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Followers implements Serializable {

    @SerializedName("href")
    @Expose
    private Object href;
    @SerializedName("total")
    @Expose
    private int total;

    /**
     * 
     * @return
     *     The href
     */
    public Object getHref() {
        return href;
    }

    /**
     * 
     * @param href
     *     The href
     */
    public void setHref(Object href) {
        this.href = href;
    }

    /**
     * 
     * @return
     *     The total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(int total) {
        this.total = total;
    }

}

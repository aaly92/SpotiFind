package com.example.aaly.spotifind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArtistAdapter extends ArrayAdapter<Artist> {

    public ArtistAdapter(Context context, ArrayList<Artist> values){
        super(context, R.layout.artist_row_layout, values);
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent ) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.artist_row_layout, parent, false);

        String artist = getItem(position).getName();

        TextView textView = (TextView) view.findViewById(R.id.artistRowView);

        textView.setText(artist);
        return view;
    }
}
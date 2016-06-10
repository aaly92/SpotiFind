package com.example.aaly.spotifind;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aaly.spotifind.data.model.Item;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private List<Item> artistList;

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        public TextView artistNameView;

        public ArtistViewHolder(View view) {
            super(view);
            artistNameView = (TextView) view.findViewById((R.id.artistNameView));
        }
    }

    public void setArtistList(List<Item> artistList) {
        this.artistList = artistList;
    }

    public ArtistAdapter(List<Item> artistList) {
        this.artistList = artistList;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        Item item = artistList.get(position);
        holder.artistNameView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_row_layout, parent, false);
        return new ArtistViewHolder(itemView);
    }
}
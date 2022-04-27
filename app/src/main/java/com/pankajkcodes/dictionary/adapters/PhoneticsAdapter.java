package com.pankajkcodes.dictionary.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankajkcodes.dictionary.R;
import com.pankajkcodes.dictionary.models.Phonetics;

import java.io.IOException;
import java.util.List;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticsAdapter.PhoneticViewHolder> {

    private final Context context;
    private final List<Phonetics> phoneticsList;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoneticViewHolder(LayoutInflater.from(context).
                inflate(R.layout.phonetic_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {
        holder.phoneticTextView.setText(phoneticsList.get(position).getText());
        holder.phoneticImgButton.setOnClickListener(view -> {

            MediaPlayer player = new MediaPlayer();
            try {
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource("https:" + phoneticsList.get(position).getAudio());
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();

            }

        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }

    static class PhoneticViewHolder extends RecyclerView.ViewHolder {
        public TextView phoneticTextView;
        public ImageButton phoneticImgButton;

        public PhoneticViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneticTextView = itemView.findViewById(R.id.textView_phonetic_item);
            phoneticImgButton = itemView.findViewById(R.id.imageButton_phonetic);

        }
    }
}
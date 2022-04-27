package com.pankajkcodes.dictionary.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pankajkcodes.dictionary.R;
import com.pankajkcodes.dictionary.models.Meanings;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder> {

    private final Context context;
    private final List<Meanings> meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningViewHolder(LayoutInflater.from(context).
                inflate(R.layout.meaning_list_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {

        holder.textViewPartOfSpeech.setText("Part of Speech : " + meaningsList.get(position).getPartOfSpeech());

        holder.recyclerViewMeaning.setHasFixedSize(true);
        holder.recyclerViewMeaning.setLayoutManager(new GridLayoutManager(context, 1));
        DefinitionAdapter definitionAdapter = new DefinitionAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recyclerViewMeaning.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }

    static class MeaningViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewPartOfSpeech;
        public RecyclerView recyclerViewMeaning;

        public MeaningViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPartOfSpeech = itemView.findViewById(R.id.textView_partofspeech);
            recyclerViewMeaning = itemView.findViewById(R.id.recylerView_meaning_item);
        }
    }
}

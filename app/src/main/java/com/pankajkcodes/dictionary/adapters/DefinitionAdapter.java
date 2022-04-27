package com.pankajkcodes.dictionary.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pankajkcodes.dictionary.R;
import com.pankajkcodes.dictionary.models.Definitions;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder> {
    Context context;
    List<Definitions> definitionsList;

    public DefinitionAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DefinitionViewHolder(LayoutInflater.from(context).
                inflate(R.layout.definitions_list_item, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        holder.definition.setText("Definition : " + definitionsList.get(position).getDefinition());
        holder.example.setText("Example : " + definitionsList.get(position).getExample());

        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.synonyms.setText(synonyms);
        holder.antonyms.setText(antonyms);

        holder.synonyms.setSelected(true);
        holder.antonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }

    static class DefinitionViewHolder extends RecyclerView.ViewHolder {
        public TextView definition, example, synonyms, antonyms;

        public DefinitionViewHolder(@NonNull View itemView) {
            super(itemView);

            definition = itemView.findViewById(R.id.textView_definitions);
            example = itemView.findViewById(R.id.textView_example);
            synonyms = itemView.findViewById(R.id.textView_synonyms);
            antonyms = itemView.findViewById(R.id.textView_antonyms);


        }
    }

}

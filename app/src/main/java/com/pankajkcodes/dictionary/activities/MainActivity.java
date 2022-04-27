package com.pankajkcodes.dictionary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.pankajkcodes.dictionary.R;
import com.pankajkcodes.dictionary.adapters.MeaningAdapter;
import com.pankajkcodes.dictionary.adapters.PhoneticsAdapter;
import com.pankajkcodes.dictionary.api.ApiRequestManager;
import com.pankajkcodes.dictionary.api.OnFetchDataListener;
import com.pankajkcodes.dictionary.models.WordApiResponse;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    TextView textViewWord;
    RecyclerView recyclerViewPhonetics, recyclerViewMeanings;
    ProgressDialog progressDialog;
    PhoneticsAdapter phoneticsAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchView = findViewById(R.id.search_view);
        textViewWord = findViewById(R.id.textView_word);
        recyclerViewPhonetics = findViewById(R.id.recycler_phonetics);
        recyclerViewMeanings = findViewById(R.id.recycler_meanings);

        progressDialog = new ProgressDialog(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setMessage("loading...");
                progressDialog.show();
                ApiRequestManager manager = new ApiRequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(WordApiResponse apiResponse, String message) {
            progressDialog.dismiss();

            if (apiResponse == null) {
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }


            if (apiResponse != null) {
                Log.d("TAG",apiResponse.getMeanings().toString());
                showData(apiResponse);
            }

        }

        @Override
        public void onFetchError(String message) {

        }
    };

    @SuppressLint("SetTextI18n")
    private void showData(WordApiResponse apiResponse) {

        textViewWord.setText("Word : " + apiResponse.getWord());
        recyclerViewPhonetics.setHasFixedSize(true);
        recyclerViewPhonetics.setLayoutManager(new GridLayoutManager(this, 1));
        phoneticsAdapter = new PhoneticsAdapter(this, apiResponse.getPhonetics());
        recyclerViewPhonetics.setAdapter(phoneticsAdapter);

        recyclerViewMeanings.setHasFixedSize(true);
        recyclerViewMeanings.setLayoutManager(new GridLayoutManager(this, 1));
        meaningAdapter = new MeaningAdapter(this, apiResponse.getMeanings());
        recyclerViewMeanings.setAdapter(meaningAdapter);


    }
}
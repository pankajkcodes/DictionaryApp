package com.pankajkcodes.dictionary.api;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.pankajkcodes.dictionary.models.WordApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ApiRequestManager {
    Context context;

    public ApiRequestManager(Context context) {
        this.context = context;
    }

    String BASE_ULR = "https://api.dictionaryapi.dev/api/v2/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_ULR)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public interface CallDictionary {
        @GET("entries/en/{word}")
        Call<List<WordApiResponse>> callMeanings(
                @Path("word") String word
        );
    }

    public void getWordMeaning(OnFetchDataListener listener, String word) {
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<WordApiResponse>> call = callDictionary.callMeanings(word);
        try {

            call.enqueue(new Callback<List<WordApiResponse>>() {
                @Override
                public void onResponse(@NonNull Call<List<WordApiResponse>> call, Response<List<WordApiResponse>> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Data Not Found !!!", Toast.LENGTH_SHORT).show();
                    }
                    if (response.body() != null) {
                        listener.onFetchData(response.body().get(0), response.message());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<WordApiResponse>> call, Throwable t) {
                    listener.onFetchError("Request Failed");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}

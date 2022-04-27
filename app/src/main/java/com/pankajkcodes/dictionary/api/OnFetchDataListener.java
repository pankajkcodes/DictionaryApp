package com.pankajkcodes.dictionary.api;

import com.pankajkcodes.dictionary.models.WordApiResponse;

public interface OnFetchDataListener {
    void onFetchData(WordApiResponse apiResponse, String message);

    void onFetchError(String message);
}

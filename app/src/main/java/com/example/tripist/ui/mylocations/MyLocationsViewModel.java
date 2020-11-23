package com.example.tripist.ui.mylocations;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyLocationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyLocationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my locations fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
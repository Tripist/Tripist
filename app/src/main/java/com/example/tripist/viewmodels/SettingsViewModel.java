package com.example.tripist.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel  extends ViewModel {
    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Settings");
    }

    public LiveData<String> getText() {
        return mText;
    }
}





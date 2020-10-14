package com.app.launcherandroid.viewmodel;

import android.app.Application;

import android.content.Context;
import android.os.Handler;

import com.example.appinfosdk2.AppInformation;
import com.example.appinfosdk2.ModalAppInfo;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class AppViewModel extends AndroidViewModel {

    //this is the data
    private MutableLiveData<List<ModalAppInfo>> appInfo;

    private List<ModalAppInfo> appList;

    private static Context context ;

    public AppViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    //we will call this method to get the data
    public LiveData<List<ModalAppInfo>> getLiveInfoObj() {
        //if the list is null
        if (appInfo == null)
            appInfo = new MutableLiveData<>();
        //we will load it
        loadAppInfo();
        return appInfo;
    }

    //This method is to get the app data from SDK
    private void loadAppInfo() {
        appList = AppInformation.getInstance().getInstalledApps(context);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                appInfo.setValue(appList);
            }
        }, 5000);

    }

}

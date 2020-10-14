package com.example.appinfosdk2;



import java.util.Comparator;

public class AppInfoComparator implements Comparator<ModalAppInfo> {
    @Override
    public int compare(ModalAppInfo obj1, ModalAppInfo obj2) {
        return obj1.getAppName().compareTo(obj2.getAppName());
    }
}

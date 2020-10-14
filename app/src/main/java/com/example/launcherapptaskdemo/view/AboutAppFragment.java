package com.example.launcherapptaskdemo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.appinfosdk2.ModalAppInfo;

import com.app.launcherandroid.viewmodel.AppViewModel;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.appinfosdk2.ModalAppInfo;
import com.example.launcherapptaskdemo.R;

import java.util.ArrayList;
import java.util.List;

public class AboutAppFragment extends Fragment implements RecyclerOnRowItemClickListener {

    public static final String TAG = "AboutAppFragment";

    private AppInfoAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private List<ModalAppInfo> listAllApp;
    private List<ModalAppInfo> listAllAppMain;
    private Context context;

    private RecyclerView rvAppInfoList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_country, container, false);
        init(view);
        loadAppData();
        return view;
    }

    //Initialize the fragment view
    private void init(View view){

        rvAppInfoList = view.findViewById(R.id.recyclerView);
        context=getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvAppInfoList.setLayoutManager(linearLayoutManager);
        //List for the app details
        listAllApp = new ArrayList<ModalAppInfo>();
        listAllAppMain = new ArrayList<ModalAppInfo>();

        // initialize Adapter
        adapter = new AppInfoAdapter(listAllApp, this);
        rvAppInfoList.setAdapter(adapter);



        // Apply Swipe Refresh
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAppData();
            }
        });

        // Apply SearchView
        SearchView searchView = view.findViewById(R.id.searchBar);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {

                listAllApp.clear();
                for (int i=0; i<listAllAppMain.size(); i++){
                    if (listAllAppMain.get(i).getAppName().toLowerCase().contains(newText.toLowerCase())){
                        listAllApp.add(listAllAppMain.get(i));
                    }
                }
                adapter.updateList( listAllApp);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
    //Load the app details from view model
    private void loadAppData(){
        swipeRefresh.setRefreshing(true);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        AppViewModel model = viewModelProvider.get(AppViewModel.class);

        model.getLiveInfoObj().observeForever( new Observer<List
                <ModalAppInfo>
                >() {
            @Override
            public void onChanged(@Nullable List
                    <ModalAppInfo> appList) {
                if(appList != null){
                    //update list of adapter
                    adapter.updateList( appList);
                    // list for search result
                    listAllAppMain.clear();
                    listAllAppMain.addAll(appList);
                   // adapter.notifyDataSetChanged();
                }
            }
        });
        swipeRefresh.setRefreshing(false);
    }

//Launch the app form list item click
    @Override
    public void onRowItemClick(View view, int position) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(listAllApp.get(position).getPackages());
        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(context, "Not able to open the app" , Toast.LENGTH_SHORT).show();
        }
    }
}


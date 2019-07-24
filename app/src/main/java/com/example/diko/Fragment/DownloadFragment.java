package com.example.diko.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.diko.R;

import static com.example.diko.Activity.HomeActivity.toolbar;
import static com.example.diko.Activity.HomeActivity.toolbarpicture;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    SearchView searchView;
    public DownloadFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_download, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchView=view.findViewById(R.id.searchView);
        toolbar(getActivity(),"Downloaded Document");
        toolbarpicture(getActivity(), View.GONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }
}

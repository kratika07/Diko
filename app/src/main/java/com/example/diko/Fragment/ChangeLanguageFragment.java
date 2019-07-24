package com.example.diko.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diko.R;

import static com.example.diko.Activity.HomeActivity.toolbar;
import static com.example.diko.Activity.HomeActivity.toolbarpicture;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangeLanguageFragment extends Fragment {


    public ChangeLanguageFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.change_language, container, false);
        toolbar(getActivity(),"Change Language");
        toolbarpicture(getActivity(), View.GONE);
    return view;


    }

}


package com.example.diko.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.R;

import static com.example.diko.Activity.HomeActivity.toolbar;
import static com.example.diko.Activity.HomeActivity.toolbarpicture;
import static com.example.diko.Fragment.HomeFragment.PARENTID;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    public NotificationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_notification, container, false);


        toolbar(getActivity(),"Notification");
        toolbarpicture(getActivity(), View.GONE);

    return view;
    }




}

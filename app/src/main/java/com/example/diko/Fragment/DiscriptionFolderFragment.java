package com.example.diko.Fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.diko.Adapter.DiscriptionAdapter;
import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.Interface.APIService;
import com.example.diko.POJO.DATum;
import com.example.diko.POJO.GetdataPojo.GetData;
import com.example.diko.R;
import com.example.diko.Utils.APIUrl;
import com.example.diko.Utils.util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscriptionFolderFragment extends Fragment {

    String desired_string;
    TextView name,updatedate,createddate,createdname;
    public DiscriptionFolderFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view=inflater.inflate(R.layout.fragment_discription_folder, container, false);
        name=view.findViewById(R.id.name);
        createddate=view.findViewById(R.id.createddate);
        updatedate=view.findViewById(R.id.dupdatedate);
        createdname=view.findViewById(R.id.createdname);
        Bundle arguments = getArguments();
        desired_string = arguments.getString("list");
        getdataDiscription();

    return view;
    }
    public void getdataDiscription()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        String user_token= SharedPrefManager.getInstance(getActivity()).getUser().getUSER_TOKEN();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService scalarService = retrofit.create(APIService.class);
        Call<GetData> stringCall = scalarService.getRootflderData(user_token,desired_string);
        stringCall.enqueue(new Callback<GetData>() {
            @Override
            public void onResponse(Call<GetData> call, Response<GetData> response) {

                if (response.code() == 200) {
                    progressDialog.dismiss();
                    GetData jsonResponse = response.body();
                    name.setText(jsonResponse.getITEMNAME());
                    updatedate.setText(jsonResponse.getMODIFIEDDATE());
                    createddate.setText(jsonResponse.getCREATEDDATE());
                    createdname.setText(jsonResponse.getCREATEDBYNAME());
                }
                else
                {
                    util.Log(getActivity(),"error list",response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<GetData> call, Throwable t) {
                progressDialog.dismiss();
                util.Log(getActivity(),"responseerr","check it"+t.toString());
            }
        });

    }
}

package com.example.diko.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.example.diko.Adapter.HomeAdapter;

import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.Interface.APIService;
import com.example.diko.POJO.DATum;
import com.example.diko.POJO.Rootfolder;
import com.example.diko.R;
import com.example.diko.Utils.APIUrl;
import com.example.diko.Utils.util;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import static com.example.diko.Activity.HomeActivity.toolbar;
import static com.example.diko.Activity.HomeActivity.toolbarpicture;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private HomeAdapter adapter;
    List<DATum> dataarraylist;

    ImageView searchicon;
    Toolbar toolbar;
    String parent_id;
    @BindView(R.id.searchView)
    SearchView searchView;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String PARENTID = "parentkey";
    SharedPreferences sharedpreferences;
    ProgressBar progressBar;
    private int start=1;
    private int limit=10;
    private  boolean isLoading=true;
    private  int pastVisibleitem,visibleItemCount,totalItemCount,previous_total=0;
    private    int view_threshold=10;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ButterKnife.bind(this,view);
        toolbar(getActivity(),"Home");
        toolbarpicture(getActivity(), View.VISIBLE);
        progressBar=view.findViewById(R.id.progressbar);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
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
        recyclerView.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        progressBar.setVisibility(View.VISIBLE);
        serviceretrofit();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount=layoutManager.getChildCount();
                totalItemCount=layoutManager.getItemCount();
                pastVisibleitem=((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

if (dy>0)
{
    if (isLoading)
    {
        if (totalItemCount>previous_total)
        {
            isLoading=false;
            previous_total=totalItemCount;
        }
    }

    if (!isLoading&&(totalItemCount-visibleItemCount)<=(pastVisibleitem+view_threshold))
    {

performpagination();
isLoading=true;
//start=start+10;
        start++;
    }
}


            }
        });

        return view;
    }
    public void serviceretrofit() {
        String usertoken=SharedPrefManager.getInstance(getActivity()).getUser().getUSER_TOKEN();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        APIService scalarService = retrofit.create(APIService.class);
        Call<String> stringCall = scalarService.getStringResponse(usertoken);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    parent_id = response.body();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(PARENTID, parent_id);
                    editor.commit();
                    getListItems();
                }
                else
                {
                    util.Log(getActivity(),"parent id","check it");
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
      public void getListItems()
         {

    final String user_token=SharedPrefManager.getInstance(getActivity()).getUser().getUSER_TOKEN();
             String parentid = sharedpreferences.getString(PARENTID, "");
           String parent=parentid.replaceAll("^\"|\"$", "");
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIService scalarService = retrofit.create(APIService.class);
    Call<Rootfolder> stringCall = scalarService.getRootFolderList(user_token,parent,start,limit);
    stringCall.enqueue(new Callback<Rootfolder>() {
        @Override
        public void onResponse(Call<Rootfolder> call, Response<Rootfolder> response) {

            if (response.code() == 200) {

                Rootfolder jsonResponse = response.body();
                dataarraylist = new ArrayList<DATum>(jsonResponse.getDATA());
                adapter = new HomeAdapter((ArrayList<DATum>) dataarraylist,getActivity());
                recyclerView.setAdapter(adapter);
               progressBar.setVisibility(View.GONE);
            }
            else
            {
                util.Log(getActivity(),"error list",response.code()+"");
                util.Log(getActivity(), "error check",response.errorBody().toString());
            }
        }

        @Override
        public void onFailure(Call<Rootfolder> call, Throwable t) {
            util.Log(getActivity(),"responseerr","check it"+t.toString());
        }
    });
}




private void performpagination()
{
    progressBar.setVisibility(View.VISIBLE);
    final String user_token=SharedPrefManager.getInstance(getActivity()).getUser().getUSER_TOKEN();
    String parentid = sharedpreferences.getString(PARENTID, "");
    String parent=parentid.replaceAll("^\"|\"$", "");
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    APIService scalarService = retrofit.create(APIService.class);
    Call<Rootfolder> stringCall = scalarService.getRootFolderList(user_token,parent,start,limit);
    stringCall.enqueue(new Callback<Rootfolder>() {
        @Override
        public void onResponse(Call<Rootfolder> call, Response<Rootfolder> response) {

            if (response.code() == 200) {

                Rootfolder jsonResponse = response.body();
                dataarraylist = new ArrayList<DATum>(jsonResponse.getDATA());
                adapter = new HomeAdapter((ArrayList<DATum>) dataarraylist,getActivity());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            Toast.makeText(getActivity(),"total start"+start,Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(getActivity(),"No More Data",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Rootfolder> call, Throwable t) {
            util.Log(getActivity(),"responseerr","check it"+t.toString());
        }
    });
}

}

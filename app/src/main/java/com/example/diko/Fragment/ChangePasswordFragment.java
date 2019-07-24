package com.example.diko.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diko.Activity.LoginActivity;
import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.Interface.APIService;
import com.example.diko.R;
import com.example.diko.Utils.APIUrl;
import com.example.diko.Utils.util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.diko.Activity.HomeActivity.toolbar;
import static com.example.diko.Activity.HomeActivity.toolbarpicture;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {
    EditText OldPassword,Newpassword,confirmpassword;
    Button submit;
    String newpassword;
    String oldpassword;
    String appid,usertoken;


    public ChangePasswordFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.change_password, container, false);
        OldPassword=view.findViewById(R.id.oldpassword);
        Newpassword=view.findViewById(R.id.newpassword);

        toolbar(getActivity(),"Change Password");
        toolbarpicture(getActivity(), View.GONE);
        submit=view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        changepasswordapi();
    }
});

        return view;
    }
    public void changepasswordapi()
    {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
         usertoken=SharedPrefManager.getInstance(getActivity()).getUser().getUSER_TOKEN();
         appid=SharedPrefManager.getInstance(getActivity()).getUser().getAPP_ID();
         newpassword=Newpassword.getText().toString();
         oldpassword=OldPassword.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        APIService scalarService = retrofit.create(APIService.class);
        Call<String> stringCall = scalarService.getChangePassword(appid,usertoken,newpassword,oldpassword);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressDialog.dismiss();
                if (response.code()==200)
                {
                    String res=response.body().toString();
                    Toast.makeText(getActivity(),"Password is changed",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.e("response code",""+response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("fail",t.toString());
            }
        });
    }

}

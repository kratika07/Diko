package com.example.diko.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class ForgotPassworForm extends AppCompatActivity {
    Button submit;
    EditText Newpassword,OldPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_form);
        submit=findViewById(R.id.submit);
        Newpassword=findViewById(R.id.newpassword);
        OldPassword=findViewById(R.id.oldpassword);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       //  resetpassword();



            }
        });
    }

public void resetpassword()
{
    final ProgressDialog progressDialog = new ProgressDialog(ForgotPassworForm.this);
    progressDialog.setMessage("Loading...");
    progressDialog.show();
    String appisd="INCnMPRnAPP015";
    String newpassword=Newpassword.getText().toString();
    String validation_code="11111";
    util.Log(getApplicationContext(),"user",appisd);
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIUrl.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();
    APIService scalarService = retrofit.create(APIService.class);
    Call<String> stringCall = scalarService.getResetPassword(appisd,validation_code,newpassword);
    stringCall.enqueue(new Callback<String>() {
        @Override
        public void onResponse(Call<String> call, Response<String> response) {

            if (response.isSuccessful())
            {
                String getresponse = response.body();
                Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
            }
            else {
                util.Log(getApplicationContext(),"404","not correct");
            }
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            progressDialog.dismiss();
            util.Log(getApplicationContext(),"fail",t.toString());
        }
    });
}

    }














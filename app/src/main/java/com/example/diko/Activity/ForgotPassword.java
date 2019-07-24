package com.example.diko.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class ForgotPassword extends AppCompatActivity {

Button submit;
EditText userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        submit=findViewById(R.id.submit);
        userid=findViewById(R.id.userid);

        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        serviceretrofit();

           }
});

    }

    public void serviceretrofit() {

        final ProgressDialog progressDialog = new ProgressDialog(ForgotPassword.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String user_id=userid.getText().toString();
        util.Log(getApplicationContext(),"user",user_id);

        String appisd="INCnMPRnAPP015";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        APIService scalarService = retrofit.create(APIService.class);
        Call<String> stringCall = scalarService.getForgotResponse(appisd,user_id);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

if (response.isSuccessful())
{
    String getresponse = response.body();

   startActivity(new Intent(getApplicationContext(),ForgotPassworForm.class));
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

package com.example.diko.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diko.Class.Result;
import com.example.diko.Class.User;
import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.Interface.APIService;
import com.example.diko.R;
import com.example.diko.Utils.APIUrl;
import com.example.diko.Utils.util;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button login;
    String user_id;
    String password;
    EditText editTextEmail, editTextPassword;
    CheckBox checkBox;
TextView forgotpassword;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USERNAME = "nameKey";
    public static final String PASSWORD = "phoneKey";
    public static final String TRUEVALUE = "truekey";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        forgotpassword=findViewById(R.id.forgot);
        checkBox = findViewById(R.id.checkbox);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignIn();
            }
        });
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        forgotpassword=findViewById(R.id.forgot);
        forgotpassword.setOnClickListener(new View.OnClickListener() {
        @Override
         public void onClick(View v) {
          startActivity(new Intent(LoginActivity.this,ForgotPassword.class));
              }
             });



        if (SharedPrefManager.getInstance(LoginActivity.this).isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        String username = sharedpreferences.getString(USERNAME, "");
        String password = sharedpreferences.getString(PASSWORD, "");
        boolean check = sharedpreferences.getBoolean(TRUEVALUE, false);

        if (check) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            editTextEmail.setText(username);
            editTextPassword.setText(password);
        } else {
            editTextEmail.setText("");
            editTextPassword.setText("");
        }

    }


    private void userSignIn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();
        final boolean value = true;
        user_id = editTextEmail.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();
        final String app_id = "INCnMPRnAPP015";
        final String locale = "us-EN";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<User> call = service.UserLogin(app_id, user_id, password, locale);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    Log.e("sucess", "working");
                    String user_token = response.body().getUSER_TOKEN();
                    String user_id = response.body().getUSER_ID();
                    String fullname = response.body().getFULL_NAME();
                    String fname = response.body().getFIRST_NAME();
                    String lname = response.body().getLAST_NAME();
                    String profile_pic_mini = response.body().getPROFILE_PIC_MINI();
                    String appId = response.body().getAPP_ID();
                    String appOption = response.body().getAPP_OPTION();
                    String siteId = response.body().getSITE_ID();
                    String email = response.body().getEMAIL();
                    String groupname = response.body().getGROUPS_NAME();

                    User user = new User(user_token, user_id, fullname, fname, lname, email, groupname, profile_pic_mini, appId, appOption, siteId);
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    if (checkBox.isChecked()) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(USERNAME, user_id);
                        editor.putString(PASSWORD, password);
                        editor.putBoolean(TRUEVALUE, value);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(USERNAME, "");
                        editor.putString(PASSWORD, "");
                        editor.putBoolean(TRUEVALUE, false);
                        editor.commit();
                    }
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), R.string.unsuccessMsg, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

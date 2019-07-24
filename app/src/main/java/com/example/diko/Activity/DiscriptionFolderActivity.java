package com.example.diko.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.Interface.APIService;
import com.example.diko.POJO.GetdataPojo.GetData;
import com.example.diko.R;
import com.example.diko.Utils.APIUrl;
import com.example.diko.Utils.util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscriptionFolderActivity extends AppCompatActivity {

    String item_id;
    TextView name,updatedate,createddate,createdname;
    CardView cardView1,cardView2,cardView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discription_folder);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getResources().getString(R.string.documentview));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        cardView1=findViewById(R.id.layout1);
        cardView2=findViewById(R.id.layout2);
        cardView3=findViewById(R.id.layout3);
        name=findViewById(R.id.name);
        createddate=findViewById(R.id.createddate);
        updatedate=findViewById(R.id.dupdatedate);
        createdname=findViewById(R.id.createdname);

         Intent intent = getIntent();
         item_id = intent.getStringExtra("list");
         getdataDiscription();

         cardView2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 showDialog2(DiscriptionFolderActivity.this);
             }
         });


        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog3(DiscriptionFolderActivity.this);
            }
        });

cardView1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showDialog1(DiscriptionFolderActivity.this);
    }
});

    }


    public void showDialog3(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.comment_section_dialoge);
        ImageView close=dialog.findViewById(R.id.imageView_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    public void showDialog2(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.refrence_link_dialoge);
        ImageView close=dialog.findViewById(R.id.imageView_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showDialog1(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.version_list_dialoge);
        ImageView  close=dialog.findViewById(R.id.imageView_close);
        close.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              dialog.dismiss();
          }
      });
        dialog.show();
    }


    public void getdataDiscription()
    {
        util.Log(getApplicationContext(),"checkstgggggggring",item_id);
        String user_token= SharedPrefManager.getInstance(getApplicationContext()).getUser().getUSER_TOKEN();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService scalarService = retrofit.create(APIService.class);
        Call<GetData> stringCall = scalarService.getRootflderData(user_token,item_id);
        stringCall.enqueue(new Callback<GetData>() {
            @Override
            public void onResponse(Call<GetData> call, Response<GetData> response) {

                if (response.code() == 200) {
                    util.Log(getApplicationContext(),"getrootfolder",response.toString());
                    GetData jsonResponse = response.body();
                    name.setText(jsonResponse.getITEMNAME());
                    updatedate.setText(jsonResponse.getMODIFIEDDATE());
                    createddate.setText(jsonResponse.getCREATEDDATE());
                    createdname.setText(jsonResponse.getCREATEDBYNAME());
                }
                else
                {
                    util.Log(getApplicationContext(),"error list",response.code()+"");
                }
            }
            @Override
            public void onFailure(Call<GetData> call, Throwable t) {
                util.Log(getApplicationContext(),"responseerr","check it"+t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_adapter_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sharedocument:
                //handle menu1 click

                break;
            case R.id.printdocument:
                //handle menu2 click

                break;
            case R.id.downloaddocumentaspdf:
                //handle menu3 click


                break;
            case R.id.downloadoriginalformate:
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}

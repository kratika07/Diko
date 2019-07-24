package com.example.diko.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diko.Fragment.BrowseFolder;
import com.example.diko.Fragment.ChangeLanguageFragment;
import com.example.diko.Fragment.ChangePasswordFragment;
import com.example.diko.Fragment.DownloadFragment;
import com.example.diko.Fragment.HomeFragment;
import com.example.diko.Fragment.NotificationFragment;
import com.example.diko.Fragment.ProfileFragment;
import com.example.diko.Fragment.SettingsFragment;
import com.example.diko.Fragment.ShareFragment;
import com.example.diko.Fragment.UploadFragment;
import com.example.diko.Helper.BottomNavigationBehavior;
import com.example.diko.Helper.SharedPrefManager;
import com.example.diko.R;
import com.example.diko.Utils.util;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static TextView title;
    Fragment fragment;
    static ImageView profilepicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        title=findViewById(R.id.toolbar_title);

        profilepicture=findViewById(R.id.toolBarPicture);
        util.isInternetAvailable(HomeActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        //String profileimage= SharedPrefManager.getInstance(getApplicationContext()).getUser().getPROFILE_PIC_MINI();

          


        profilepicture.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        fragment=new ProfileFragment();

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
    }
});



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        loadFragment(new HomeFragment());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.bind(this);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
           fragment=new HomeFragment();
        } else if (id == R.id.browsefolder) {
            fragment=new BrowseFolder();
        } else if (id == R.id.changepassword) {
            fragment=new ChangePasswordFragment();
        } else if (id == R.id.changelanguage) {
            fragment=new ChangeLanguageFragment();
        } else if (id == R.id.notification) {
            fragment=new NotificationFragment();
        } else if (id == R.id.settings) {
            fragment=new SettingsFragment();
        }
        else  if (id==R.id.logout)
        {
            showCustomDialog();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //Fragment fragment;
             fragment=null;
            switch (item.getItemId()) {
                case R.id.ihome:
                    fragment = new HomeFragment();

                    loadFragment(fragment);
                    return true;
                case R.id.share:
                    fragment = new ShareFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.download:
                  fragment = new DownloadFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.upload:
                   fragment = new UploadFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main, fragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }



    public static void  toolbar(Context context,String titlestring)
    {
        title.setText(titlestring);

    }

    public static void  toolbarpicture(Context context, int view )
    {
       profilepicture.setVisibility(view);

    }

    private void showCustomDialog()
    {
        final Dialog dialog = new Dialog(HomeActivity.this);
        dialog.setContentView(R.layout.logout_alert_dialogbox);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setTitle("Custom Dialog");
        Button yes = (Button) dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        dialog.show();
        Button declineButton = (Button) dialog.findViewById(R.id.no);
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    private void logout() {
        SharedPrefManager.getInstance(this).logout();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }


}



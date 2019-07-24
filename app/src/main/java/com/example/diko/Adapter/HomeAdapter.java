package com.example.diko.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.PopupMenu;

import android.widget.TextView;

import com.example.diko.Activity.DiscriptionFolderActivity;
import com.example.diko.POJO.DATum;
import com.example.diko.R;

import java.util.ArrayList;



import static com.example.diko.R.menu.home_adapter_menu;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

private ArrayList<DATum> list;
private Context mCtx;
    DATum myList;
    public HomeAdapter(ArrayList<DATum> rootfolderArrayList,Context mCtx) {
        this.list = rootfolderArrayList;
        this.mCtx=mCtx;
    }
    @Override
       public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_adapter_layout, parent, false);
        return new ViewHolder(v);
        }
        @Override
        public void onBindViewHolder(final HomeAdapter.ViewHolder holder, final int position) {
        myList = list.get(position);
        holder.textViewHead.setText(myList.getITEMNAME());
        holder.Documentuodate.setText(myList.getCREATEDBYNAME());
        holder.Actualdate.setText(myList.getCREATEDDATE());
        holder.user.setText(myList.getMODIFIEDBYNAME());
        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        PopupMenu popup = new PopupMenu(mCtx, holder.buttonViewOption);
        popup.inflate(home_adapter_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
@Override
public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.sharedocument:
        break;
        case R.id.printdocument:
        break;
        case R.id.downloaddocumentaspdf:
        break;
        case R.id.downloadoriginalformate:
            break;
        }
        return false;
        }
        });

        popup.show();
        }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(mCtx, DiscriptionFolderActivity.class);
             intent.putExtra("list",myList.getITEMID());
             mCtx.startActivity(intent);
            }
        });


        }


@Override
public int getItemCount() {
        return list.size();

        }

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewHead;
    public TextView textViewDesc;
    public TextView buttonViewOption;
    public TextView Documentuodate;
    public TextView Actualdate;
    public TextView user;
    public ViewHolder(View itemView) {
        super(itemView);
        textViewHead = (TextView) itemView.findViewById(R.id.title);
        textViewDesc = (TextView) itemView.findViewById(R.id.subtitle);
        buttonViewOption = (TextView) itemView.findViewById(R.id.dots);
        Documentuodate=itemView.findViewById(R.id.document);
        Actualdate=itemView.findViewById(R.id.actualdate);
        user=itemView.findViewById(R.id.user);
    }
}
}

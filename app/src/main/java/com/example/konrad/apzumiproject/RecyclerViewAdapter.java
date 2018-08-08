package com.example.konrad.apzumiproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Konrad on 08.08.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context1;
    private List<APIResults> apiResultsList;
    int THRESHOLD = 16;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlelinerecyclerview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final APIResults singleApiResult = apiResultsList.get(position);

        // W przypadku gdy trafimy na element z Bitbucketa zmieniamy kolor tła elementu listy z RecyclerView na beżowy. W ocenie
        // tego warunku pomoże nam znienna typu prostego przypisana do każdego elementu pobranego poprzez API i zapisana w pamieci
        if (singleApiResult.isBitBucketResource){
            holder.lineralLay.setBackgroundResource(R.color.myOwnColorForBitbucketElement);
        }

        // Zabezpieczamy sie rowniez przed zbyt dluga nazwa uzytkownika
        holder.userName.setText((singleApiResult.userName.length() > THRESHOLD) ? singleApiResult.userName.substring(0, THRESHOLD) + ".." : singleApiResult.userName);


    }
    @Override
    public int getItemCount() {
        return apiResultsList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView repositoryName;
        public TextView userName;
        public LinearLayout lineralLay;

        public MyViewHolder(View itemView) {
            super(itemView);
            repositoryName = (TextView) itemView.findViewById(R.id.repositorySource);
            userName = (TextView) itemView.findViewById(R.id.userName);
            lineralLay = (LinearLayout) itemView.findViewById(R.id.singleLineLay);


            // dodaje odnosnik do tla w singleLine

        }


        private MenuInflater getMenuInflater() {
            return new MenuInflater(context1);
        }

    }
    public RecyclerViewAdapter(List<APIResults> apiResultsList, Context context){
        this.apiResultsList = apiResultsList;
        this.context1 = context;

    }
}

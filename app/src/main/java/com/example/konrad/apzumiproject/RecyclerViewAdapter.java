package com.example.konrad.apzumiproject;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.fahmisdk6.avatarview.AvatarView;

/**
 * Created by Konrad on 08.08.2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static  MediaPlayer mySound;
    private Context context1;
    private List<APIResults> apiResultsList;
    int THRESHOLD = 13;
    private int position;

    public static int positionForRecycler(int condition){
        return condition;
    }
    public int getPosition1() {
        return position;
    }

    public void setPosition1(int position) {
        this.position = position;
    }


    public int getPosition() {
        return position;
    }

    public int setPosition(int position) {
        this.position = position;
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlelinerecyclerview, parent, false);
                v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context1, GeneralMethods.position, Toast.LENGTH_LONG).show();

            }
        });
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final APIResults singleApiResult = apiResultsList.get(position);

        Picasso.with(context1).load(singleApiResult.avatarSource).into(holder.imageView);
        // W przypadku gdy trafimy na element z Bitbucketa zmieniamy kolor tła elementu listy z RecyclerView na beżowy. W ocenie
        // tego warunku pomoże nam znienna typu prostego przypisana do każdego elementu pobranego poprzez API i zapisana w pamieci
        if (singleApiResult.isBitBucketResource){
            holder.lineralLay.setBackgroundResource(R.color.myOwnColorForBitbucketElement);
        }else{
            holder.lineralLay.setBackgroundResource(R.color.white);
        }
        // Zabezpieczamy sie rowniez przed zbyt dluga nazwa uzytkownika
        holder.userName.setText((singleApiResult.userName.length() > THRESHOLD) ? singleApiResult.userName.substring(0, THRESHOLD) + ".." : singleApiResult.userName);
        holder.repositoryName.setText(singleApiResult.repositoryName);
        // Ustawiamy OnClicka po wyborze dowolnego elementu z listy
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySound = MediaPlayer.create(context1, R.raw.klikanie);
                mySound.start();
                GeneralMethods.position = setPosition(holder.getAdapterPosition());
                String toastMessageSelectedPositionNumber = String.valueOf(GeneralMethods.position);
                Toast.makeText(context1, "Position: "+toastMessageSelectedPositionNumber, Toast.LENGTH_LONG).show();

            }
        });

    }
    @Override
    public int getItemCount() {
        return apiResultsList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView repositoryName;
        public TextView userName;
        public LinearLayout lineralLay;
//        public AvatarView avatarView;
        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            repositoryName = (TextView) itemView.findViewById(R.id.repositorySource);
            userName = (TextView) itemView.findViewById(R.id.userName);
            lineralLay = (LinearLayout) itemView.findViewById(R.id.singleLineLay);
//            avatarView = (AvatarView) itemView.findViewById(R.id.avatar);
            imageView = (ImageView) itemView.findViewById(R.id.avatarr);
        }

        @Override
        public void onClick(View v) {

        }
    }
    public RecyclerViewAdapter(List<APIResults> apiResultsList, Context context){
        this.apiResultsList = apiResultsList;
        this.context1 = context;

    }
}

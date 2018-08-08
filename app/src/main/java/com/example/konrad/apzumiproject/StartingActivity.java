package com.example.konrad.apzumiproject;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        ButterKnife.bind(this);
        Button fab = (Button) findViewById(R.id.startbtn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask<Object, Object, List<APIResults>> execute = new APIdataSearcher().execute();
                final List<APIResults> mojaLista;
                try {
                    mojaLista = execute.get();
                    final List<String> listaaaa = new ArrayList<>();
                    final List<String> listForAdapter = new ArrayList<>();
                    final List<Double> exchangeRateList = new ArrayList<>();
//                for (Currencies mojItem : mojaLista) {
//                    listaaaa.add(mojItem.getCode() + " : " + mojItem.getValue() + " PLN");
//                    listForAdapter.add(mojItem.getCode());
//                    exchangeRateList.add(mojItem.getValue());
//                    NaszeMetody.exchangeRates = exchangeRateList;
//                    NaszeMetody.menuCurrencies = listForAdapter;
//                    zapiszDoPamieciListeWalut(listForAdapter);
//                }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}




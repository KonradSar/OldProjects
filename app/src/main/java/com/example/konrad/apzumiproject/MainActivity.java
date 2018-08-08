package com.example.konrad.apzumiproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerForAPIData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(GeneralMethods.apiResults, MainActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);

        if(isNetworkAvailable()){
            adjustDataForAdapter();
        } else {

            Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, StartingActivity.class);
            startActivity(intent);
    }
    }

    public void adjustDataForAdapter() {
        RecyclerViewAdapter  r4 = new RecyclerViewAdapter(GeneralMethods.apiResults, MainActivity.this);
        recyclerView.setAdapter(r4);

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}




// dodaj zabezpieczenie z aplikacji zakupowej isNetworkAvailable()
// dodaj zabezpieczenie ilosci znakow do wyswietlenia w holderze i sprawdzic do jakiej dlugosci nazwy uzytkownika ma to dzialac

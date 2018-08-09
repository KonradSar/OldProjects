package com.example.konrad.apzumiproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import static com.example.konrad.apzumiproject.GeneralMethods.*;
import static java.util.Collections.*;

public class MainActivity extends AppCompatActivity {


    // Fab Icon downloaded from view-source:https://pl.icons8.com/icon/set/sort/color dateTime: 09.08.18, 18:39
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ImageView imageView;
    TextView userNameForSplitView;
    TextView repoNameForSplitView;
    TextView additionalInfoForSplitView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingbtn);
        floatingActionButton.setVisibility(View.VISIBLE);
        imageView = (ImageView) findViewById(R.id.userPhotoSplitView);
        userNameForSplitView = (TextView) findViewById(R.id.userNameSplitView);
        repoNameForSplitView = (TextView) findViewById(R.id.repositoryNameSplitView);
        additionalInfoForSplitView = (TextView) findViewById(R.id.descriptionSplitView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerForAPIData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(apiResults, MainActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);
        if(!apiResults.isEmpty()){
            adjustDataForAdapter();
        }else{
            Toast.makeText(getApplicationContext(), "No data to display", Toast.LENGTH_LONG).show();
        }

        // Za pomoca watku co 100 milisekunt sprawdzamy wartosc int i weglug jej wartosci wybieramy odpowiednia pozycje o zadanym indeksie
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while(true) {
                        sleep(10);
                        APIResults selectedUser = apiResults.get(position);
                        // Wrzocamy niektore polecenia do glownego watku bo inaczej sie wysypuje wiec przenosimy wszystko odnosnie
                        // username, useravatar, description, reponame do runOnUiThread(new Runnable(){} i probujemy zaladowac dane
                        // Oryg. You have to move the portion of the background task that updates the UI onto the main thread. There is a simple piece of code for this:
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                APIResults selectedUser = apiResults.get(position);
                                userNameForSplitView.setText("USER"+": "+selectedUser.getUserName());
                                repoNameForSplitView.setText("REOPSITORY"+": "+selectedUser.getRepositoryName());
                                additionalInfoForSplitView.setText("DESCRIPTION"+":  "+selectedUser.getDescription());
                                Picasso.with(context).load(selectedUser.getAvatarSource()).into(imageView);
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Korzystam z sortowania dostepnego w Android Studio a nastepnie wlasnym kodem scalam listy
                apiResultsSorted.clear();
                // Przygotowujemy pusta liste Stringow
                List<String> listOfStrings = new ArrayList<String>();
                List<APIResults> listOriginal = GeneralMethods.apiResults;
                // Iterujac po liscie z wynikami wyswietlanej w activity z kazdego elementu odczytujemy wartosc pola userName i przypisujemy do Stringa.
                // Nastepnie tego wlasnie Stringa przypisujemy do nowej listy, ktora potem posortujemy
                for(APIResults singleElement: listOriginal){
                    String singleUser = singleElement.getUserName();
                    listOfStrings.add(singleUser);
                }
                // Sortujemy liste Stringow
                Collections.sort(listOfStrings);

                List<APIResults> finalSortedList = new ArrayList<APIResults>();
                int counter = 0;
                String nextStringName = "";
                for(int i = 0; i < listOfStrings.size(); i++){
                    nextStringName = listOfStrings.get(i);
                    for(APIResults singleOriginalElement: listOriginal){
                        if (nextStringName.equals(singleOriginalElement.getUserName())){
                            finalSortedList.add(counter, singleOriginalElement);
                            apiResultsSorted.add(counter, singleOriginalElement);
                            counter += 1;
                        }
                    }
                }
                // Podmieniamy stara liste czyszczac ja i dodajac dane z innej juz posortowanej listy z generalmethods
                GeneralMethods.apiResults.clear();
                GeneralMethods.apiResults.addAll(apiResultsSorted);
                recyclerViewAdapter.notifyDataSetChanged();
                recyclerViewAdapter.notifyItemRemoved(position);
                recyclerViewAdapter.notifyItemRangeChanged(position, apiResults.size());
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        // W wyniku powyzszego sortowania obslugiwanego za pomoca FAB mamy posortowana liste Userow gotowa do wyswietlenia. Znajduje
        // sie ona w GeneralMethods.apiResultsSorted
    }

    public void adjustDataForAdapter() {
        RecyclerViewAdapter  r4 = new RecyclerViewAdapter(apiResults, MainActivity.this);
        recyclerView.setAdapter(r4);

    }

}


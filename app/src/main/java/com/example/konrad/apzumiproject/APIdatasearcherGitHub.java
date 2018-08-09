package com.example.konrad.apzumiproject;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.konrad.apzumiproject.GeneralMethods.apiResults;

/**
 * Created by Konrad on 08.08.2018.
 */

public class APIdatasearcherGitHub extends AsyncTask<Object, Object, List<APIResults>>{
    @Override
    protected List<APIResults> doInBackground(Object[] params) {
        URL url = null;
        // Pamietamy o wyczyszczeniu listy przed ponownym pobraniem danych
        GeneralMethods.apiResultsGitHub.clear();
        try {
            url = new URL("https://api.github.com/repositories");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();
            int responseCode = http.getResponseCode();
            if (responseCode == 200) { //jeżeli ok TO:
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(http.getInputStream()));
                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                // Mamy tablice obiekow czyli sytuacja jest bardzo prosta. Docieramy do kolejnych obiektow i pobieramy ich odpowiednie pola
                JSONArray jsonArray = new JSONArray(stringBuilder.toString());
                List<APIResults> preParse = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = (JSONObject) jsonArray.get(i);
                    String fullName = (String) obj.get("full_name");
                    String description = (String) obj.get("description");
                    // Wchodzimy glebiej do obiektu owner z avatarem_url
                    JSONObject singleObjOwner = (JSONObject) obj.get("owner");
                    String avatarURL = (String) singleObjOwner.get("avatar_url");
                    preParse.add(i, new APIResults(fullName, "GitHub", avatarURL, false, description));
                    GeneralMethods.apiResultsGitHub.add(i, preParse.get(i));
                    GeneralMethods.apiResults.add(apiResults.size(), preParse.get(i));
                }

            } else {
                System.out.println("tabla nie obsluzona");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            // Zabezpieczamy sie dodatkowo przed RuntimeException oraz NullPointerException, kotre wystepuja
        }catch (NullPointerException e) {
            e.printStackTrace();
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
        return GeneralMethods.apiResultsGitHub;
    }
}

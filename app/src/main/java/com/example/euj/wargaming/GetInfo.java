package com.example.euj.wargaming;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.euj.wargaming.utils.InputStreamOperations;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Euj on 19/10/2015.
 */
public class GetInfo extends Activity{
    Intent intent=null;
    public void callint()
    {
    intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.fr"));
    startActivity(intent);
    }
public   void getI()
{
    Intent intent =null;
    try {
        //  String myurl= "http://api.worldoftanks.eu/wot/account/info/?application_id=demo&language=fr&account_id=518153743";
        String myurl= "https://api.worldoftanks.eu/wot/auth/login/?application_id=7ae23772426dd2b4d758769f65850f26";
        URL url = new URL(myurl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe compl�mentaire:
             * Elle contient une m�thode InputStreamToString.
             */

        String result = InputStreamOperations.InputStreamToString(inputStream);
        System.out.println(result);
        callint();
        // On r�cup�re le JSON complet
//        JSONObject jsonObject = new JSONObject(result);
        // On r�cup�re le tableau d'objets qui nous concernent
     //   System.out.println(jsonObject.getString("status"));
      // JSONArray array = new JSONArray(jsonObject.getString("status"));
        // Pour tous les objets on r�cup�re les infos
       /* for (int i = 0; i < array.length(); i++) {
            // On r�cup�re un objet JSON du tableau
            JSONObject obj = new JSONObject(array.getString(i));
            System.out.println(obj);
            // On fait le lien Personne - Objet JSON


        }*/

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

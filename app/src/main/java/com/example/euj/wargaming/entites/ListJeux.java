package com.example.euj.wargaming.entites;

import com.example.euj.wargaming.utils.InputStreamOperations;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Euj on 07/11/2015.
 */
public class ListJeux {
    public String nation_i18n;
    public String contour_image;
    public String image;
    public String image_small;
    public String is_premium;
    public String level;
    public String name;
    public String id;

    public ListJeux() {
    }
    public ListJeux(String nation_i18n, String contour_image, String image, String image_small, String is_premium, String level, String name) {
        super();
        this.nation_i18n = nation_i18n;
        this.contour_image = contour_image;
        this.image = image;
        this.image_small = image_small;
        this.is_premium = is_premium;
        this.level = level;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNation_i18n() {
        return nation_i18n;
    }

    public void setNation_i18n(String nation_i18n) {
        this.nation_i18n = nation_i18n;
    }

    public String getContour_image() {
        return contour_image;
    }

    public void setContour_image(String contour_image) {
        this.contour_image = contour_image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public String getIs_premium() {
        return is_premium;
    }

    public void setIs_premium(String is_premium) {
        this.is_premium = is_premium;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static ArrayList<ListJeux> getVehl() {

        ArrayList<ListJeux> vehs = new ArrayList<ListJeux>();

        try {
            String myurl= "http://api.worldoftanks.eu/wot/encyclopedia/tanks/?application_id=7ae23772426dd2b4d758769f65850f26&fields=name%2Clevel%2Cimage%2Ctank_id";
//String myurl="http://api.worldoftanks.eu/wot/regularteams/list/?application_id=7ae23772426dd2b4d758769f65850f26";
            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            /*
             * InputStreamOperations est une classe complémentaire:
             * Elle contient une méthode InputStreamToString.
             */
            String result = InputStreamOperations.InputStreamToString(inputStream);
            System.out.println(result);

            // On récupère le JSON complet
            JSONObject jsonObject = new JSONObject(result);
            JSONObject res=jsonObject.getJSONObject("data").getJSONObject("1");


            // On récupère le tableau d'objets qui nous concernent
        /*    JSONArray array = new JSONArray(jsonObject.getJSONObject("data").getString("1"));

            // Pour tous les objets on récupère les infos
            for (int i = 0; i < array.length(); i++) {
                // On récupère un objet JSON du tableau
                JSONObject obj = new JSONObject(array.getString(i));
                // On fait le lien Personne - Objet JSON**/
            ListJeux veh = new ListJeux();

            veh.setName(res.getString("name"));
            veh.setLevel(res.getString("level"));
            veh.setImage(res.getString("image"));
            veh.setId(res.getString("tank_id"));

            vehs.add(veh);
            veh = new ListJeux();

            res=jsonObject.getJSONObject("data").getJSONObject("33");
            veh.setId(res.getString("tank_id"));
            veh.setName(res.getString("name"));
            veh.setLevel(res.getString("level"));
            veh.setImage(res.getString("image"));
            vehs.add(veh);
            veh = new ListJeux();
            res=jsonObject.getJSONObject("data").getJSONObject("49");
            veh.setId(res.getString("tank_id"));
            veh.setName(res.getString("name"));
            veh.setLevel(res.getString("level"));
            veh.setImage(res.getString("image"));
            vehs.add(veh);
            veh = new ListJeux();
            res=jsonObject.getJSONObject("data").getJSONObject("81");
            veh.setId(res.getString("tank_id"));
            veh.setName(res.getString("name"));
            veh.setLevel(res.getString("level"));
            veh.setImage(res.getString("image"));
            vehs.add(veh);
          /*  }*/



        } catch (Exception e) {
            e.printStackTrace();
        }
        // On retourne la liste des personnes
        return vehs;

    }
}
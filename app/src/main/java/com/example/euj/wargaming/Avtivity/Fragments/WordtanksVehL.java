package com.example.euj.wargaming.Avtivity.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.euj.wargaming.R;
import com.example.euj.wargaming.adapters.ListJeuxCustomAdapter;
import com.example.euj.wargaming.entites.ListJeux;
import com.example.euj.wargaming.utils.HelperHttp;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Euj on 15/11/2015.
 */
public class WordtanksVehL extends Fragment implements View.OnClickListener  {
    ListView Vehl;
    ListJeuxCustomAdapter VehlAdapter;
    List<ListJeux> vehls = new ArrayList<ListJeux>();
    ListJeux v;
    String URL = "http://api.worldoftanks.eu/wot/globalmap/fronts/?application_id=demo";

    public WordtanksVehL() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wvehl, container,
                false);
        Vehl = (ListView) rootView.findViewById(R.id.Vehl);
        Vehl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), vehls.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        new Thread(new Runnable() {
            public void run() {
              v= new ListJeux();
                vehls=v.getVehl();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // This code will always run on the UI thread, therefore is safe to modify UI elements.
                        VehlAdapter = new ListJeuxCustomAdapter(getActivity(), R.layout.veh_details_row,vehls);
                        Vehl.setAdapter(VehlAdapter);

                    }
                });
            }

        }).start();

    //    new AsycGetTeachers().execute();


        return rootView;
    }


    void parseJsonTeachers(List<ListJeux> vehls, String json) {
        try {

            JSONArray array = new JSONArray(json);
           System.out.println(array);
//			for (int i = 0; i < array.length(); i++) {
//				JSONObject j = array.getJSONObject(i);
//				Teacher teacher = new Teacher();
//
//				teacher.setId(j.optInt("id"));
//				teacher.setGivenName(j.optString("givenName"));
//				teacher.setLastName(j.optString("lastName"));
//				teacher.setClassName(j.optString("className"));
//				teacher.setImgPath(j.optString("imgPath"));
//				teachers.add(teacher);
//						}
            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);
                ListJeux veh = null;
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    veh = objectMapper.readValue(j.toString(),
                            ListJeux.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                vehls.add(veh);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        System.out.println("gg");
    }


    class AsycGetTeachers extends AsyncTask<String, Void, String> {
        ProgressDialog barProgressDialog = new ProgressDialog(getActivity());


        @Override
        protected void onPreExecute() {

            barProgressDialog.setTitle("Loading ...");
            barProgressDialog.setMessage("Load Data in progress ...");
            barProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            barProgressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            System.out.println("background");
            String jsonTeacherList = HelperHttp
                    .getJSONResponseFromURL("http://api.worldoftanks.eu/wot/account/tanks/?application_id=7ae23772426dd2b4d758769f65850f26&account_id=518153743");
            System.out.println(jsonTeacherList);
            parseJsonTeachers(vehls, jsonTeacherList);

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            VehlAdapter = new ListJeuxCustomAdapter(getActivity(), R.layout.veh_details_row,vehls);
            Vehl.setAdapter(VehlAdapter);
            barProgressDialog.dismiss();

            super.onPostExecute(result);
        }
    }


}

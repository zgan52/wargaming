package com.example.euj.wargaming.Avtivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.euj.wargaming.R;
import com.example.euj.wargaming.utils.Myapp;


public class worldoftanks extends Activity implements View.OnClickListener {
    String url = "https://api.worldoftanks.eu/wot/auth/login/?application_id=7ae23772426dd2b4d758769f65850f26";
    SharedPreferences user;
    SharedPreferences.Editor edit;
    public static  String Prefence_filename="user";

    Myapp app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldoftanks);
        Intent intent=getIntent();
        String log=intent.getStringExtra("logout");
        if(log=="true")
        {
            System.out.println(
                    "logout"
            );
        }
        app = (Myapp) getApplication();
        if (app.isConnected()) {
           launch();
        }
        else {
        }
        Button cnx= (Button) this.findViewById(R.id.button);
        cnx.setOnClickListener(this);
        WebView view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);

        view.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                System.out.println(view.getUrl());
                String Url = view.getUrl();
                if (Url.contains("status=ok")) {
                    int pos = Url.indexOf("&account");
                    int pos1 = Url.indexOf("nickname") + "nickname=".length();
                    int pos2 = Url.indexOf("account_id=") + "account_id=".length();
                    int pos3 = Url.indexOf("&expires_at");
                    int pos4=Url.indexOf("&nickname=");
                    int pos5=Url.indexOf("&access_token=")+"&access_token=".length();
                    String nom = (Url.substring(pos1, pos));
                    String id_c = Url.substring(pos2, pos3);
                    String acces_T=Url.substring(pos5,pos4);
                    System.out.println(id_c);
                    System.out.println("APP ID "+acces_T);
                    user = getSharedPreferences(Prefence_filename, MODE_PRIVATE);
                    edit = user.edit();
                    edit.putString("name", nom);
                    edit.putString("id_c", id_c);
                    edit.putString("acc_c",acces_T);
                    edit.commit();
                    app = (Myapp) getApplication();
                    app.setConnected(true);
                    launch();
                }

            }


        });

        view.loadUrl(url);

    }
    public void launch()
    {
        Intent intent = new Intent(this, worldoftanksassistant.class);
        startActivity(intent);
    }




    @Override
    public void onClick(View v) {
        app = (Myapp) getApplication();
        app.setConnected(false);
       launch();
    }
}

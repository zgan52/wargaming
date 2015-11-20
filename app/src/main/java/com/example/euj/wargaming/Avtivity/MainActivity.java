package com.example.euj.wargaming.Avtivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.euj.wargaming.GetInfo;
import com.example.euj.wargaming.R;
import com.example.euj.wargaming.adapters.ListJeuxCustomAdapter;
import com.example.euj.wargaming.entites.ListJeux;
import com.example.euj.wargaming.utils.Myapp;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

   public BufferedReader reader = null;
    public GetInfo getI=new GetInfo();
    List<ListJeux> imgList = null;
    SharedPreferences user;

    Myapp app;
    String img1 = "http://ps4daily.com/wp-content/uploads/2015/09/World-of-Tanks1.jpg";
    String img2 = "http://www.dotmmo.com/wp-content/uploads/2011/06/World-of-Warplanes-logo.jpg";
    String img3 = "https://i.ytimg.com/vi/f6E3c69y0aM/maxresdefault.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView malist=(ListView) findViewById(R.id.listView);
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.Relativ);
        user = getSharedPreferences("user", MODE_PRIVATE);
        System.out.println(user.getString("name",""));
        app = (Myapp) getApplication();
       /* if (app.isConnected()) {
            getActionBar().setIcon(R.drawable.icon_reports_connected);
            getActionBar().setTitle(user.getString("name",""));
        }
        else {
            getActionBar().setIcon(R.drawable.icon_reports_notconnected);
        }
        getActionBar().setDisplayHomeAsUpEnabled(true);*/

        imgList = new ArrayList<ListJeux>();
     /*   ListJeux A1 = new ListJeux(img1, "world of tanks", "world of tanks");
        ListJeux A2 = new ListJeux(img2, "world of planes", "world of planes");
        ListJeux A3 = new ListJeux(img3, "world of warship", "world of warship");
        imgList.add(A1);
        imgList.add(A2);
        imgList.add(A3);
        malist.setAdapter(new ListJeuxCustomAdapter(getBaseContext(), R.layout.one_article, imgList));*/
        malist.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }
    public void onClick(View v)
    {


        new Thread(new Runnable() {
        public void run() {
       /* getI.getI();*/

    }  }).start(); }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();




        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.logout)
        {
            app = (Myapp) getApplication();
            app.setConnected(false);
            Intent intent = new Intent(this, worldoftanks.class);
            intent.putExtra("logout","true");
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(position);
        if(position==0)
        {
            Intent intent = new Intent(this, worldoftanksassistant.class);
            startActivity(intent);

        }
        else{
            System.out.println(imgList.get(position).getName());

            Toast.makeText(this, imgList.get(position).getName(), Toast.LENGTH_LONG).show();

        }


    }


}

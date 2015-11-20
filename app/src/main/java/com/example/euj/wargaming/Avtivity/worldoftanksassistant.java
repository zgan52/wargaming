package com.example.euj.wargaming.Avtivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.euj.wargaming.Avtivity.Fragments.FriendsFragment;
import com.example.euj.wargaming.Avtivity.Fragments.HomeFragment;
import com.example.euj.wargaming.Avtivity.Fragments.MessagesFragment;
import com.example.euj.wargaming.Avtivity.Fragments.WordtanksVehL;
import com.example.euj.wargaming.R;
import com.example.euj.wargaming.entites.Joueur;

/**
 * Created by Bouba on 15/11/2015.
 *   // 7ae23772426dd2b4d758769f65850f26
 */
public class worldoftanksassistant extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
    //String URL = "http://api.worldoftanks.eu/wot/account/info/?application_id=84fcef7e2debf34a3139569aa512bb43&account_id=518153743";
    String URL = "http://193.95.2.149:8080/parsing/teacher";
    Joueur joueur = new Joueur();
    //private TextView cote,batailles,arbres,degat,frags,exrerience;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    SharedPreferences user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldoftanksassistant);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        user = getSharedPreferences("user", MODE_PRIVATE);
        drawerFragment.Name.setText(user.getString("name",""));





    }
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);

    }
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new WordtanksVehL();
                title = getString(R.string.title_home);
                System.out.println("++++++++++++++++++HOME");
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(R.string.title_friends);
                System.out.println("++++++++++++++++++Friends");
                break;
            case 2:
                fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                System.out.println("++++++++++++++++++Messages");
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}

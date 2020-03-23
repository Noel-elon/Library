package com.example.library.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.Adapter.LevelAdapter;
import com.example.library.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;


public class LevelFragment extends Fragment {
    public static List<String> levels;
    LevelAdapter levelAdapter;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    TextView header;
    FirebaseAuth firebaseAuth;
    NavController navController;
    SignUpFragment signUpFragment = new SignUpFragment();
    String hey = "HEY, ";


    public LevelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();

        levels = new ArrayList<>();

        levels.add("Year one");
        levels.add("Year two");
        levels.add("Year three");
        levels.add("Year four");
        levels.add("Year five");


        View view = inflater.inflate(R.layout.fragment_level, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences(signUpFragment.PREF_NAME, Context.MODE_PRIVATE);
        String name = preferences.getString(signUpFragment.PREF_TAG, "");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentNavHost);
        navigationView = view.findViewById(R.id.nav_view);
        drawerLayout = view.findViewById(R.id.drawerLayout);

        String finalString = hey + name;


        toolbar = view.findViewById(R.id.toolbarlev);
        toolbar.setBackgroundColor(getResources().getColor(R.color.layoutbg));
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("");
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        activity.getSupportActionBar().setTitle("");

        // NavigationUI.setupActionBarWithNavController(activity, navController, drawerLayout);
        // NavigationUI.setupWithNavController(navigationView, navController);
        //navigationView.setNavigationItemSelectedListener(activity);
        drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.material_drawer_open, R.string.material_drawer_close);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.about:
                        Toasty.info(getContext(), "About clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.aboutdev:
                        Toasty.info(getContext(), "About Dev clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        Toasty.info(getContext(), "Bella ciao!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        Navigation.findNavController(getView()).navigate(R.id.action_levelFragment_to_signInFragment);
                        break;
                    default:
                        return true;
                }

                return true;
            }
        });


        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = view.findViewById(R.id.levelRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        levelAdapter = new LevelAdapter(levels);
        recyclerView.setAdapter(levelAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_levelFragment_to_uploadFileFragment);
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //if (item.getItemId() == )
        return super.onOptionsItemSelected(item);
    }
}

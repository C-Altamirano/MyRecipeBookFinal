package com.example.caltamirano.myrecipebook;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.caltamirano.myrecipebook.adapters.RecipeAdapter;
import com.example.caltamirano.myrecipebook.model.DatosResponse;
import com.example.caltamirano.myrecipebook.model.Recipe;
import com.example.caltamirano.myrecipebook.model.RecipeBook;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();

    private View rootView;
    private BottomNavigationView menuBottonNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    //private

    /*
    private RecyclerView recetasRecyclerView;
    private RecipeAdapter recipeAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        recetasRecyclerView = (RecyclerView) findViewById(R.id.recetasRecyclerView);
        recetasRecyclerView.setHasFixedSize(true);
        recetasRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeAdapter = new RecipeAdapter(this);
*/

        //recetasRecyclerView.setAdapter(recipeAdapter);

        rootView = findViewById(R.id.rootView);
        //recetasRecyclerView.setAdapter(recipeAdapter);

        //cargarDatos();
        /*
        Recipe r = new Recipe();
        r.setId(35382);
        r.setTitle("Jalapeno Popper Grilled Cheese Sandwich");
        r.setPublisher("Closet Cooking");
        r.setPublisherUrl("http://closetcooking.com");
        r.setfUrl("http://food2fork.com/view/35382");
        r.setImageUrl("http://static.food2fork.com/Jalapeno2BPopper2BGrilled2BCheese2BSandwich2B12B500fd186186.jpg");

        List<String> ingredients = new ArrayList<String>();
        ingredients.add("2 jalapeno peppers, cut in half lengthwise and seeded");
        ingredients.add("2 slices sour dough bread");
        ingredients.add("1 tablespoon butter, room temperature");
        ingredients.add("2 tablespoons cream cheese, room temperature");
        ingredients.add("1/2 cup jack and cheddar cheese, shredded");
        ingredients.add("1 tablespoon tortilla chips, crumbled");
        r.setIngredients(ingredients);

        r.setSocialRank(75);
        r.setSourceUrl("http://www.closetcooking.com/2011/04/jalapeno-popper-grilled-cheese-sandwich.html");

        recipeAdapter.add(r);
        */


        fragmentManager = getSupportFragmentManager();
        menuBottonNavigationView = (BottomNavigationView) findViewById(R.id.menuBottonNavigationView);
        menuBottonNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.mainList:
                        fragment = new ListFragment();
                        //fragment.setFragmentManager(fragmentManager);
                        break;
                    case R.id.searchItems:
                        fragment = new SearchFragment();
                        break;
                    case R.id.savedItems:
                        fragment = new FavoriteFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

    }



    public void mostrarMessage(String mensaje) {
        Snackbar.make(rootView, mensaje, Snackbar.LENGTH_LONG).show();
    }
}

package com.example.caltamirano.myrecipebook;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.caltamirano.myrecipebook.adapters.RecipeAdapter;
import com.example.caltamirano.myrecipebook.model.DatosResponse;
import com.example.caltamirano.myrecipebook.model.Recipe;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements RecipeAdapter.OnRecipeSaveSelectedListener, RecipeAdapter.OnRecipeSelectedListener {

    private static final Gson gson = new Gson();

    private View rootView;
    private int page=1;

    private RecyclerView recetasRecyclerView;
    private RecipeAdapter recipeAdapter;
    private FloatingActionButton nextFloatingActionButton;
    private FloatingActionButton backFloatingActionButton;


    private Context context;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        rootView = inflater.inflate(R.layout.fragment_list, container, false);

        context = container.getContext();

        Fresco.initialize(getActivity());
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        recetasRecyclerView = (RecyclerView) rootView.findViewById(R.id.recetasRecyclerView);
        recetasRecyclerView.setHasFixedSize(true);
        recetasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeAdapter = new RecipeAdapter(getActivity());

        //recetasRecyclerView.setAdapter(recipeAdapter);

        recetasRecyclerView.setAdapter(recipeAdapter);

        nextFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.nextFloatingActionButton);
        backFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.backFloatingActionButton);
        //SwipeRefreshLayout
        /*
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page++;
                cargarDatos();
            }
        });
*/

        cargarDatos();

        nextFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recipeAdapter.clear();
                page++;
                if(page>=2)
                    backFloatingActionButton.setVisibility(View.VISIBLE);
                cargarDatos();

            }
        });

        backFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeAdapter.clear();
                page--;
                if(page<=1)
                    backFloatingActionButton.setVisibility(View.INVISIBLE);
                cargarDatos();
            }
        });

        return rootView;

    }

    private void cargarDatos() {
        DatosFood2ForkService service = ServiceGenerator.createService(DatosFood2ForkService.class);
        Call<DatosResponse> call = service.recipeByPage("7169669ec5bc70130193975269c829fd","t",page);

        call.enqueue(new Callback<DatosResponse>() {
            @Override
            public void onResponse(Call<DatosResponse> call, Response<DatosResponse> response) {
                //swipeRefreshLayout.setRefreshing(false);
                Log.e("MIAPP ", "ok");

                if (response.isSuccessful()) {
                    recipeAdapter.setDataset(response.body().getRecipes());
                    nextFloatingActionButton.setVisibility(View.VISIBLE);
                    Log.e("MIAPP ", "ok");
                } else {
                    Log.e("MIAPP ", "No se puede obtener los establecimientos");
                }

            }

            @Override
            public void onFailure(Call<DatosResponse> call, Throwable t) {
                //swipeRefreshLayout.setRefreshing(false);
                Log.e("MIAPP", "Error obteniendo establecimientos: " + t.getMessage());
                Log.e("MIAPP", "Error obteniendo establecimientos 2: " + t.toString());
                Log.e("MIAPP", "Error obteniendo establecimientos 3: " + call.toString());
            }
        });
    }


    private void mostrarMessage(String mensaje) {
        //Snackbar.make(rootView.findViewById(R.id.rootView) , mensaje, Snackbar.LENGTH_LONG).show();
        Toast.makeText(rootView.getContext(),mensaje, Toast.LENGTH_LONG);
    }

    @Override
    public void onRecipeSelected(Recipe recipe, Context context) {
        //mostrarMessage("detalle");
        Intent detail = new Intent(context , DetailActivity.class);
        detail.putExtra("id", recipe.getId());
        context.startActivity(detail);

    }

    @Override
    public void onRecipeSaveSelected(Recipe recipe, Context context) {

    }
}

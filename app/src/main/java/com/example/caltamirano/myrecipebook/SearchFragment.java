package com.example.caltamirano.myrecipebook;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.caltamirano.myrecipebook.adapters.RecipeAdapter;
import com.example.caltamirano.myrecipebook.model.DatosResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private static final Gson gson = new Gson();

    private View rootView;
    private int page = 1;
    private String query = "";

    private RecyclerView recetasRecyclerView;
    private RecipeAdapter recipeAdapter;
    private FloatingActionButton nextFloatingActionButton;
    private FloatingActionButton backFloatingActionButton;
    private Button searchButton;
    private EditText recipEditText;
    private LinearLayout searchView;
    private CoordinatorLayout resultView;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        Fresco.initialize(getActivity());
        super.onCreate(savedInstanceState);

        recetasRecyclerView = (RecyclerView) rootView.findViewById(R.id.recetasRecyclerView);
        recetasRecyclerView.setHasFixedSize(true);
        recetasRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recipeAdapter = new RecipeAdapter(getActivity());

        recetasRecyclerView.setAdapter(recipeAdapter);

        nextFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.nextFloatingActionButton);
        backFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.backFloatingActionButton);
        recipEditText = (EditText) rootView.findViewById(R.id.recipeEditText);
        searchButton = (Button) rootView.findViewById(R.id.searchButton);
        searchView = (LinearLayout) rootView.findViewById(R.id.searchView);
        resultView = (CoordinatorLayout) rootView.findViewById(R.id.resultView);
        searchView.setVisibility(View.VISIBLE);
        resultView.setVisibility(View.INVISIBLE);


        nextFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeAdapter.clear();
                page++;
                if (page >= 2)
                    backFloatingActionButton.setVisibility(View.VISIBLE);
                cargarDatos();
            }
        });

        backFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipeAdapter.clear();
                page--;
                if (page <= 1)
                    backFloatingActionButton.setVisibility(View.INVISIBLE);
                cargarDatos();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = recipEditText.getText().toString();
                searchView.setVisibility(View.INVISIBLE);
                resultView.setVisibility(View.VISIBLE);
                cargarDatos();
            }
        });


        return rootView;
    }

    private void cargarDatos() {
        DatosFood2ForkService service = ServiceGenerator.createService(DatosFood2ForkService.class);
        Call<DatosResponse> call = service.recipeByQueryPage("7169669ec5bc70130193975269c829fd", query, page);

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

}

package com.example.caltamirano.myrecipebook;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.caltamirano.myrecipebook.model.DatosResponse;
import com.example.caltamirano.myrecipebook.model.DetailResponse;
import com.example.caltamirano.myrecipebook.model.RecipeDetail;
import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView publisherTextView;
    private TextView ingredientsTextView;
    private TextView urlTextView;
    private ImageView fotoImageView;
    private RatingBar rankRatingBar;


    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Fresco.initialize(this.getBaseContext().getApplicationContext());


        id=getIntent().getExtras().getString("id");

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        publisherTextView = (TextView) findViewById(R.id.publisherTextView);
        urlTextView = (TextView) findViewById(R.id.urlTextView);
        ingredientsTextView = (TextView) findViewById(R.id.ingredientsTextView);
        fotoImageView = (ImageView) findViewById(R.id.fotoImageView);
        rankRatingBar = (RatingBar) findViewById(R.id.rankRatingBar);

        cargarDatos();
    }




    private void cargarDatos() {
        DatosFood2ForkService service = ServiceGenerator.createService(DatosFood2ForkService.class);
        Call<DetailResponse> call = service.recipeDetail("7169669ec5bc70130193975269c829fd",id);

        call.enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {
                //swipeRefreshLayout.setRefreshing(false);
                Log.e("MIAPP ", "ok");

                if (response.isSuccessful()) {
                    RecipeDetail a = response.body().getRecipe();
                    titleTextView.setText(a.getTitle());
                    publisherTextView.setText(a.getPublisher());
                    urlTextView.setText(a.getfUrl());
                    ingredientsTextView.setText(a.getIngredients().toString());
                    rankRatingBar.setRating((float) (a.getSocialRank() * 0.05));

                    Uri uri = Uri.parse(a.getImageUrl());
                    fotoImageView.setImageURI(uri);

                    Log.e("MIAPP ", "ok");
                } else {
                    Log.e("MIAPP ", "No se puede obtener los establecimientos");
                }

            }

            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {
                //swipeRefreshLayout.setRefreshing(false);
                Log.e("MIAPP", "Error obteniendo establecimientos: " + t.getMessage());
                Log.e("MIAPP", "Error obteniendo establecimientos 2: " + t.toString());
                Log.e("MIAPP", "Error obteniendo establecimientos 3: " + call.toString());
            }
        });
    }


}

package com.example.caltamirano.myrecipebook;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import com.example.caltamirano.myrecipebook.model.DatosResponse;
import com.example.caltamirano.myrecipebook.model.DetailResponse;
import com.example.caltamirano.myrecipebook.model.RecipeBook;
import com.example.caltamirano.myrecipebook.model.RecipeDetail;

public interface DatosFood2ForkService {

    @GET("search")
    Call<DatosResponse> recipeByPage(@Query("key") String apiKey, @Query("page") int page);

    @GET("search")
    Call<DatosResponse> recipeByPage(@Query("key") String apiKey, @Query("sort") String sort, @Query("page") int page);

    @GET("search")
    Call<DatosResponse> recipeByQueryPage(@Query("key") String apiKey, @Query("q") String query, @Query("page") int page);


    @GET("search")
    Call<DatosResponse> recipeByQuery(@Query("key") String apiKey, @Query("q") String query);

    @GET("get")
    Call<DetailResponse> recipeDetail(@Query("key") String apiKey, @Query("rId") String rId);

}

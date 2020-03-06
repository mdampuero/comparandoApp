package com.ar.comparando.services;

import com.ar.comparando.models.Product;
import com.ar.comparando.responses.ResponsesLenders;
import com.ar.comparando.responses.ResponsesProducts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("products")
    Call<ResponsesLenders> getLenders();

    @GET("products/search?limit=100")
    Call<ResponsesProducts> getProducts(@Query("query") String query);

    @GET("products/{barcode}")
    Call<Product> getProduct(@Path("barcode") String barcode);
}

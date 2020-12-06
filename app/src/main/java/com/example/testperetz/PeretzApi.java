package com.example.testperetz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PeretzApi {

    @GET("products?category=93&key=47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278")
    Call<List<ListItem>> getItems();
}

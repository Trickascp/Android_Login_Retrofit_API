package com.example.testauth.remote;

import com.example.testauth.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @FormUrlEncoded
    @POST("auth/signin")
    Call<ResponseBody> goLogin(@Field("username") String username,
                       @Field("password") String password);

}

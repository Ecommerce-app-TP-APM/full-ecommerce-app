package com.example.app_project;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

        @POST("authenticate/")
    Call<ReponseLogin> loginUser(@Body RequestLogin RequestLogin);


        @POST("users/")
    Call<ReposeRegister> registerUsers(@Body RequestRegister RequestRegister);


}



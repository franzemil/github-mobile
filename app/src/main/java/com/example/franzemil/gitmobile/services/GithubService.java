package com.example.franzemil.gitmobile.services;


import com.example.franzemil.gitmobile.models.Readme;
import com.example.franzemil.gitmobile.models.RepositorioResponse;
import com.example.franzemil.gitmobile.models.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("search/repositories")
    Call<RepositorioResponse> getRepositories();

    @GET("search/repositories?sorter=starts&order=desc")
    Call<RepositorioResponse> getRepositoriesByLanguage(@Query("q") String code);

    @GET("users/{login}")
    Call<Usuario> getUserInfo(@Path("login") String login);

    @GET("repos/{owner}/{repo}/readme")
    Call<Readme> getReadme(@Path("owner") String owner, @Path("repo") String repo);

}

package com.example.franzemil.gitmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.franzemil.gitmobile.adapters.RepositorioAdapter;
import com.example.franzemil.gitmobile.models.Language;
import com.example.franzemil.gitmobile.models.RepositorioResponse;
import com.example.franzemil.gitmobile.models.Repositorio;
import com.example.franzemil.gitmobile.services.GithubService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LanguageActivity extends AppCompatActivity implements RepositorioAdapter.RepositorioListener {

    Language language;
    SwipeRefreshLayout languageSwipe;

    RecyclerView repositorioRecylerView;

    RepositorioAdapter repositorioAdapter = new RepositorioAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        language = (Language) getIntent().getExtras().getSerializable("LANGUAGE");
        languageSwipe = (SwipeRefreshLayout) findViewById(R.id.languageSwipe);

        languageSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRepositoryData(language.getCode());
            }
        });

        repositorioRecylerView = (RecyclerView) findViewById(R.id.repositorioRecyclerView);
        repositorioRecylerView.setAdapter(repositorioAdapter);
        repositorioRecylerView.setLayoutManager(new LinearLayoutManager(this));
        repositorioRecylerView.setHasFixedSize(false);

        loadRepositoryData(language.getCode());

        repositorioAdapter.setListener(this);

    }

    private void loadRepositoryData(String code) {
        languageSwipe.setRefreshing(true);
        GithubService service = ServiceGenerator.createService(GithubService.class);
        Call<RepositorioResponse> call = service.getRepositoriesByLanguage("language:" + code);

        call.enqueue(new Callback<RepositorioResponse>() {
            @Override
            public void onResponse(Call<RepositorioResponse> call, Response<RepositorioResponse> response) {
                languageSwipe.setRefreshing(false);
                if (response.isSuccessful()) {
                    for (Repositorio repo: response.body().getRepositorios()) {
                        Log.d("REPO", repo.getName());
                    }
                    repositorioAdapter.setDataSet(response.body().getRepositorios());
                }
            }

            @Override
            public void onFailure(Call<RepositorioResponse> call, Throwable t) {
                languageSwipe.setRefreshing(false);
            }
        });
    }


    @Override
    public void onClickListener(Repositorio repositorio) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra("REPOSITORY", repositorio);
        startActivity(intent);
    }
}

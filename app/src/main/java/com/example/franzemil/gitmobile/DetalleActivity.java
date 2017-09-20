package com.example.franzemil.gitmobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.franzemil.gitmobile.models.Readme;
import com.example.franzemil.gitmobile.models.Repositorio;
import com.example.franzemil.gitmobile.models.Usuario;
import com.example.franzemil.gitmobile.services.GithubService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.feras.mdv.MarkdownView;


public class DetalleActivity extends AppCompatActivity{

    Repositorio repositorio;
    Usuario usuario;

    TextView nameTextView, fullNameTextView,  descriptionTextView;
    ImageView avatarImageView;
    TextView loginTextView, numeroRepositoriosTextView, bioTextView, numSeguidoresTextView;

    MarkdownView readmeMarkdownView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        fullNameTextView = (TextView) findViewById(R.id.fullNameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descripcionTextView);

        avatarImageView = (ImageView) findViewById(R.id.logoUsuarioImageView);

        loginTextView = (TextView) findViewById(R.id.loginNameTextView);
        bioTextView = (TextView) findViewById(R.id.bioTextView);
        numeroRepositoriosTextView = (TextView) findViewById(R.id.numeroRepositoriosTextView);
        numSeguidoresTextView = (TextView) findViewById(R.id.numeroSeguidoresTextView);

        readmeMarkdownView = (MarkdownView) findViewById(R.id.readmeMarkdownView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        repositorio = (Repositorio) getIntent().getExtras().getSerializable("REPOSITORY");

        loadDetalle(repositorio);

        nameTextView.setText(repositorio.getName());
        fullNameTextView.setText(repositorio.getFullName());
        descriptionTextView.setText(repositorio.getDescription());
    }

    private void loadDetalle(Repositorio repositorio) {
        GithubService service = ServiceGenerator.createService(GithubService.class);
        Call<Usuario> call = service.getUserInfo(repositorio.getUsuario().getLogin());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (response.isSuccessful()) {
                    usuario = response.body();
                    loadUserData(usuario);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    private void loadUserData(Usuario usuario) {

        Glide.with(this).load(usuario.getAvatarUrl()).into(avatarImageView);
        loginTextView.setText(usuario.getLogin());
        bioTextView.setText(usuario.getBio());
        numeroRepositoriosTextView.setText("Repositorios: " + usuario.getPublicRepos().toString());
        numSeguidoresTextView.setText("Seguidores:" + usuario.getFollowers().toString());

        GithubService service = ServiceGenerator.createService(GithubService.class);
        Call<Readme> call = service.getReadme(usuario.getLogin(), repositorio.getName());
        call.enqueue(new Callback<Readme>() {
            @Override
            public void onResponse(Call<Readme> call, Response<Readme> response) {
                if (response.isSuccessful()) {
                    readmeMarkdownView.loadMarkdownFile(response.body().getDownloadUrl());
                }
            }

            @Override
            public void onFailure(Call<Readme> call, Throwable t) {

            }
        });

    }


}

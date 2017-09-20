package com.example.franzemil.gitmobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.franzemil.gitmobile.adapters.LanguageAdapter;
import com.example.franzemil.gitmobile.models.Language;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LanguageAdapter.LanguageListener {

    RecyclerView languageRecyclerView;
    LanguageAdapter adapter = new LanguageAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter.setListener(this);
        languageRecyclerView = (RecyclerView) findViewById(R.id.languageRecyclerView);
        languageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        languageRecyclerView.setHasFixedSize(true);
        languageRecyclerView.setAdapter(adapter);
        cargarLanguages();
    }


    public void cargarLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language("Python", "python", "https://i.stack.imgur.com/jBli3.png"));
        languages.add(new Language("Php", "php", "https://icon-icons.com/icons2/887/PNG/512/PHP_icon-icons.com_68990.png"));
        languages.add(new Language("Javascript", "javascript", "http://ecodile.com/wp-content/uploads/2015/10/node_icon2.png"));
        languages.add(new Language("Java", "java", "https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/419px-Java_programming_language_logo.svg.png"));
        adapter.setDataSet(languages);
    }

    @Override
    public void onLanguageClickListener(Language language) {

        Intent activity = new Intent(this, LanguageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("LANGUAGE", language);
        activity.putExtras(bundle);
        startActivity(activity);

    }
}

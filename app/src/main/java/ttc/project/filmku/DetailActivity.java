package ttc.project.filmku;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewFilmDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("DETAIL");
        }

        textViewFilmDetail = findViewById(R.id.tv_film_detail);
        Button buttonSearch = findViewById(R.id.btn_search);

        buttonSearch.setOnClickListener(this);


        Intent intent = getIntent();

        if (intent != null) {
            if (intent.getStringExtra("film_title") != null) {
                String title = intent.getStringExtra("film_title");
                textViewFilmDetail.setText(title);
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                String movie_title = textViewFilmDetail.getText().toString();
                searchWeb(movie_title);
                break;
        }
    }

    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

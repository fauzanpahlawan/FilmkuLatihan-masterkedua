package ttc.project.filmku;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    private final static String TAG_LIFECYCLE = "Lifecycle";

    //    COMPLETED (5) Buat variable text view tv_total_result yg dibuat di layout
    //    COMPLETED (6) Override onCreateOptionsMenu dan inflate menu yang dibuat
    //    COMPLETED (7) Handle onSelected menu dengan override onOptionsItemSelected
    //    COMPLETED (8) onOptionsItemSelected berisi method untuk mengeluarkan toast (sementara)

    private ArrayList<String> filmTitle = new ArrayList<>();
    private TextView textViewTotalResult;
    private RecyclerView recyclerViewFilm;
    private FilmAdapter filmAdapter;
    private ProgressBar progressBar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_get_popular:
                Uri uriGetPopular = NetworkUtils.uriBuilderGetPopular();
                new NetworkTask().execute(uriGetPopular);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getTotalResult(String data) {
        try {
            JSONObject jsonData = new JSONObject(data);
            return jsonData.getString("total_results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<String> filmTitle(String data) {
        ArrayList<String> results = new ArrayList<>();
        try {
            JSONObject jsonData = new JSONObject(data);
            JSONArray filmResults = jsonData.getJSONArray("results");
            for (int i = 0; i < filmResults.length(); i++) {
                JSONObject filmResultsItems = filmResults.getJSONObject(i);
                String filmTitle = filmResultsItems.getString("title");
                results.add(filmTitle);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    //AsyncTask<Data to process by AsynTask, Data to return onProgressUpdate, Data to return onPostExecute>
    class NetworkTask extends AsyncTask<Uri, Integer, String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        //Task containing that will be executed by AsynTask in the Background Thread
        @Override
        protected String doInBackground(Uri... uris) {
            URL getPopularURL = null;
            try {
                getPopularURL = new URL(uris[0].toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (getPopularURL != null) {
                try {
                    return NetworkUtils.getResponseFromHttpUrl(getPopularURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //Task that will be executed by AsynTask in the Main Thread
        //Last method to be executed in the AsynTask serial
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.INVISIBLE);
            textViewTotalResult.setText(getTotalResult(s));
            filmTitle = filmTitle(s);
            filmAdapter.setData(filmTitle);
            filmAdapter.notifyDataSetChanged();

        }
    }

    //IAK1 connect to the internet
    //mulai persiapan koneksi ke internet
    //    COMPLETED (9) beri permission connect internet di androidmanifest
    //    COMPLETED (10) cek kelas network utils sebagai class helper
    //mulai koneksikan ke internet
    //    COMPLETED (16) coba panggil method getResponsefromHttp di onSelectedMenu (pasti error karena tidak di bg)
    //    COMPLETED (17) karena error kita harus buat asyntask, sekarang buat inner class asyntask
    //    COMPLETED (18) buat asyntask dengan parameter <Url, Void, String>, Url adalah input nya,
    //    Void return progress nya, dan String return setelah selesainya
    //    COMPLETED (19) override seluruh method di asyntask dan penjelasan permethodnya
    //    TCOMPLETEDODO (20) panggil getResponseFromHttp di doInBackground
    //    COMPLETED (21) setText result di tv_total_result di onPostExecute
    //    parsing json
    //    TODO (22) karena kita ingin menampilkan jumlah result saja, kita perlu parsing json
    //    TODO (23) buat method untuk parsing dan mengambil nilai total result bernama getTotaResult(String jsonData)
    //    TODO (24) penjelasan json dan logika dalam methodnya

    //IAK2 showing item using recyclerview
    //set up recyclerview
    //    TODO (25) lihat terlebih dahulu penjelasan komponen recyclerview
    //    TODO (26) untuk menggunakan recyclerview, kita harus menambahkan depedency terlebih dahulu di gradle :module
    //    TODO (29) buat variable Recyclerview dan find di onCreate
    //set up viewholder
    //    TODO (30) buat resource baru film_item sebagai layout setiap items pada recyclerview nantinya
    //    TODO (31) gunakan linearlayout height=wrap_content, width=match_parent
    //    TODO (32) dalam film_item.xml, tambahkan textview dengan id=film_name", height = 100dp, dan gravity = center, margin=10dp
    //    TODO (33) sekarang buat kelas baru dgn nama FilmViewHolder dan Extends RecyclerView.ViewHolder
    //    TODO (34) dalam FilmViewHolder, buat variable TextView dan find view nya menggunakan view
    //set up adapter
    //    TODO (35) buat class baru bernama FilmAdapter extends Recyclerview.Adapter<FilmViewHolder>
    //    TODO (36) override method yang diperlukan, dan buat constructor juga yang menerima context
    //    TODO (37) buat method setData() yg menerima arraylist string sebagai data judul film
    //    TODO (38) buat LayoutInflater dan inflate film_item
    //    TODO (39) return view dari layout inflater
    //    TODO (40) sekarang ke onBindViewHolder, set tv_name_film dengan arraylist pada posisi yang ada di parameter method
    //    TODO (41) jangan lupa di getItemCount() return jumlah data yg diterima
    //menyambungkan recyclerview dengan adapter
    //    TODO (42) sekarang buat Layout manager di onCreate, disini kita akan menggunakan LinearLayoutManager
    //    TODO (43) buat variable adapter di class, dan instansiasi di Oncreate
    //    TODO (44) set layout manager di recyclerview
    //    TODO (45) set adapter di recyclerview
    //memasukkan data yang didapat dari internet ke recyclerview
    //    TODO (46) sekarang, buat method sendData(String s) dgn parameter jsondata
    //    TODO (47) ambil id dengan mengiterasikan jsonarray dan masukkan dalam arraylist
    //    TODO (48) ambil judul dengan mengiterasikan jsonarray dan masukkan dalam arraylist
    //    TODO (49) panggil method swapAdater dari adapter dan masukkan arraylist
    //    TODO (50) panggil method notifyDataSetChanged dari adapter untuk mengupdate data source
    //    TODO (51) panggil method sendData di onPostExecute pada asyntask dan masukkan jsondata

    //IAK3 Lifecycle Aware
    //    TODO (52) cek kondisi jika dirotate
    //    TODO (53) untuk mengatasi kondisi ini, override method onSavedInstanceState yg paramter nya hanya outstate
    //    TODO (54) simpan value text saat ini di tv_total_result ke dalam bundle
    //    TODO (55) simpan value arraylist judul_list
    //    TODO (56) akses bundle dari onCreate
    //    TODO (57) set text dari bundle
    //    TODO (58) swap adapter dengan list, jangan lupa notifydatasetchanged

    //IAK4 Interaction between activities
    //    TODO (59) buat kelas baru yaitu DetailActivity.java
    //    TODO (60) dalam layout detailactivity, tambahkan 1 textview tv_id dan 1 tombol btn_search
    //    TODO (61) sekarang ke kelas adapter, di kelas onBindViewHolder, handle onClick di itemView
    //    TODO (62) dalam method onClick, definisikan explicit intent ke detailactivity
    //    TODO (63) kemudian tambahkan extra id dan judul kedalam explicit intent
    //    TODO (64) di dalam detailactivity, ambil id dan nama
    //    TODO (65) ubah title action bar dengan nama, kemudian tampilkan id dan nama
    //    TODO (66) handle onclick di detail activity untuk serach title film

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG_LIFECYCLE, "OnCreate");

        progressBar = findViewById(R.id.inderteminate_progress_bar);
        textViewTotalResult = findViewById(R.id.tv_total_result);
        recyclerViewFilm = findViewById(R.id.rv_films);

        progressBar.setVisibility(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        filmAdapter = new FilmAdapter(this);
        recyclerViewFilm.setLayoutManager(layoutManager);
        recyclerViewFilm.setAdapter(filmAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LIFECYCLE, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LIFECYCLE, "OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG_LIFECYCLE, "OnRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LIFECYCLE, "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LIFECYCLE, "OnDestroy");
    }
}

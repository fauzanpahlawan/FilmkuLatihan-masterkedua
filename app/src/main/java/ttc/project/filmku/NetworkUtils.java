package ttc.project.filmku;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    //    COMPLETED (11) buat variable TMDB base url sebagai base URL
    //    COMPLETED (12) buat variable TMDB api key untuk menyimpan api key mu
    //    COMPLETED (13) buat method untuk membangun URL request popular movies
    //    COMPLETED (14) buat URI sesuai dokumentasi TMDB
    //    TODO (15) buat URL dari URI yang telah dibuat

    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/";
    private static final String TMDB_API_KEY = "981e4de53fa7c562b91c898b4387ff4c";

    public static Uri uriBuilderGetPopular() {
        Uri.Builder uriBuilderGetPopular = new Uri.Builder();
        Uri uri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendPath("movie")
                .appendPath("popular")
                .appendQueryParameter("api_key", TMDB_API_KEY)
                .build();
        return uri;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

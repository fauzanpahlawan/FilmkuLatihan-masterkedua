package ttc.project.filmku;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class FilmViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewPosition;
    public TextView textViewFilmName;

    public FilmViewHolder(View itemView) {
        super(itemView);
        textViewPosition = itemView.findViewById(R.id.tv_position);
        textViewFilmName = itemView.findViewById(R.id.tv_film_name);
    }
}

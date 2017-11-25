package ttc.project.filmku;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private Context mContext;
    private ArrayList<String> filmNames;

    public FilmAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<String> filmNames) {
        this.filmNames = filmNames;
    }

    @Override
    public FilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_views, parent, false);

        return new FilmViewHolder(view);
    }


    //onBindViewHolder binding view to every row
    //also can be used to make interaction for every object binded
    @Override
    public void onBindViewHolder(FilmViewHolder holder, int position) {
        holder.textViewPosition.setText(String.valueOf(position));
        holder.textViewFilmName.setText(filmNames.get(position));
        final Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("film_title", filmNames.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (filmNames == null) {
            return 0;
        } else {
            return filmNames.size();
        }
    }
}

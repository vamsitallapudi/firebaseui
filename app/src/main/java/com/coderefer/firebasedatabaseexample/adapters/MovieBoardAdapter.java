package com.coderefer.firebasedatabaseexample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.coderefer.firebasedatabaseexample.models.Movie;
import com.coderefer.firebasedatabaseexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vamsi on 13-Jul-16.
 */

public class MovieBoardAdapter extends RecyclerView.Adapter<MovieBoardAdapter.ViewHolder> {



    private List<Movie> movieBoardList;
    private Context context;

    public MovieBoardAdapter(Context context, List<Movie> movieBoardList){
        this.context = context;
        this.movieBoardList = movieBoardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_board_item,parent,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movieBoard = movieBoardList.get(position);
        holder.tvMovieName.setText(String.valueOf(movieBoard.getMovieName()));
        holder.ratingBar.setRating(movieBoard.getMovieRating());
        Picasso.with(context).load(movieBoard.getMoviePoster()).into(holder.ivMoviePoster);
    }

    @Override
    public int getItemCount() {
        return movieBoardList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvMovieName;
        RatingBar ratingBar;
        ImageView ivMoviePoster;

        public ViewHolder(View v) {
            super(v);
            tvMovieName = (TextView) v.findViewById(R.id.tv_name);
            ratingBar = (RatingBar) v.findViewById(R.id.rating_bar);
            ivMoviePoster = (ImageView) v.findViewById(R.id.iv_movie_poster);
        }
    }
}

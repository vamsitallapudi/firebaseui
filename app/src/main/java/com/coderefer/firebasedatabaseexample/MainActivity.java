package com.coderefer.firebasedatabaseexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coderefer.firebasedatabaseexample.fragments.AddMovieFragment;
import com.coderefer.firebasedatabaseexample.models.Movie;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    ScaleAnimation shrinkAnim;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private TextView tvNoMovies;

    //Getting reference to Firebase Database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabaseReference = database.getReference();

    private static final String userId = "53";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing our Recyclerview
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        tvNoMovies = (TextView) findViewById(R.id.tv_no_movies);

        //scale animation to shrink floating actionbar
        shrinkAnim = new ScaleAnimation(1.15f, 0f, 1.15f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        if (mRecyclerView != null) {
            //to enable optimization of recyclerview
            mRecyclerView.setHasFixedSize(true);
        }
        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Say Hello to our new Firebase UI Element, i.e., FirebaseRecyclerAdapter
        final FirebaseRecyclerAdapter<Movie,MovieViewHolder> adapter = new FirebaseRecyclerAdapter<Movie, MovieViewHolder>(
                Movie.class,
                R.layout.movie_board_item,
                MovieViewHolder.class,
                //referencing the node where we want the database to store the data from our Object
                mDatabaseReference.child("users").child(userId).child("movies").getRef()
        ) {
            @Override
            protected void populateViewHolder(MovieViewHolder viewHolder, Movie model, int position) {
                if(tvNoMovies.getVisibility()== View.VISIBLE){
                    tvNoMovies.setVisibility(View.GONE);
                }
                viewHolder.tvMovieName.setText(model.getMovieName());
                viewHolder.ratingBar.setRating(model.getMovieRating());
                Picasso.with(MainActivity.this).load(model.getMoviePoster()).into(viewHolder.ivMoviePoster);
            }
        };

        mRecyclerView.setAdapter(adapter);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, new AddMovieFragment())
                        .addToBackStack(null)
                        .commit();
                //animation being used to make floating actionbar disappear
                shrinkAnim.setDuration(400);
                fab.setAnimation(shrinkAnim);
                shrinkAnim.start();
                shrinkAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //changing floating actionbar visibility to gone on animation end
                        fab.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {


                    }
                });

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (fab.getVisibility() == View.GONE)
            fab.setVisibility(View.VISIBLE);
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView tvMovieName;
        RatingBar ratingBar;
        ImageView ivMoviePoster;

        public MovieViewHolder(View v) {
            super(v);
            tvMovieName = (TextView) v.findViewById(R.id.tv_name);
            ratingBar = (RatingBar) v.findViewById(R.id.rating_bar);
            ivMoviePoster = (ImageView) v.findViewById(R.id.iv_movie_poster);
        }
    }



}

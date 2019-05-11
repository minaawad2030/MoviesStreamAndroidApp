package com.example.testapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<MyData> myData;

    public RecyclerAdapter(Context context, List<MyData> myData) {
        this.context = context;
        this.myData = myData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card ,viewGroup,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {

        viewHolder.title.setText(myData.get(i).getName());
        viewHolder.description.setText(myData.get(i).getDescription());
        Picasso.get().load(myData.get(i).getImageURL()).into(viewHolder.imageView);

        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, myData.get(i).getName(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context,ShowMovie.class);
                //flag to allow calling startActivity() from outside activity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("video",myData.get(i).getVideoURL());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title,description ;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.movie_title);
            description=(TextView) itemView.findViewById(R.id.movie_desciption);
            imageView=(ImageView)itemView.findViewById(R.id.movie_img);
            relativeLayout=(RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}

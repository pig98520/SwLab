package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Leisure_Exhibition_Activity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Leisure_Exhibition_Activity.this, Leisure_Activity.class);
        startActivity(intent);
        finish();
    }
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leisure_exhibition);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        DatabaseReference databaseRef=FirebaseDatabase.getInstance().getReference("server/exhibition");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot pisSnapshot : dataSnapshot.getChildren()) {
                    DB_Lerisure_Exhibition lerisure=pisSnapshot.getValue(DB_Lerisure_Exhibition.class);
                    Log.i("Photo's Title:", lerisure.getTitle());
                    Log.i("Photo's Content:", lerisure.getContent());
                    Log.i("Photo's Url:", lerisure.getImageUrl());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Photo", "failed: " + databaseError.getMessage());
            }
        });
        FirebaseRecyclerAdapter<DB_Lerisure_Exhibition,LeisureViewHolder> adapter=
                new FirebaseRecyclerAdapter<DB_Lerisure_Exhibition, LeisureViewHolder>(DB_Lerisure_Exhibition.class,R.layout.leisure_theater_list,LeisureViewHolder.class,databaseRef) {
                    @Override
                    protected void populateViewHolder(LeisureViewHolder viewHolder, DB_Lerisure_Exhibition model, int position) {
                        viewHolder.setPhoto(model);
                    }
                };
        recyclerView.setAdapter(adapter);
    }
    public static class LeisureViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView content;

        public LeisureViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.txt_title);
            content = (TextView) itemView.findViewById(R.id.txt_content);
        }

        public void setPhoto(DB_Lerisure_Exhibition lerisure) {
            title.setText(lerisure.getTitle());
            /* content.setText(lerisure.getContent());*/
            content.setText(Html.fromHtml("<a href="+lerisure.getContent()+">官方網站</a> "));
            content.setMovementMethod(LinkMovementMethod.getInstance());
            Glide.with(image.getContext())
                    .load(lerisure.getImageUrl())
                    .into(image);
        }
    }

}


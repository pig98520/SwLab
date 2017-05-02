package com.example.swlab.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Leisure_Exhibition_Activity extends AppCompatActivity {
    public class Exhibition {
        private String date;
        private String title;
        private String url;
        private String png;

        public void setDate(String date) {this.date = date;}
        public String getDate() {return date;}

        public void setPng(String png) {this.png = png;}
        public String getPng() {return png;}

        public void setTitle(String title) {this.title = title;}
        public String getTitle() {return title;}

        public void setUrl(String url) {this.url = url;}
        public String getUrl() {return url;}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leisure_exhibition);
        Firebase.setAndroidContext(this);
        processView();
        processControl();
    }

    private void processControl() {

    }

    private void processView() {

    }


    private ArrayList<Exhibition> list = new ArrayList<Exhibition>();

        private void setupFirebase() {
            final String exhibition_date = "date";
            final String exhibitiob_png = "png";
            final String exhibitiob_title = "title";
            final String exhibitiob_url = "url";

            /*DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("exhibition");
            dbRef.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                @Override
                public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {
                    Exhibition n = new Exhibition();
                    n.setDate((String) dataSnapshot.child(exhibition_date).getValue());
                    n.setPng((String) dataSnapshot.child(exhibitiob_png).getValue());
                    n.setTitle((String) dataSnapshot.child(exhibitiob_title).getValue());
                    n.setUrl((String) dataSnapshot.child(exhibitiob_url).getValue());

                    list.add(0,n);
                    adapter.notifyDataSetChanged();

                }

            });*/
        }

    }


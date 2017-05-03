
package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question_Activity extends AppCompatActivity {
    private ArrayList<DB_Question> list=new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        ListView listView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> adapterQ = new ArrayAdapter<String>(this,
                android.R.layout.two_line_list_item,
                android.R.id.text1);
        listView.setAdapter(adapterQ);

        DatabaseReference reference_contacts = FirebaseDatabase.getInstance().getReference("question");
        reference_contacts.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                adapterQ.clear();
                for(com.google.firebase.database.DataSnapshot ds:dataSnapshot.getChildren()){
                    adapterQ.add(ds.child("Que").getValue().toString());
                    adapterQ.add(ds.child("Ans").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
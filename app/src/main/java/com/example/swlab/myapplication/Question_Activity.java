
package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
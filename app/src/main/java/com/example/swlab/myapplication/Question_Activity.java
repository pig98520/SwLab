package com.example.swlab.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.swlab.myapplication.R.layout.question;

public class Question_Activity extends AppCompatActivity {
    private ListView listview;
    private List<DB_Question> questionList;
    private DatabaseReference databaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(question);
        processView();
        processControl();
    }

    private void processView() {
        databaseRef= FirebaseDatabase.getInstance().getReference("question");
        listview=(ListView)findViewById(R.id.listView);
        questionList=new ArrayList<>();
    }

    private void processControl() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionList.clear();

                for(DataSnapshot questionSnapshot : dataSnapshot.getChildren()){
                    DB_Question question=questionSnapshot.getValue(DB_Question.class);
                    questionList.add(question);
                }
                DB_Question_List adapter=new DB_Question_List(Question_Activity.this,questionList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

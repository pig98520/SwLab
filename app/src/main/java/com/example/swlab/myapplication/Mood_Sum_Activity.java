package com.example.swlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class Mood_Sum_Activity extends AppCompatActivity {
    private GraphView graph;
    private FirebaseAuth auth;
    private Firebase dataRef;
    private String[] x_array;
    private Integer[] y_array;
    private DataPoint[] dataPoints;
    private ArrayList<String> x_axis = new ArrayList<String>();
    private ArrayList<Integer> y_axis=new ArrayList<Integer>();

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setClass(Mood_Sum_Activity.this, Mood_Activity.class);
        startActivity(intent);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_sum);
        processView();
        getData();
/*        setGraph();*/
    }

    private void getData() {
        dataRef=new Firebase("https://swlabapp.firebaseio.com/user/moodDetection/"+auth.getCurrentUser().getUid());
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    x_axis.clear();
                    y_axis.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        DB_Mood_Detection db_mood_detection = postSnapshot.getValue(DB_Mood_Detection.class);
                        x_axis.add(db_mood_detection.getTime());
                        y_axis.add(Integer.parseInt(db_mood_detection.getScore()));
                    }
                    setGraph(x_axis, y_axis);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void processView() {
        graph = (GraphView) findViewById(R.id.graphView);
        auth=FirebaseAuth.getInstance();
    }

    private void setGraph(ArrayList<String> x_axis, ArrayList<Integer> y_axis) {
        if(x_axis.size()<2){
            x_array=new String[] {"0","0"};
            y_array=new Integer[]{ 0, 0};
            dataPoints = new DataPoint[y_array.length];
            for (int i = 0; i < dataPoints.length; i++)
                dataPoints[i] = new DataPoint(0,0);
        }
        else if(x_axis.size()<10) {
            x_array=new String[x_axis.size()];
            y_array=new Integer[y_axis.size()];
            x_array=x_axis.toArray(x_array);
            y_array=y_axis.toArray(y_array);
            dataPoints = new DataPoint[y_array.length];
            for (int i = 0; i < dataPoints.length; i++)
                dataPoints[i] = new DataPoint(i + 1, y_array[i]);
        }
        else {
            dataPoints = new DataPoint[10];
            x_array=new String[10];
            y_array=new Integer[10];
            for (int i = 0; i < dataPoints.length; i++) {
                x_array[i]=x_axis.get(x_axis.size()-10+i);
                y_array[i]=y_axis.get(y_axis.size()-10+i);
                dataPoints[i] = new DataPoint(i + 1, y_array[i]);
            }
        }

        LineGraphSeries<DataPoint> series=new LineGraphSeries<>(dataPoints);

        graph.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter=new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(x_array);
/*        staticLabelsFormatter.setVerticalLabels(new String[]{});*/
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.setTitle("心情分數折線圖");
        graph.getViewport().setMaxY(54);
        graph.getViewport().setMinY(0);
    }

/*    參考資料:
    1.客製化GraphView : https://www.numetriclabz.com/android-line-graph-using-graphview-library-tutorial/#Custom_Paint_Object
    2.GraphView官網 : http://www.android-graphview.org/download-getting-started/*/
}

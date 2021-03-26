package com.example.onome;

import androidx.appcompat.app.AppCompatActivity;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.onome.models.DatabaseHandler;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler = new DatabaseHandler(ResultActivity.this);
    SeekBar seekBar;
    TextView name1, name2;
    Switch overview;
    AnyChartView anyChartViewpie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);

        name1.setText((MainActivity.nameList.get(0).getNome()).toString());
        name2.setText((MainActivity.nameList.get(1).getNome()).toString());


        seekBar = findViewById(R.id.seek);
        overview = findViewById(R.id.switch1);
        anyChartViewpie = (AnyChartView) findViewById(R.id.any_chart_view_pie);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        int time = (int) b.get("Time");
        int progress = (int) b.get("Progress");
        seekBar.setProgress(progress);
        NamesPie(time);
        overview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    gotag(time, 0);
                }
                else{
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress > 0 && progress < 12.5)
                    go(0, progress);
                if(progress > 12.5 && progress < 25)
                    go(1, progress);
                if(progress > 25 && progress < 37.5)
                    go(2, progress);
                if(progress > 37.5 && progress < 50)
                    go(3, progress);
                if(progress > 50 && progress < 62.5)
                    go(4, progress);
                if(progress > 62.5 && progress < 75)
                    go(5, progress);
                if(progress > 75 && progress < 87.5)
                    go(6, progress);
                if(progress > 87.5)
                    go(7, progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
            }
        });
    }

    public void NamesPie(int time) {
        AnyChartView anyChartView = findViewById(R.id.any_chart_view_pie);
        Pie pie = AnyChart.pie();
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry(MainActivity.nameList.get(0).getNome() , MainActivity.nameList.get(0).getRes().get(time).getFrequencia()));
        data.add(new ValueDataEntry(MainActivity.nameList.get(1).getNome() , MainActivity.nameList.get(1).getRes().get(time).getFrequencia()));
        pie.data(data);
        anyChartView.setChart(pie);
    }

    public void go(int time, int progress){
        Intent myIntent = new Intent(this, ResultActivity.class);
        myIntent.putExtra("Time", time);
        myIntent.putExtra("Progress", progress);
        this.startActivity(myIntent);
    }

    public void gotag(int time, int progress){
        Intent myIntent = new Intent(this, TagActivity.class);
        myIntent.putExtra("Time", time);
        myIntent.putExtra("Progress", progress);
        this.startActivity(myIntent);
    }
}











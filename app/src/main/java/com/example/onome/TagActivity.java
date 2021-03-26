package com.example.onome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.charts.TagCloud;
import com.anychart.scales.OrdinalColor;
import com.example.onome.models.DatabaseHandler;
import java.util.ArrayList;
import java.util.List;

public class TagActivity extends AppCompatActivity {
    DatabaseHandler databaseHandler = new DatabaseHandler(TagActivity.this);
    SeekBar seekBar;
    TextView name1, name2;
    Switch overview;
    AnyChartView anyChartViewtag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tag_activity);

        seekBar = findViewById(R.id.seek);
        overview = findViewById(R.id.switch1);
        anyChartViewtag = (AnyChartView) findViewById(R.id.any_chart_view_tag);

        name1 = (TextView) findViewById(R.id.name1);
        name2 = (TextView) findViewById(R.id.name2);

        name1.setText((MainActivity.nameList.get(0).getNome()).toString());
        name2.setText((MainActivity.nameList.get(1).getNome()).toString());


        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        int time = (int) b.get("Time");
        int progress = (int) b.get("Progress");

        NamesChart(time);


        seekBar.setProgress(progress);
        overview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
                else{
                    gopie(time, 0);
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

    public void NamesChart(int time){
        AnyChartView anyChartView = findViewById(R.id.any_chart_view_tag);
        TagCloud tagCloud = AnyChart.tagCloud();
        tagCloud.title("Nomes Comparados...");
        OrdinalColor ordinalColor = OrdinalColor.instantiate();
        ordinalColor.colors(new String[] {
                "#26959f", "#f18126", "#3b8ad8", "#60727b", "#e24b26"
        });
        tagCloud.colorScale(ordinalColor);
        tagCloud.angles(new Double[] {-90d, 0d, 90d});

        tagCloud.colorRange().enabled(true);
        tagCloud.colorRange().colorLineSize(15d);

        List<DataEntry> data = databaseHandler.getNames(time);

        tagCloud.data(data);

        anyChartView.setChart(tagCloud);
    }
    public void go(int time, int progress){
        Intent myIntent = new Intent(this, TagActivity.class);
        myIntent.putExtra("Time", time);
        myIntent.putExtra("Progress", progress);
        this.startActivity(myIntent);
    }

    public void gopie(int time, int progress){
        Intent myIntent = new Intent(this, ResultActivity.class);
        myIntent.putExtra("Time", time);
        myIntent.putExtra("Progress", progress);
        this.startActivity(myIntent);
    }


}




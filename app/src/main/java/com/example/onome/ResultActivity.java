package com.example.onome;

import androidx.appcompat.app.AppCompatActivity;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.CategoryValueDataEntry;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.charts.TagCloud;
import com.anychart.scales.OrdinalColor;

import java.util.ArrayList;
import java.util.List;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        AnyChartView anyChartView = findViewById(R.id.any_chart_view);

        TagCloud tagCloud = AnyChart.tagCloud();


        OrdinalColor ordinalColor = OrdinalColor.instantiate();
        ordinalColor.colors(new String[] {
                "#26959f", "#f18126", "#3b8ad8", "#60727b", "#e24b26"
        });
        tagCloud.colorScale(ordinalColor);
        tagCloud.angles(new Double[] {-90d, 0d, 90d});

        tagCloud.colorRange().enabled(true);
        tagCloud.colorRange().colorLineSize(15d);

        List<DataEntry> data = new ArrayList<>();
        data.add(new CategoryValueDataEntry("Maria", "Nome", 11734129));
        data.add(new CategoryValueDataEntry("Jose", "Nome", 5754529));
        data.add(new CategoryValueDataEntry("Ana", "Nome", 3089858));
        data.add(new CategoryValueDataEntry("João", "Nome", 2984119));

        tagCloud.data(data);

        anyChartView.setChart(tagCloud);

    }
}
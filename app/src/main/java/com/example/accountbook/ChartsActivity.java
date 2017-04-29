package com.example.accountbook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by 旭东 on 2017/4/16.
 */

public class ChartsActivity extends AppCompatActivity {

    private LineChartView mChart;
    private Map<String,Integer> table=new TreeMap<>();
    private LineChartData mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);
        mChart=(LineChartView) findViewById(R.id.chart);
        //取出MainActivity传递过来的List对象
        List<CostBean> allDate= (List<CostBean>) getIntent().getSerializableExtra("cost_list");
        generateValues(allDate);
        generateData();
        mChart.setLineChartData(mData);
    }

    private void generateData() {
        List<Line> lines=new ArrayList<>();
        List<PointValue> values=new ArrayList<>();
        int indexX=0;
        for(Integer value:table.values()){
            values.add(new PointValue(indexX,value));
            indexX++;
        }
        Line line=new Line(values);
        line.setColor(ChartUtils.COLORS[0]);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(ChartUtils.COLORS[1]);
        lines.add(line);
        mData=new LineChartData(lines);
    }

    private void generateValues(List<CostBean> allDate) {
        if(allDate!=null){
            for(int i=0;i<allDate.size();i++){
                CostBean costBean=allDate.get(i);
                String costDate=costBean.costData;
                int costMoney=Integer.parseInt(costBean.costMoney);
                if(!table.containsKey(costDate)){
                    table.put(costDate,costMoney);
                }else{
                    int originMoney=table.get(costDate);
                    originMoney+=costMoney;
                    table.put(costDate,originMoney);
                }
            }
        }
    }
}

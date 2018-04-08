package ashishpatil.androidtest.view;


import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import ashishpatil.androidtest.Contracts.contract;
import ashishpatil.androidtest.Database.InstanceClass;
import ashishpatil.androidtest.R;
import ashishpatil.androidtest.entitys.HeartRates;
import ashishpatil.androidtest.presenters.Presenter_History;


public class HistroyFragment extends android.app.Fragment implements OnChartGestureListener, OnChartValueSelectedListener, contract.IHistroyView {

    private TextView device_name;
    private LinearLayout back_navigation;
    private LineChart mChart;
    private ArrayList<String> xVals;
    private ArrayList<Entry> yVals;
    private Presenter_History presenter_history;

    public View onCreateView(LayoutInflater inflater, ViewGroup vg,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        return inflater.inflate(R.layout.fragment_histroy, vg, false);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mChart = getView().findViewById(R.id.linechart);
        device_name = getView().findViewById(R.id.device_name);
        back_navigation = getView().findViewById(R.id.back_navigation);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        presenter_history = new Presenter_History(this);

        getDiviceName();
        setData();


        // Upper and Lowar Limits

        LimitLine upper_limit = new LimitLine(100f, "Upper range of heart rate while resting");
        upper_limit.setLineWidth(2f);
        upper_limit.enableDashedLine(10f, 10f, 0f);
        upper_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        upper_limit.setTextSize(10f);

        LimitLine lower_limit = new LimitLine(60f, "Lower range of heart rate while resting");
        lower_limit.setLineWidth(2f);
        lower_limit.enableDashedLine(10f, 10f, 0f);
        lower_limit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        lower_limit.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();

        // reset all limit lines to avoid overlapping lines
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(upper_limit);
        leftAxis.addLimitLine(lower_limit);
        leftAxis.setAxisMaxValue(120f);
        leftAxis.setAxisMinValue(30f);

        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        // Animate line chart
        mChart.animateX(1000);
        mChart.animateY(100);

        mChart.getAxisRight().setEnabled(false);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        l.setForm(Legend.LegendForm.LINE);

        // Back navigation on title click
        back_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
            }
        });


    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            mChart.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }


    private void setData() {

        setXYValues();

        LineDataSet set1;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "Heart Rate Histroy");
        set1.setFillAlpha(110);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(5f);
        set1.setDrawCircleHole(true);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(false);


        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        // add the datasets
        dataSets.add(set1);

        // Create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // Set Line Chart Data
        mChart.setData(data);

    }


    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }


    @Override
    public void getDiviceName() {

        device_name.setText(presenter_history.getDiviceName());
    }

    public void setXYValues() {
        List<HeartRates> heartRates;
        yVals = new ArrayList<Entry>();
        xVals = new ArrayList<String>();

        heartRates = presenter_history.getHeartData(InstanceClass.getDatabaseHelper(getActivity().getApplicationContext()));

        for (int i = 0; i < heartRates.size(); i++) {
            xVals.add(heartRates.get(i).getTime());
            yVals.add(new Entry(Float.parseFloat(heartRates.get(i).getValue()), i));

        }

    }
}

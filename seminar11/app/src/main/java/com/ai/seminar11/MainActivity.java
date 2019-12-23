package com.ai.seminar11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linear);
        float values[] = {300, 400, 100, 500};
        linearLayout.addView(new PieChartView(this, calculatePieChartData(values)));
    }

    private float[] calculatePieChartData(float[] values) {
        float total = 0;
        float[] pieValues = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            total += values[i];
        }
        for (int i = 0; i < values.length; i++) {
            pieValues[i] = 360 * (values[i] / total);
        }
        return pieValues;
    }

    public class PieChartView extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] valueDegree;
        private int[] COLORS = {Color.BLUE, Color.GREEN, Color.GRAY, Color.CYAN, Color.RED};
        RectF rectf = new RectF(10, 10, 500, 500);
        int temp = 0;

        public PieChartView(Context context, float[] values) {

            super(context);
            valueDegree = new float[values.length];
            for (int i = 0; i < values.length; i++) {
                valueDegree[i] = values[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for (int i = 0; i < valueDegree.length; i++) {//values2.length; i++) {
                if (i == 0) {
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, 0, valueDegree[i], true, paint);
                } else {
                    temp += (int) valueDegree[i - 1];
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, valueDegree[i], true, paint);
                }
            }
        }
    }

}

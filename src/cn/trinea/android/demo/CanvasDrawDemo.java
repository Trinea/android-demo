package cn.trinea.android.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * CanvasDrawDemo
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-10-11
 */
public class CanvasDrawDemo extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.canvas_demo);

        RelativeLayout layout = (RelativeLayout)findViewById(R.id.canvas_demo_layout);
        CanvasDemoView view = new CanvasDemoView(this);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.leftMargin = 600;
        // params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layout.addView(view, params);
    }

    public class CanvasDemoView extends View {

        public CanvasDemoView(Context context){
            super(context);
        }

        public CanvasDemoView(Context context, AttributeSet attrs){
            super(context, attrs);
        }

        Paint paint = new Paint();

        protected void dispatchDraw(Canvas canvas) {
            super.dispatchDraw(canvas);

            canvas.drawText("画贝塞尔曲线:", 10, 310, paint);
            paint.reset();
            // paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.GREEN);
            Path path2 = new Path();
            path2.moveTo(100, 320);// 设置Path的起点
            path2.quadTo(150, 310, 170, 400); // 设置贝塞尔曲线的控制点坐标和终点坐标
            // canvas.drawPath(path2, paint);//画出贝塞尔曲线
            Path path1 = new Path();
            path1.moveTo(30, 400);// 设置Path的起点
            path1.quadTo(100, 310, 170, 400); // 设置贝塞尔曲线的控制点坐标和终点坐标
            canvas.drawPath(path1, paint);// 画出贝塞尔曲线

            // paint.setColor(Color.rgb(160, 160, 160));
            // float width = 2.5f;
            // paint.setStrokeWidth(width);
            // RectF oval = new RectF(0, 0, 100, 150);
            // canvas.drawRect(oval, paint);
            // paint.setStyle(Paint.Style.STROKE);
            // paint.setColor(Color.rgb(60, 160, 0));
            // canvas.drawArc(oval, 0, 90, false, paint);
        }
    }
}

package com.test.fastshape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/***
 *   created by zhongrui on 2018/12/30
 */
public class TestV extends View {
    public TestV(Context context) {
        super(context);
    }

    public TestV(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestV(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {Path path=new Path();
//        path.addRoundRect(new RectF(0,0,getWidth(),getHeight()), 100,100, Path.Direction.CW);
//        canvas.clipPath(path);
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path=new Path();
        path.addRoundRect(new RectF(0,0,getWidth(),getHeight()), 100,100, Path.Direction.CW);

        Path p=new Path();
        p.addRect(new RectF(0,0,getWidth(),getHeight()), Path.Direction.CW);

        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
//        canvas.clipPath(path);
        paint.setColor(Color.BLUE);
//        canvas.drawPath(path,paint);

        p.op(path, Path.Op.DIFFERENCE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(p,paint);

    }

    /**
     * Transparent the given color by the factor
     * The more the factor closer to zero the more the color gets transparent
     *
     * @param color  The color to transparent
     * @param factor 1.0f to 0.0f
     * @return int - A transplanted color
     */
    private int adjustAlpha(int color, float factor) {
        int alpha = Math.round(Color.alpha(color) * factor);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("=======","====superonSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("=======","====onSizeChanged");
    }

    public void aa(){
        post(new Runnable() {
            @Override
            public void run() {
                Log.i("=======","====Runnable");
            }
        });
    }
}

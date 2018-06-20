package com.github.fastshape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/7/24.
 */

public class MyImageView extends ImageView {
    private Paint paint;
    private Paint paintView;
    /***圆角半径*/
    private float radius;
    /***左上圆角半径*/
    private float topLeftRadius;
    /***右上圆角半径*/
    private float topRightRadius;
    /***左下圆角半径*/
    private float bottomLeftRadius;
    /***右下圆角半径*/
    private float bottomRightRadius;

    public MyImageView(Context context) {
        super(context);
        init(context,null);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs == null) {
            return;
        }
        TypedArray viewNormal = context.obtainStyledAttributes(attrs, R.styleable.MyImageView);
        radius = viewNormal.getDimension(R.styleable.MyImageView_radius, 0);
        bottomRightRadius = viewNormal.getDimension(R.styleable.MyImageView_bottomRightRadius, 0);
        bottomLeftRadius = viewNormal.getDimension(R.styleable.MyImageView_bottomLeftRadius, 0);
        topRightRadius = viewNormal.getDimension(R.styleable.MyImageView_topRightRadius, 0);
        topLeftRadius = viewNormal.getDimension(R.styleable.MyImageView_topLeftRadius, 0);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paintView = new Paint();
        paintView.setXfermode(null);
    }

    public MyImageView setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public MyImageView setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public MyImageView setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public MyImageView setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public MyImageView setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }
    public void complete(){
        invalidate();
    }
    @Override
    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvasRadius = new Canvas(bitmap);
        super.draw(canvasRadius);
        if(radius>0){
            drawLiftUp(canvasRadius,radius);
            drawLiftDown(canvasRadius,radius);
            drawRightUp(canvasRadius,radius);
            drawRightDown(canvasRadius,radius);
        }else{
            drawLiftUp(canvasRadius,topLeftRadius);
            drawLiftDown(canvasRadius,bottomLeftRadius);
            drawRightUp(canvasRadius,topRightRadius);
            drawRightDown(canvasRadius,bottomRightRadius);
        }
        canvas.drawBitmap(bitmap, 0, 0, paintView);
        bitmap.recycle();
    }

    private void drawLiftUp(Canvas canvas,float radius) {
        Path path = new Path();
        path.moveTo(0, radius);
        path.lineTo(0, 0);
        path.lineTo(radius, 0);
        path.arcTo(new RectF(0, 0, radius * 2, radius * 2), -90, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLiftDown(Canvas canvas,float radius) {
        Path path = new Path();
        path.moveTo(0, getHeight() - radius);
        path.lineTo(0, getHeight());
        path.lineTo(radius, getHeight());
        path.arcTo(new RectF(0, getHeight() - radius * 2, radius * 2, getHeight()), 90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas,float radius) {
        Path path = new Path();
        path.moveTo(getWidth() - radius, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - radius);
        path.arcTo(new RectF(getWidth() - radius * 2, getHeight() - radius * 2, getWidth(), getHeight()), -0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas,float radius) {
        Path path = new Path();
        path.moveTo(getWidth(), radius);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - radius, 0);
        path.arcTo(new RectF(getWidth() - radius * 2, 0, getWidth(), 0 + radius * 2), -90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }
}

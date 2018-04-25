package com.github.fastshape;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/4/9.
 */

public class Helper {



    protected int left_width;
    protected int left_height;
    protected int top_width;
    protected int top_height;
    protected int right_width;
    protected int right_height;
    protected int bottom_width;
    protected int bottom_height;

    public int getLeft_width() {
        return left_width;
    }

    public void setLeft_width(int left_width) {
        this.left_width = left_width;
    }

    public int getLeft_height() {
        return left_height;
    }

    public void setLeft_height(int left_height) {
        this.left_height = left_height;
    }

    public int getTop_width() {
        return top_width;
    }

    public void setTop_width(int top_width) {
        this.top_width = top_width;
    }

    public int getTop_height() {
        return top_height;
    }

    public void setTop_height(int top_height) {
        this.top_height = top_height;
    }

    public int getRight_width() {
        return right_width;
    }

    public void setRight_width(int right_width) {
        this.right_width = right_width;
    }

    public int getRight_height() {
        return right_height;
    }

    public void setRight_height(int right_height) {
        this.right_height = right_height;
    }

    public int getBottom_width() {
        return bottom_width;
    }

    public void setBottom_width(int bottom_width) {
        this.bottom_width = bottom_width;
    }

    public int getBottom_height() {
        return bottom_height;
    }

    public void setBottom_height(int bottom_height) {
        this.bottom_height = bottom_height;
    }


    protected int[] getLeftWH(int width,int height){
        if(left_width!=-1&&left_height!=-1){
            return new int[]{left_width,left_height};
        }else if(left_width!=-1){
            return new int[]{left_width, (int) chuFa(chengFa(left_width,height),width)};
        }else if(left_height!=-1){
            return new int[]{left_height, (int) chuFa(chengFa(left_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected int[] getTopWH(int width,int height){
        if(top_width!=-1&&top_height!=-1){
            return new int[]{top_width,top_height};
        }else if(top_width!=-1){
            return new int[]{top_width, (int) chuFa(chengFa(top_width,height),width)};
        }else if(top_height!=-1){
            return new int[]{top_height, (int) chuFa(chengFa(top_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected int[] getRightWH(int width,int height){
        if(right_width!=-1&&right_height!=-1){
            return new int[]{right_width,right_height};
        }else if(right_width!=-1){
            return new int[]{right_width, (int) chuFa(chengFa(right_width,height),width)};
        }else if(right_height!=-1){
            return new int[]{right_height, (int) chuFa(chengFa(right_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }
    protected int[] getBottomWH(int width,int height){
        if(bottom_width!=-1&&bottom_height!=-1){
            return new int[]{bottom_width,bottom_height};
        }else if(bottom_width!=-1){
            return new int[]{bottom_width, (int) chuFa(chengFa(bottom_width,height),width)};
        }else if(bottom_height!=-1){
            return new int[]{bottom_height, (int) chuFa(chengFa(bottom_height,width),height)};
        }else{
            return new int[]{width,height};
        }
    }

    protected  double chuFa(double d1,double d2) {
        return chuFa(d1,d2,2);
    }
    private  double chuFa(double d1,double d2,int scale) {
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理
        try {
            if(d2==0){
                throw new Exception("分母不能为0");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.divide
                (bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    protected double chengFa(double d1,double d2){
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);
        return round(bd1.multiply(bd2).doubleValue());
    }
    protected double round(double value) {
        return round(value,2, BigDecimal.ROUND_HALF_UP);
    }
    protected double round(double value, int scale,int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
}

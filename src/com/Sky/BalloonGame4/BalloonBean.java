package com.Sky.BalloonGame4;

import android.graphics.Bitmap;

/**
 * Created by Sky
 * Date: 2015/4/10.
 */
public class BalloonBean {
    /**
     * 气球图片对象，x坐标，y坐标，出现时间,是否存在
     */
    private Bitmap balloon;
    private int x;
    private int y;
    private int t;
    private boolean exited;

    /**
     *
     * @param balloon bitmap类型，气球图片
     * @param x     int 气球x坐标
     * @param y     int 气球y坐标
     * @param t     int 气球飘的时间
     * @param exited    boolean 气球是否被点破了
     */
    public BalloonBean(Bitmap balloon, int x, int y, int t, boolean exited) {
        this.balloon = balloon;
        this.x = x;
        this.y = y;
        this.t = t;
        this.exited = exited;
    }

    public boolean isExited() {
        return exited;
    }

    public void setExited(boolean exited) {
        this.exited = exited;
    }

    public Bitmap getBalloon() {
        return balloon;
    }

    public void setBalloon(Bitmap balloon) {
        this.balloon = balloon;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}

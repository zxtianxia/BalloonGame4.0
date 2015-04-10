package com.Sky.BalloonGame4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.SurfaceHolder;

/**
 * Created by Sky
 * Date: 2015/4/10.
 */
public class BalloonGame_NormalThread extends Thread {
    private boolean running;
    int type;
    DrawCanvs_DependonGameType drawcanvs;
    SurfaceHolder surfaceHolder;
    Context context;
    private Bitmap bitmap_black, bitmap_blue, bitmap_gray, bitmap_green, bitmap_lightblue, bitmap_orange, bitmap_pink;
    /**
     * @param surfaceHolder
     * @param context
     */
    public BalloonGame_NormalThread(SurfaceHolder surfaceHolder, Context context, int type) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        running = true;
        initBalloonBitmap();
        this.type = type;
    }
    /**
     * surfaceView后台绘画线程
     */
    @Override
    public void run() {
        //根据游戏的类型不同，采用不同的画法
        judgeCanvas();
        while (running) {
            //获取画布
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            //调用画法
            drawcanvs.drawCavs();
            //将画出内容推送到surfaceview中
            surfaceHolder.unlockCanvasAndPost(canvas);
            //设置图像推送间隔时间
            SystemClock.sleep(100);
        }
    }
    /**
     * 退出图像绘制的后台进程
     */
    public void exit() {
        running = false;
    }
/******************************以下为私有方法***********************************************/

    /**
     * 初始化所有的气球图像
     */
    private void initBalloonBitmap() {
        bitmap_black = BitmapFactory.decodeResource(context.getResources(), R.drawable.black);
        bitmap_blue = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue);
        bitmap_gray = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray);
        bitmap_green = BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
        bitmap_lightblue = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightblue);
        bitmap_orange = BitmapFactory.decodeResource(context.getResources(), R.drawable.orange);
        bitmap_pink = BitmapFactory.decodeResource(context.getResources(), R.drawable.pink);
    }
    //根据游戏的类型不同，采用不同的画法
    private void judgeCanvas() {

        switch (type) {
            case GameType.Easy:
                drawcanvs=new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs() {

                    }
                };
                break;
            case GameType.Normal:
                drawcanvs=new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs() {

                    }
                };
                break;
            case GameType.Difficult:
                drawcanvs=new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs() {

                    }
                };
                break;
            case GameType.Crazy:
                drawcanvs=new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs() {

                    }
                };
                break;
        }
    }
}

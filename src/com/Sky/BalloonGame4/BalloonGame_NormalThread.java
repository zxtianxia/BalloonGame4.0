package com.Sky.BalloonGame4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sky
 * Date: 2015/4/10.
 */
public class BalloonGame_NormalThread extends Thread {
    private boolean running;
    int type;
    int a = 1;
    int canvasheight;
    int canvaswidth;

    DrawCanvs_DependonGameType drawcanvs;
    SurfaceHolder surfaceHolder;
    Context context;
    List<BalloonBean> balloonBeans_list;
    List<Bitmap> bitmaps_list;
    private Bitmap bitmap_black, bitmap_blue, bitmap_gray, bitmap_green, bitmap_lightblue, bitmap_orange, bitmap_pink;

    /**
     * @param surfaceHolder
     * @param context
     */
    public BalloonGame_NormalThread(SurfaceHolder surfaceHolder, Context context, int type) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        this.type = type;
        running = true;
        //气球初始化
        initBalloonBitmap();

        //游戏界面气球初始化
        balloonBeans_list = new ArrayList<BalloonBean>();
        Canvas canvas = surfaceHolder.lockCanvas();
        canvaswidth = canvas.getWidth();
        canvasheight = canvas.getHeight();
        surfaceHolder.unlockCanvasAndPost(canvas);
        for (int i = 0; i < 8; i++) {
            Bitmap balloon = bitmaps_list.get(getRandom(bitmaps_list.size()));//
            BalloonBean bean = new BalloonBean(balloon, getRandom(canvaswidth - bitmap_black.getWidth()), canvasheight + getRandom(canvasheight), 0, true);
            balloonBeans_list.add(bean);
        }
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
            drawcanvs.drawCavs(canvas);
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

    /**
     * ***************************以下为私有方法**********************************************
     */


    //根据游戏的类型不同，采用不同的画法
    private void judgeCanvas() {

        switch (type) {
            case GameType.Easy:

                drawcanvs = new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs(Canvas canvas) {
                        for (int i = 0; i < balloonBeans_list.size(); i++) {
                            BalloonBean bean = balloonBeans_list.get(i);
                            //for (BalloonBean bean : balloonBeans_list) {
                            int y = bean.getY();
                            if (!bean.isExited() || y < -bitmap_black.getHeight()) {
                                Log.i("remove","-----"+balloonBeans_list.indexOf(bean));
                                balloonBeans_list.remove(bean);

                                i--;
                                Bitmap balloon = bitmaps_list.get(getRandom(bitmaps_list.size()));
                                BalloonBean e = new BalloonBean(balloon, getRandom(canvaswidth - bitmap_black.getWidth()), canvasheight + getRandom(bitmap_black.getHeight()), 0, true);
                                balloonBeans_list.add(e);
                            } else {
                                if (y > canvasheight) {
                                    bean.setT(1);
                                    bean.setY(y - 8);
                                } else {
                                    canvas.drawBitmap(bean.getBalloon(), bean.getX(), y, null);
                                    int t = bean.getT();
                                    bean.setY(y - a * t * t / 2);
                                    bean.setT(++t);
                                }
                            }
                        }
                    }
                };
                break;
            case GameType.Normal:
                drawcanvs = new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs(Canvas canvas) {

                    }
                };
                break;
            case GameType.Difficult:
                drawcanvs = new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs(Canvas canvas) {

                    }
                };
                break;
            case GameType.Crazy:
                drawcanvs = new DrawCanvs_DependonGameType() {
                    @Override
                    public void drawCavs(Canvas canvas) {

                    }
                };
                break;
        }
    }


    /**
     * 通过传过来的数值范围来获得随机数
     */
    private int getRandom(int totallenth) {
        return (int) (Math.random() * totallenth);
    }

    /**
     * 初始化所有的气球图像
     * 并添加到气球结合中
     */
    private void initBalloonBitmap() {
        bitmaps_list = new ArrayList<Bitmap>();
        bitmap_black = BitmapFactory.decodeResource(context.getResources(), R.drawable.black);
        bitmaps_list.add(bitmap_black);
        bitmap_blue = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue);
        bitmaps_list.add(bitmap_blue);
        bitmap_gray = BitmapFactory.decodeResource(context.getResources(), R.drawable.gray);
        bitmaps_list.add(bitmap_gray);
        bitmap_green = BitmapFactory.decodeResource(context.getResources(), R.drawable.green);
        bitmaps_list.add(bitmap_green);
        bitmap_lightblue = BitmapFactory.decodeResource(context.getResources(), R.drawable.lightblue);
        bitmaps_list.add(bitmap_lightblue);
        bitmap_orange = BitmapFactory.decodeResource(context.getResources(), R.drawable.orange);
        bitmaps_list.add(bitmap_orange);
        bitmap_pink = BitmapFactory.decodeResource(context.getResources(), R.drawable.pink);
        bitmaps_list.add(bitmap_pink);
    }
}

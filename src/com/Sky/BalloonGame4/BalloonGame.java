package com.Sky.BalloonGame4;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 *
 */
public class BalloonGame extends Activity implements SurfaceHolder.Callback {
    @ViewInject(R.id.surfaceView_balloon)
    SurfaceView surfaceView;
    BalloonGame_NormalThread normalThread;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        normalThread=new BalloonGame_NormalThread(surfaceHolder,BalloonGame.this);
        normalThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        normalThread.exit();
    }
}

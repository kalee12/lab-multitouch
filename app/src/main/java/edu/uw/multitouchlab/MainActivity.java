package edu.uw.multitouchlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    private DrawingSurfaceView view;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = (DrawingSurfaceView)findViewById(R.id.drawingView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY() - getSupportActionBar().getHeight(); //closer to center...

        int action = event.getActionMasked();
        switch(action) {
            case (MotionEvent.ACTION_DOWN) : //put finger down
                //Log.v(TAG, "finger down");
//                view.ball.cx = x;
//                view.ball.cy = y;
                DrawingSurfaceView.addTouch(event.getPointerId(event.getActionIndex())
                        , event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                return true;
            case (MotionEvent.ACTION_MOVE) : //move finger
                //Log.v(TAG, "finger move");
//                view.ball.cx = x;
//                view.ball.cy = y;
                int count = event.getPointerCount();
                for (int i = 0; i < count; i++) {
                    DrawingSurfaceView.moveTouch(event.getPointerId(i), event.getX(event.getPointerId(i)),
                            event.getY(event.getPointerId(i)));
                }
                return true;
            case (MotionEvent.ACTION_UP) : //lift finger up
                Log.v(TAG, "last finger");
                DrawingSurfaceView.removeTouch(event.getPointerId(event.getActionIndex()));
                return true;
            case (MotionEvent.ACTION_CANCEL) : //aborted gesture
                Log.v(TAG, "canceled");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) : //outside bounds
                Log.v(TAG, "outside");
            case (MotionEvent.ACTION_POINTER_DOWN) :
                Log.v(TAG, "Another finger down");
                Log.v(TAG, event.getActionIndex() + "");
                Log.v(TAG, event.getPointerId(event.getActionIndex()) + "");
                DrawingSurfaceView.addTouch(event.getPointerId(event.getActionIndex())
                        , event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                return true;
            case (MotionEvent.ACTION_POINTER_UP) :
                Log.v(TAG, "A finger lifted");
                Log.v(TAG, event.getActionIndex() + "");
                Log.v(TAG, event.getPointerId(event.getActionIndex()) + "");

                DrawingSurfaceView.removeTouch(event.getPointerId(event.getActionIndex()));
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
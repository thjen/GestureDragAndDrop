package com.example.qthjen.gesturedraganddrop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    int mode = 0;
    int DRAG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, 250);
        layoutParams.leftMargin = 50;
        layoutParams.topMargin = 50;
        image.setLayoutParams(layoutParams);
        image.setOnTouchListener(new View.OnTouchListener() {
            RelativeLayout.LayoutParams params;
            float dx = 0, dy = 0, x = 0, y = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                ImageView imageView = (ImageView) view;
                switch (motionEvent.getAction() & motionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                        dx = motionEvent.getRawX() - params.leftMargin;
                        dy = motionEvent.getRawY() - params.topMargin;
                        mode = DRAG;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if ( mode == DRAG) {
                            x = motionEvent.getRawX();
                            y = motionEvent.getRawY();

                            params.leftMargin = (int) (x - dx);
                            params.topMargin = (int) (y - dy);

                            params.rightMargin = 0;
                            params.bottomMargin = 0;
                            params.rightMargin = params.leftMargin + (5* params.width);
                            params.bottomMargin = params.topMargin + (19* params.height);
                            imageView.setLayoutParams(params);
                        }

                        break;
                }

                return true;
            }
        });

    }
}

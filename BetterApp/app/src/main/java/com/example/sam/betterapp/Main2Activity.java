package com.example.sam.betterapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import java.lang.Math;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final ImageView rocket = (ImageView) findViewById(R.id.rocket);
        final ImageView bad = (ImageView) findViewById(R.id.bad);



        Runnable runnable = new Runnable() {
            public void run() {


                while (true) {
                    synchronized (this) {
                        try {
                            move_image(bad);
                        } catch (Exception e) {}
                    }
                }
            }
        };
        Thread mythread = new Thread(runnable);
        mythread.start();



        rocket.setOnTouchListener(new View.OnTouchListener()
        {
            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
            PointF StartPT = new PointF(); // Record Start Position of 'img'

            //@Override
            public boolean onTouch(View v, MotionEvent event)
            {
                float angle=90;





                //rocket.setImageResource(R.drawable.bad);
                int eid = event.getAction();
                //  final Toast toast = Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT);
                String Postion= "X: "+rocket.getX()+" Y: "+rocket.getY();

                Log.i("Game_position",Postion);

                final Toast toast = Toast.makeText(Main2Activity.this, Postion,Toast.LENGTH_SHORT);


                switch (eid)
                {
                    case MotionEvent.ACTION_MOVE :


                        PointF mv = new PointF( event.getX() - DownPT.x, event.getY() - DownPT.y);
                        rocket.setX((int)(StartPT.x+mv.x));
                        rocket.setY((int)(StartPT.y+mv.y));
                        StartPT = new PointF( rocket.getX(), rocket.getY() );
                        Postion= "X: "+rocket.getX()+" Y: "+rocket.getY();
                        toast.makeText(Main2Activity.this, Postion,Toast.LENGTH_SHORT);
                        if(is_Touching(rocket,bad)) {
                            Intent myIntent = new Intent(Main2Activity.this, End.class);
                            //myIntent.putExtra("key", value); //Optional parameters
                            Main2Activity.this.startActivity(myIntent);
                        }
                        new CountDownTimer(10, 10)
                        {
                            public void onTick(long millisUntilFinished) {}//toast.show();}
                            public void onFinish() {toast.cancel();}
                        }.start();

                        //toast.cancel();
                        //  rocket.setPivotX(rocket.getWidth()/2);
                        //  rocket.setPivotY(rocket.getHeight()/2);
                        //  rocket.setRotation(angle);

                        break;
                    case MotionEvent.ACTION_DOWN :
                        DownPT.x = event.getX();
                        DownPT.y = event.getY();
                        StartPT = new PointF( rocket.getX(), rocket.getY() );
                        break;
                    case MotionEvent.ACTION_UP :
                        // Nothing have to do
                        //rocket.setImageResource(R.drawable.rocket);
                        toast.cancel();
                        break;
                    default :
                        toast.cancel();
                        break;
                }
                return true;
            }
        });

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean is_Touching(ImageView a, ImageView b)
    {


        if(distance(a.getX(), a.getY(),b.getX(), b.getY())<90) {
            return true;
        }
        else
            return false;
    }

    public double distance(float ax, float ay, float bx, float by)
    {
        return Math.sqrt((float)(ax-bx)*(ax-bx)+(float)(ay-by)*(ay-by));
    }

int wait=0;
    public void move_image(ImageView image)
    {
        int distance=1;
        int direction=  (int)(10.0*(Math.random()));
        direction=direction%4;
        wait++;

        if(wait==5000) {
            wait=0;
            if (direction == 0) {
                image.setY(image.getY() - distance);
            }
            if (direction == 1) {
                image.setY(image.getY() + distance);
            }
            if (direction == 2) {
                image.setX(image.getX() - distance);
            }
            if (direction == 3) {
                image.setX(image.getX() + distance);
            }
        }

    }

}

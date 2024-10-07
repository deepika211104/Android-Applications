package com.example.canvarectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        setContentView(new Myview(this));

    }

    private class Myview extends View {
        public Myview(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);
            Paint paint=new Paint();
            int x=getWidth();
            int y=getHeight();

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.MAGENTA);
            canvas.drawPaint(paint);

            paint.setColor(Color.YELLOW);
            canvas.drawRect(x/2-300,y/2-300,x/2+300,y/2+300,paint);

        }
    }
}
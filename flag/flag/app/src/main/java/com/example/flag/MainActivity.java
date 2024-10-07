package com.example.flag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FlagView(this));
    }

    private class FlagView extends View {
        private Paint paint;

        public FlagView(Context context) {
            super(context);
            paint = new Paint();
        }

        @Override
        protected void onDraw(@NonNull Canvas canvas) {
            super.onDraw(canvas);

            // Define dimensions
            int flagWidth = getWidth() - 100;
            int flagHeight = getHeight() / 3; // Divide height into 3 parts for stripes
            int xStart = 50;
            int yStart = 100;

            // Draw Saffron Rectangle (Top Stripe)
            paint.setColor(Color.rgb(255, 153, 51)); // Saffron color
            canvas.drawRect(xStart, yStart, xStart + flagWidth, yStart + flagHeight, paint);

            // Draw White Rectangle (Middle Stripe)
            paint.setColor(Color.WHITE);
            canvas.drawRect(xStart, yStart + flagHeight, xStart + flagWidth, yStart + 2 * flagHeight, paint);

            // Draw Green Rectangle (Bottom Stripe)
            paint.setColor(Color.rgb(19, 136, 8)); // Green color
            canvas.drawRect(xStart, yStart + 2 * flagHeight, xStart + flagWidth, yStart + 3 * flagHeight, paint);

            // Draw Ashoka Chakra (Circle in center of white stripe)
            int circleRadius = 200;
            int circleX = xStart + flagWidth / 2;
            int circleY = yStart + flagHeight + flagHeight / 2; // Middle of the white stripe

            paint.setColor(Color.rgb(0, 0, 128)); // Navy blue color for Ashoka Chakra
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            canvas.drawCircle(circleX, circleY, circleRadius, paint);

            // Draw 24 spokes inside the Ashoka Chakra
            int spokeCount = 24;
            double angle = 360 / spokeCount;
            for (int i = 0; i < spokeCount; i++) {
                float startX = circleX + (float) (circleRadius * Math.cos(Math.toRadians(i * angle)));
                float startY = circleY + (float) (circleRadius * Math.sin(Math.toRadians(i * angle)));
                canvas.drawLine(circleX, circleY, startX, startY, paint);
            }

        }
    }
}
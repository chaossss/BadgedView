package com.github.chaossss.widget.badge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.DisplayMetrics;

/**
 * Created by chaos on 2016/1/6.
 */
public class CircleBadge extends BaseBadge {
    private static final double MAGIC = 1.732;

    private int radius;
    private int center;

    public CircleBadge(Context context, String badgeText, int badgeColor, int badgeTextColor) {
        super(context, badgeText, badgeColor, badgeTextColor);
    }

    @Override
    public void initBadge() {
        Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setColor(badgeColor);
        backgroundPaint.setStyle(Paint.Style.FILL);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float scaledDensity = dm.scaledDensity;

        TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint
                .SUBPIXEL_TEXT_FLAG);
        textPaint.setTypeface(Typeface.create(TYPEFACE, TYPEFACE_STYLE));
        textPaint.setTextSize(textSize * scaledDensity);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(badgeTextColor);

        Rect textBounds = new Rect();
        textPaint.getTextBounds(badgeText, 0, badgeText.length(), textBounds);

        if(textBounds.height() > textBounds.width()){
            radius = (int)(textBounds.height() / 2 * MAGIC);
        } else {
            radius = (int)(textBounds.width() / 2  * MAGIC);
        }

        badge = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
        badge.setHasAlpha(true);

        Canvas canvas = new Canvas(badge);
        canvas.drawCircle(radius, radius, radius, backgroundPaint);
        canvas.drawText(badgeText, radius, radius + textBounds.height() / 2, textPaint);
    }

    @Override
    public void draw(Canvas canvas) {
        if(badge == null) {
            initBadge();
        }
        canvas.drawBitmap(badge, getBounds().left, getBounds().top, paint);
    }

    @Override
    public int getIntrinsicWidth() {
        return radius * 2;
    }

    @Override
    public int getIntrinsicHeight() {
        return radius * 2;
    }
}

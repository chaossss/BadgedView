package com.github.chaossss.widget.badge;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.DisplayMetrics;

/**
 * Badge that draws a triangle
 * Created by chaos on 2015/11/27.
 */
public class TriBadge extends BaseBadge {
    private static final double SQUARE2 = 1.414;
    private static final double SQUARE5 = 2.236;

    public static final int TOPLEFT = 0x00;
    public static final int TOPRIGHT = 0x01;
    public static final int BOTTMLEFT = 0x10;
    public static final int BOTTOMRIGHT = 0x11;

    private int triLength;
    private int rootViewWidth;
    private int rootViewHeight;
    private int tribadgeGravity;

    public TriBadge(Context context, String badgeText, int badgeColor, int badgeTextColor) {
        super(context, badgeText, badgeColor, badgeTextColor);
    }

    public void setTribadgeGravity(int tribadgeGravity) {
        this.tribadgeGravity = tribadgeGravity;
    }

    public void setRootViewParam(int rootViewWidth, int rootViewHeight){
        this.rootViewWidth = rootViewWidth;
        this.rootViewHeight = rootViewHeight;
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
        textPaint.setColor(badgeTextColor);

        badge = Bitmap.createBitmap(rootViewWidth, rootViewHeight, Bitmap.Config.ARGB_8888);
        badge.setHasAlpha(true);

        Canvas canvas = new Canvas(badge);

        Path path = new Path();
        Rect textBounds = new Rect();
        textPaint.getTextBounds(badgeText, 0, badgeText.length(), textBounds);

        //Calculates triangle badges's length by the input badgeText's length
        triLength = (int)((textBounds.height() / 4 * 7 + textBounds.width() / 2) * SQUARE2);

        drawTribadgePath(canvas, path, backgroundPaint);

        float textYOffset = textBounds.height() / 8 * 11;
        //Keep the offset distance
        if(tribadgeGravity >> 1 == 0){
            textYOffset = -textYOffset / 2;
        }
        float textXOffset = (float)(triLength * SQUARE2 - textBounds.width()) / 2;

        canvas.drawTextOnPath(badgeText, path, textXOffset, textYOffset, textPaint);
    }

    private void drawTribadgePath(Canvas canvas, Path path, Paint backgroundPaint){
        switch (tribadgeGravity){
            case TOPLEFT:
                path.moveTo(0, triLength);
                path.lineTo(triLength, 0);
                path.lineTo(0, 0);
                path.close();
                break;

            case TOPRIGHT:
                path.moveTo(rootViewWidth - triLength, 0);
                path.lineTo(rootViewWidth, triLength);
                path.lineTo(rootViewWidth, 0);
                path.close();
                break;

            case BOTTMLEFT:
                path.moveTo(0, rootViewHeight - triLength);
                path.lineTo(triLength, rootViewHeight);
                path.lineTo(0, rootViewHeight);
                path.close();
                break;

            case BOTTOMRIGHT:
                path.moveTo(rootViewWidth - triLength, rootViewHeight);
                path.lineTo(rootViewWidth, rootViewHeight - triLength);
                path.lineTo(rootViewWidth, rootViewHeight);
                path.close();
                break;
        }

        canvas.drawPath(path, backgroundPaint);
    }

    @Override
    public void draw(Canvas canvas) {
        if(badge == null) {
            initBadge();
        }
        canvas.drawBitmap(badge, 0, 0, paint);
    }
}
